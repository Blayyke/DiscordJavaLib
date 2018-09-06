package me.xa5.discordjavalib.entities.impl;

import com.eclipsesource.json.JsonObject;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import me.xa5.discordjavalib.DJLConstants;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.*;
import me.xa5.discordjavalib.event.Event;
import me.xa5.discordjavalib.event.EventListener;
import me.xa5.discordjavalib.util.JsonFactory;
import me.xa5.discordjavalib.util.Logger;
import okhttp3.OkHttpClient;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.*;

public class DiscordApiImpl implements DiscordApi {
    private final WSClient wsClient;
    private final Logger logger = Logger.create(DJLConstants.NAME);
    private WebSocketFactory websocketFactory;
    private OkHttpClient httpClient;
    private String token;
    private Game game;
    private BotUser botAccount;
    private Map<String, GuildImpl> guildMap = new HashMap<>();
    private Map<String, UserImpl> userMap = new HashMap<>();
    private List<EventListener> listeners;
    private boolean loggedIn = false;
    private long ping;

    public DiscordApiImpl(WebSocketFactory websocketFactory, OkHttpClient httpClient, String token, Game game, List<EventListener> listeners) {
        this.websocketFactory = websocketFactory;
        this.httpClient = httpClient;
        this.token = token;
        this.game = game;
        this.wsClient = new WSClient(this);
        this.listeners = listeners;
    }

    private void connect() throws IOException, WebSocketException {
        verifyToken();
        wsClient.connect();
    }

    public WebSocketFactory getWebSocketFactory() {
        return websocketFactory;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void invalidate() {
        guildMap.clear();
        userMap.clear();

        throw new NotImplementedException();
    }

    public void setPing(long ping) {
        this.ping = ping;
    }

    @Override
    public long getPing() {
        return ping;
    }

    @Override
    public Game getGame() {
        return game;
    }

    public void setBotAccount(BotUser botAccount) {
        this.botAccount = botAccount;
    }

    @Override
    public BotUser getBotAccount() {
        return botAccount;
    }

    public Map<String, GuildImpl> getGuildMap() {
        return guildMap;
    }

    @Override
    public List<Guild> getGuilds() {
        return Collections.unmodifiableList(new ArrayList<>(guildMap.values()));
    }

    public GuildImpl getOrCreateGuild(String id, boolean unavailable) {
        if (!guildMap.containsKey(id)) guildMap.put(id, new GuildImpl(this, unavailable, id));
        return guildMap.get(id);
    }

    public User getOrCreateUser(JsonObject userObj) {
        String id = userObj.get("id").asString();
        if (!userMap.containsKey(id)) userMap.put(id, JsonFactory.userFromJson(this, userObj));
        return userMap.get(id);
    }

    @Override
    public void login() throws IOException, WebSocketException {
        if (loggedIn) throw new IllegalStateException("Already connected to discord!");
        getLogger().info("Attempting login...");

        connect();
        loggedIn = true;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public Guild getGuild(String id) {
        return guildMap.get(id);
    }

    @Override
    public TextChannel getTextChannel(String id) {
        return getGuilds().stream().map(guild -> guild.getTextChannel(id)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Override
    public Role getRole(String id) {
        return getGuilds().stream().map(guild -> guild.getRole(id)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    @Override
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void dispatchEvent(Event event) {
        logger.trace("Dispatching event " + event.getClass().getSimpleName() + " to all registered listeners.");
        try {
            getListeners().forEach(listener -> listener.onEvent(event));
        } catch (Exception e) {
            logger.error("Uncaught exception during event handling: ");
            e.printStackTrace();
        }
    }

    @Override
    public List<EventListener> getListeners() {
        return listeners;
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

    private void verifyToken() {
        if (token == null || token.isEmpty()) throw new RuntimeException("need to provide nonnull & non-empty token!");
        //todo HTTP check with discord.
    }
}