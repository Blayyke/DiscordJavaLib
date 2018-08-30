package me.xa5.discordjavalib;

import com.neovisionaries.ws.client.WebSocketFactory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Game;
import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import okhttp3.OkHttpClient;

public class DiscordApiBuilder {
    private WebSocketFactory websocketFactory = new WebSocketFactory();
    private OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private String token = null;
    private Game game = null;

    public DiscordApi build() {
        if (token == null || token.isEmpty()) throw new RuntimeException("need to provide nonnull & non-empty token!");

        return new DiscordApiImpl(websocketFactory, httpClient, token, game);
    }

    public DiscordApiBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public DiscordApiBuilder setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public DiscordApiBuilder setWebsocketFactory(WebSocketFactory websocketFactory) {
        this.websocketFactory = websocketFactory;
        return this;
    }

    public DiscordApiBuilder setGame(Game game) {
        this.game = game;
        return this;
    }
}