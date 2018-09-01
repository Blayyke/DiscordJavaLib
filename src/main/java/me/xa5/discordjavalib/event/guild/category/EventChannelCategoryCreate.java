package me.xa5.discordjavalib.event.guild.category;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.ChannelCategoryImpl;

public class EventChannelCategoryCreate extends CategoryEvent {
    public EventChannelCategoryCreate(DiscordApi api, ChannelCategoryImpl category) {
        super(api, category);
    }
}