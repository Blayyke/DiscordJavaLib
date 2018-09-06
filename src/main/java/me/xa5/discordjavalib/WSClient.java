package me.xa5.discordjavalib;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.WriterConfig;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.handler.WSHandlerPresenceUpdate;
import me.xa5.discordjavalib.handler.WSHandlerReady;
import me.xa5.discordjavalib.handler.WSHandlerTypingStart;
import me.xa5.discordjavalib.handler.channel.WSHandlerChannelCreate;
import me.xa5.discordjavalib.handler.channel.WSHandlerChannelDelete;
import me.xa5.discordjavalib.handler.channel.WSHandlerChannelUpdate;
import me.xa5.discordjavalib.handler.guild.WSHandlerGuildCreate;
import me.xa5.discordjavalib.handler.guild.emotes.WSHandlerGuildEmotesUpdate;
import me.xa5.discordjavalib.handler.guild.member.WSHandlerGuildMemberAdd;
import me.xa5.discordjavalib.handler.guild.member.WSHandlerGuildMemberRemove;
import me.xa5.discordjavalib.handler.guild.member.WSHandlerGuildMemberUpdate;
import me.xa5.discordjavalib.handler.guild.member.WSHandlerGuildMembersChunk;
import me.xa5.discordjavalib.handler.guild.role.WSHandlerGuildRoleCreate;
import me.xa5.discordjavalib.handler.guild.role.WSHandlerGuildRoleDelete;
import me.xa5.discordjavalib.handler.guild.role.WSHandlerGuildRoleUpdate;
import me.xa5.discordjavalib.handler.message.WSHandlerMessageCreate;
import me.xa5.discordjavalib.handler.message.WSHandlerMessageDelete;
import me.xa5.discordjavalib.handler.message.WSHandlerMessageReactionAdd;
import me.xa5.discordjavalib.handler.message.WSHandlerMessageUpdate;
import me.xa5.discordjavalib.util.JsonFactory;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WSClient {
    private WebSocket client;
    private DiscordApiImpl api;
    private DJLHttp http;

    private String wsUrl;
    private long sequence = -1;
    private boolean connected;
    private long heartbeatSendTime;
    private Map<String, WSEventHandler> eventHandlers = new HashMap<>();
    private String sessionId;
    private String[] ignoredEvents = {"presences_replace"}; // discord sends this to bots for no apparent reason /shrug

    public WSClient(DiscordApiImpl api) {
        this.api = api;

        registerWSHandler(new WSHandlerReady(api));
        registerWSHandler(new WSHandlerGuildCreate(api));
        registerWSHandler(new WSHandlerPresenceUpdate(api));

        registerWSHandler(new WSHandlerChannelCreate(api));
        registerWSHandler(new WSHandlerChannelDelete(api));
        registerWSHandler(new WSHandlerChannelUpdate(api));

        registerWSHandler(new WSHandlerTypingStart(api));
        registerWSHandler(new WSHandlerMessageDelete(api));
        registerWSHandler(new WSHandlerMessageCreate(api));
        registerWSHandler(new WSHandlerMessageUpdate(api));
        registerWSHandler(new WSHandlerMessageReactionAdd(api));

        registerWSHandler(new WSHandlerGuildRoleCreate(api));
        registerWSHandler(new WSHandlerGuildRoleDelete(api));
        registerWSHandler(new WSHandlerGuildRoleUpdate(api));

        registerWSHandler(new WSHandlerGuildMemberAdd(api));
        registerWSHandler(new WSHandlerGuildMembersChunk(api));
        registerWSHandler(new WSHandlerGuildMemberUpdate(api));
        registerWSHandler(new WSHandlerGuildMemberRemove(api));

        registerWSHandler(new WSHandlerGuildEmotesUpdate(api));
    }

    private void registerWSHandler(WSEventHandler handler) {
        eventHandlers.put(handler.getInternalEventName().toLowerCase(), handler);
        System.out.println("Registered handler " + handler.getInternalEventName().toUpperCase());
    }

    public void connect() throws IOException, WebSocketException {
        http = new DJLHttp(api);

        client = api.getWebSocketFactory().createSocket(getWebSocketUrl());
        client.addListener(new WSListener(this, api));
        client.addHeader("Authorization", "Bot " + api.getToken());
        client.connect();
    }

    public DiscordApiImpl getApi() {
        return api;
    }

    private String getWebSocketUrl() throws IOException {
        if (this.wsUrl == null) {
            Response response = http.getBlocking(DiscordEndpoint.GET_GATEWAY);
            if (response.body() == null)
                throw new RuntimeException("Response body null for " + DiscordEndpoint.GET_GATEWAY.getUrl() + "!");
            JsonValue json = Json.parse(response.body().string());
            wsUrl = json.asObject().get("url").asString() + "/?v=" + DJLConstants.API_VERSION + "&encoding=json";
        }
        return this.wsUrl;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public void handleEvent(String eventType, JsonObject data) {
        api.getLogger().debug("Received WS event (" + eventType + ").");

        // Filter out events that we don't need
        for (String ignoredEvent : ignoredEvents) if (eventType.equalsIgnoreCase(ignoredEvent)) return;

        WSEventHandler wsEventHandler = eventHandlers.get(eventType.toLowerCase());
        if (wsEventHandler == null) {
            api.getLogger().warn("No handler registered for event " + eventType.toUpperCase());
            api.getLogger().warn("^ DATA:: " + data.toString(WriterConfig.PRETTY_PRINT));
            return;
        }

        wsEventHandler.handle(this, data);
    }

    public void onHello(JsonObject data) {
        identify();
        long interval = data.get("heartbeat_interval").asLong();

        Thread thread = new Thread(DJLConstants.NAME + " Heartbeat-Thread") {
            @Override
            public void run() {
                try {
                    while (connected) {
                        sendHeartbeat();
                        Thread.sleep(interval);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private void identify() {
        JsonObject data = Json.object()
                .set("token", api.getToken())
                .set("compress", true)
                .set("properties", Json.object()
                        .set("$os", System.getProperty("os.name"))
                        .set("$device", DJLConstants.NAME)
                        .set("$browser", DJLConstants.NAME))
                .set("presence", Json.object()
                        .set("status", "dnd")
                        .set("afk", false));
        if (api.getGame() != null)
            data.set("game", JsonFactory.from(api.getGame()));


        send(JsonFactory.newPayload(2, data));
        api.getLogger().debug("Sent IDENTIFY(op:2).");
    }

    public void send(JsonObject payload) {
        client.sendText(payload.toString());
        api.getLogger().trace("Sent WS message: " + payload.toString().replace(api.getToken(), "{TOKEN REDACTED}"));
    }

    void sendHeartbeat() {
        send(JsonFactory.newPayload(1, sequence));
        heartbeatSendTime = System.currentTimeMillis();
        api.getLogger().trace("Sent heartbeat.");
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public long heartbeatSendTime() {
        return heartbeatSendTime;
    }

    public void setSessionId(String id) {
        this.sessionId = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void sendGuildSync(String id) {
        api.getLogger().debug("Sending guild sync for " + id + '.');
        send(JsonFactory.newPayload(8, Json.object()
                .add("guild_id", id)
                .add("query", "")
                .add("limit", 0)));
    }
}