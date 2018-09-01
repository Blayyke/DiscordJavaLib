package me.xa5.discordjavalib.event;

import me.xa5.discordjavalib.entities.DiscordApi;

public class ReadyEvent extends Event {
    public ReadyEvent(DiscordApi api) {
        super(api);
    }
}