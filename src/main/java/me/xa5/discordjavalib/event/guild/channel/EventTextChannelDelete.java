package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;

public class EventTextChannelDelete extends TextChannelEvent {
    public EventTextChannelDelete(DiscordApi api, TextChannel textChannel) {
        super(api, textChannel);
    }
}