package me.xa5.discordjavalib.event;

import me.xa5.discordjavalib.entities.DiscordApi;

public class EventReady extends Event {
    public EventReady(DiscordApi api) {
        super(api);
    }
}