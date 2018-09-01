package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.TextChannel;

public class TextChannelImpl implements TextChannel {
    private String topic;
    private String lastMessageId;
    private int position;
    private ChannelCategory parentCategory;
    private String id;
    private String name;
    private DiscordApi api;
    private Guild guild;

    public TextChannelImpl(DiscordApi api, String lastMessageId, int position, ChannelCategory parentCategory, String id, String name, String topic, Guild guild) {
        this.api = api;
        this.topic = topic;
        this.lastMessageId = lastMessageId;
        this.position = position;
        this.parentCategory = parentCategory;
        this.id = id;
        this.name = name;
        this.guild = guild;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getLastMessageId() {
        return lastMessageId;
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
    public DiscordApi getApi() {
        return api;
    }
}