package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;

public class ChannelCategoryImpl implements ChannelCategory {
    private int position;
    private String id;
    private String name;
    private DiscordApi api;
    private Guild guild;

    public ChannelCategoryImpl(DiscordApi api, Guild guild, String id, String name, int position) {
        this.api = api;
        this.guild = guild;
        this.position = position;
        this.id = id;
        this.name = name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Guild getGuild() {
        return guild;
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
    public DiscordApi getApi() {
        return api;
    }
}