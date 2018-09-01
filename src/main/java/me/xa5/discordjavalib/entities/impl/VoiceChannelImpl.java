package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.VoiceChannel;

public class VoiceChannelImpl implements VoiceChannel {
    private int position;
    private ChannelCategory parentCategory;
    private String id;
    private String name;
    private final int bitrate;
    private final int userLimit;
    private DiscordApi api;
    private Guild guild;

    public VoiceChannelImpl(DiscordApi api, ChannelCategory parentCategory, String id, String name, int bitrate, int userLimit, int position, Guild guild) {
        this.api = api;
        this.position = position;
        this.parentCategory = parentCategory;
        this.id = id;
        this.name = name;
        this.bitrate = bitrate;
        this.userLimit = userLimit;
        this.guild = guild;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public ChannelCategory getParentCategory() {
        return parentCategory;
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
    public int getBitrate() {
        return bitrate;
    }

    @Override
    public int getUserLimit() {
        return userLimit;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }
}