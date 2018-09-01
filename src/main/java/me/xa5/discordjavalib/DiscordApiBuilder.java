package me.xa5.discordjavalib;

import com.neovisionaries.ws.client.WebSocketFactory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Game;
import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import me.xa5.discordjavalib.event.EventListener;
import me.xa5.discordjavalib.util.Assert;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscordApiBuilder {
    private WebSocketFactory webSocketFactory = new WebSocketFactory();
    private List<EventListener> listeners = new ArrayList<>();
    private OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private String token = null;
    private Game game = null;

    /**
     * Build the discord api client with your provided configuration.
     *
     * @return The newly-created {@link DiscordApi}, ready for login.
     */
    public DiscordApi build() {
        Assert.notNull(this.httpClient, "httpClient");
        Assert.notNull(this.webSocketFactory, "webSocketFactory");
        Assert.notNull(listeners, "listeners");
        return new DiscordApiImpl(webSocketFactory, httpClient, token, game, listeners);
    }

    /**
     * Set the token of the bot you want to connect with. This is not optional.
     *
     * @param token The discord API token.
     * @return The {@link DiscordApiBuilder}
     */
    public DiscordApiBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Optionally provide your own {@link OkHttpClient} to use for interfacing with the discord REST API.
     *
     * @param httpClient Your custom {@link OkHttpClient} to use for discord HTTP requests.
     * @return The {@link DiscordApiBuilder}
     */
    public DiscordApiBuilder setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /**
     * Optionally provide your own {@link WebSocketFactory} to use for building the discord websocket client.
     *
     * @param webSocketFactory Your custom {@link WebSocketFactory} instance
     * @return The {@link DiscordApiBuilder}
     */
    public DiscordApiBuilder setWebSocketFactory(WebSocketFactory webSocketFactory) {
        this.webSocketFactory = webSocketFactory;
        return this;
    }

    /**
     * Optionally specify the game that the bot will have set upon startup.
     *
     * @param game The game to set
     * @return The {@link DiscordApiBuilder}
     */
    public DiscordApiBuilder setGame(Game game) {
        this.game = game;
        return this;
    }

    /**
     * Provide {@link EventListener}s for use in dispatching events.
     *
     * @param listeners Array of {@link EventListener} objects
     * @return The {@link DiscordApiBuilder}
     */
    public DiscordApiBuilder addListeners(EventListener... listeners) {
        Assert.noneNull(listeners, "listeners");
        Collections.addAll(this.listeners, listeners);
        return this;
    }

    /**
     * Provide {@link EventListener}s for use in dispatching events.
     *
     * @param listeners Array of {@link EventListener} objects
     * @return The {@link DiscordApiBuilder}
     */
    public DiscordApiBuilder addListeners(List<EventListener> listeners) {
        Assert.noneNull(listeners, "listeners");
        this.listeners.addAll(listeners);
        return this;
    }
}