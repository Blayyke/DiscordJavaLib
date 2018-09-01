package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.VoiceChannel;

public class EventVoiceChannelDelete extends VoiceChannelEvent {
    public EventVoiceChannelDelete(DiscordApi api, VoiceChannel channel) {
        super(api, channel);
    }
}