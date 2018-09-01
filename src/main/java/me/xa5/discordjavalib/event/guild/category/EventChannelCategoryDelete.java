package me.xa5.discordjavalib.event.guild.category;

import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;

public class EventChannelCategoryDelete extends CategoryEvent {
    public EventChannelCategoryDelete(DiscordApi api, ChannelCategory category) {
        super(api, category);
    }
}