package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Role;

import java.awt.*;

public class RoleImpl implements Role {
    private String id;
    private String name;
    private Color color;
    private int position;
    private long rawPermissions;
    private boolean mentionable;
    private boolean managed;
    private boolean hoisted;
    private DiscordApi api;

    public RoleImpl(DiscordApi api, String name, Color color, int position, long rawPermissions, boolean mentionable, boolean managed, boolean hoisted, String id) {
        this.api = api;
        this.id = id;
        this.name = name;
        this.color = color;
        this.position = position;
        this.rawPermissions = rawPermissions;
        this.mentionable = mentionable;
        this.managed = managed;
        this.hoisted = hoisted;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String mention() {
        return "<@&" + getId() + ">";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public long getRawPermissions() {
        return rawPermissions;
    }

    @Override
    public boolean isMentionable() {
        return mentionable;
    }

    @Override
    public boolean isManaged() {
        return managed;
    }

    @Override
    public boolean isHoisted() {
        return hoisted;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }
}