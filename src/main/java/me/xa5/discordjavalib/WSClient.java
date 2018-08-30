package me.xa5.discordjavalib;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.WriterConfig;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.handler.WSHandlerGuildCreate;
import me.xa5.discordjavalib.handler.WSHandlerReady;
import me.xa5.discordjavalib.util.JsonFactory;
import okhttp3.Response;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WSClient {
    private WebSocket client;
    private DiscordApiImpl api;
    private DJLHttp http;

    private String wsUrl;
    private long sequence;
    private boolean connected;
    private long heartbeatSendTime;
    private Map<String, WSEventHandler> eventHandlers = new HashMap<>();
    private String sessionId;

    public WSClient(DiscordApiImpl api) {
        this.api = api;

        registerWSHandler(new WSHandlerReady(api));
        registerWSHandler(new WSHandlerGuildCreate(api));
    }

    private void registerWSHandler(WSEventHandler handler) {
        eventHandlers.put(handler.getInternalEventName().toLowerCase(), handler);
        System.out.println("Registered handler " + handler.getInternalEventName().toUpperCase());
    }

    public void connect() throws IOException, WebSocketException {
        http = new DJLHttp(api);

        client = api.getWebsocketFactory().createSocket(getWebsocketUrl());
        client.addListener(new WSListener(this));
        client.addHeader("Authorization", "Bot " + api.getToken());
        client.connect();
    }

    public DiscordApiImpl getApi() {
        return api;
    }

    private String getWebsocketUrl() throws IOException {
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
        System.out.println("RECEIVED EVENT::");
        WSEventHandler wsEventHandler = eventHandlers.get(eventType.toLowerCase());
        if (wsEventHandler == null) {
            System.err.println("No handler registered for event " + eventType.toUpperCase());
            System.err.println("DATA:: " + data.toString(WriterConfig.PRETTY_PRINT));
            throw new NotImplementedException();
        }

        wsEventHandler.handle(this, data);
    }

    public void onHello(JsonObject data) {
        identify();
        long interval = data.get("heartbeat_interval").asLong();

        Thread thread = new Thread(DJLConstants.NAME + "-heartbeat-thread") {
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

    void identify() {
        try {
            JsonObject payload = Json.object()
                    .set("op", 2)
                    .set("d", Json.object()
                            .set("token", api.getToken())
                            .set("compress", true)
                            .set("properties", Json.object()
                                    .set("$os", System.getProperty("os.name"))
                                    .set("$device", DJLConstants.NAME)
                                    .set("$browser", DJLConstants.NAME))
                            .set("presence", Json.object()
                                    .set("status", "dnd")
                                    .set("afk", false)));

            if (api.getGame() != null)
                payload.set("game", JsonFactory.from(api.getGame()));

            send(payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Sent identify!");
    }

    private void send(JsonObject payload) {
        client.sendText(payload.toString());
//        System.out.println("Sent text " + payload.toString(WriterConfig.PRETTY_PRINT));
    }

    void sendHeartbeat() {
        send(Json.object().set("op", 1).set("d", sequence));
        heartbeatSendTime = System.currentTimeMillis();
        System.out.println("sent heartbeat!");
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
}