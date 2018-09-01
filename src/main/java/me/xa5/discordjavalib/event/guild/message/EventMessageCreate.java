package me.xa5.discordjavalib.event.guild.message;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.MessageImpl;
import me.xa5.discordjavalib.event.MessageEvent;

public class EventMessageCreate extends MessageEvent {
    public EventMessageCreate(DiscordApi api, MessageImpl message) {
        super(api, message);
    }
}