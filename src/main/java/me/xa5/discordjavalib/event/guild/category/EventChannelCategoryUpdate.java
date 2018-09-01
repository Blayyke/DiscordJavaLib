package me.xa5.discordjavalib.event.guild.category;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.impl.ChannelCategoryImpl;

public class EventChannelCategoryUpdate extends CategoryEvent {
    private final TextChannel oldCategory;

    public EventChannelCategoryUpdate(DiscordApi api, ChannelCategoryImpl newCategory, TextChannel oldCategory) {
        super(api, newCategory);
        this.oldCategory = oldCategory;
    }

    public TextChannel getOldCategory() {
        return oldCategory;
    }
}