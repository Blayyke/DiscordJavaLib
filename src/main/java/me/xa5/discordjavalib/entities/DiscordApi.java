package me.xa5.discordjavalib.entities;

import com.neovisionaries.ws.client.WebSocketException;
import me.xa5.discordjavalib.event.Event;
import me.xa5.discordjavalib.event.EventListener;
import me.xa5.discordjavalib.util.Logger;

import java.io.IOException;
import java.util.List;

public interface DiscordApi {
    String getToken();

    long getPing();

    Game getGame();

    BotUser getBotAccount();

    List<Guild> getGuilds();

    void login() throws IOException, WebSocketException;

    Logger getLogger();

    TextChannel getTextChannel(String id);

    Guild getGuild(String id);

    List<EventListener> getListeners();

    User getUser(String id);

    Role getRole(String id);

    void addListener(EventListener listener);

    void dispatchEvent(Event event);
}