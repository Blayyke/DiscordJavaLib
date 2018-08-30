package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Emote;

public class EmoteImpl implements Emote {
    private boolean managed;
    private boolean requiresColons;
    private boolean animated;
    private String id;
    private String name;
    private DiscordApi api;

    public EmoteImpl(DiscordApi api, boolean animated, boolean managed, String id, String name, boolean requiresColons) {
        this.api = api;
        this.requiresColons = requiresColons;
        this.animated = animated;
        this.managed = managed;
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean requiresColons() {
        return requiresColons;
    }

    @Override
    public boolean isManaged() {
        return managed;
    }

    @Override
    public boolean isAnimated() {
        return animated;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String mention() {
        return "<:" + getName() + ":" + getId() + ">";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }
}
