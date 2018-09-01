package me.xa5.discordjavalib.event;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Message;

public abstract class MessageEvent extends Event {
    private final Message message;

    public MessageEvent(DiscordApi api, Message message) {
        super(api);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}