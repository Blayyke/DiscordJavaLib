package me.xa5.discordjavalib.event.guild.category;

import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.event.guild.GuildEvent;

public abstract class CategoryEvent extends GuildEvent {
    private final ChannelCategory category;

    public CategoryEvent(DiscordApi api, ChannelCategory category) {
        super(api, category.getGuild());
        this.category = category;
    }
}