package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Icon;
import me.xa5.discordjavalib.entities.User;

public class UserImpl implements User {
    private String name;
    private String id;
    private String discriminator;
    private boolean bot;
    private Icon.UserIcon avatar;
    private DiscordApi api;

    public UserImpl(DiscordApi api, String id, String discriminator, boolean bot, String name, Icon.UserIcon avatar) {
        this.api = api;
        this.name = name;
        this.id = id;
        this.discriminator = discriminator;
        this.bot = bot;
        this.avatar = avatar;
    }

    @Override
    public String getDiscriminator() {
        return discriminator;
    }

    @Override
    public boolean isBot() {
        return bot;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String mention() {
        return "<@" + getId() + ">";
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }

    @Override
    public Icon.UserIcon getAvatar() {
        return avatar;
    }
}