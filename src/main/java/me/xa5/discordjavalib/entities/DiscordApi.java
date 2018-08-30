package me.xa5.discordjavalib.entities;

import com.neovisionaries.ws.client.WebSocketException;

import java.io.IOException;
import java.util.List;

public interface DiscordApi {
    String getToken();

    long getPing();

    Game getGame();

    BotUser getBotAccount();

    List<Guild> getGuilds();

    void login() throws IOException, WebSocketException;
}