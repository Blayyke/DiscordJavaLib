package me.xa5.discordjavalib.entities;

import java.util.List;

public interface DiscordApi {
    String getToken();

    long getPing();

    Game getGame();

    BotUser getBotAccount();

    List<Guild> getGuilds();
}