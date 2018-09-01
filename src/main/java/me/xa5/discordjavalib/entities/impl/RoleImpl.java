package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.Role;

import java.awt.*;

public class RoleImpl implements Role {
    private final DiscordApi api;
    private final String id;
    private String name;
    private Color color;
    private int position;
    private long rawPermissions;
    private boolean mentionable;
    private boolean managed;
    private boolean hoisted;
    private Guild guild;

    public RoleImpl(DiscordApi api, String name, Color color, int position, long rawPermissions, boolean mentionable, boolean managed, boolean hoisted, String id, Guild guild) {
        this.api = api;
        this.id = id;
        this.name = name;
        this.color = color;
        this.position = position;
        this.rawPermissions = rawPermissions;
        this.mentionable = mentionable;
        this.managed = managed;
        this.hoisted = hoisted;
        this.guild = guild;
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
    public Guild getGuild() {
        return guild;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setManaged(boolean managed) {
        this.managed = managed;
    }

    public void setHoisted(boolean hoisted) {
        this.hoisted = hoisted;
    }

    public void setMentionable(boolean mentionable) {
        this.mentionable = mentionable;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRawPermissions(long rawPermissions) {
        this.rawPermissions = rawPermissions;
    }
}