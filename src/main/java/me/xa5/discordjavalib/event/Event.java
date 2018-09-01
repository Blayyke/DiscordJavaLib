package me.xa5.discordjavalib.event;

import me.xa5.discordjavalib.entities.DJLEntity;
import me.xa5.discordjavalib.entities.DiscordApi;

public abstract class Event implements DJLEntity {
    private final DiscordApi api;

    public Event(DiscordApi api) {
        this.api = api;
    }

    @Override
    public final DiscordApi getApi() {
        return api;
    }
}