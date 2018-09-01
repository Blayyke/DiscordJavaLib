package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.Channel;
import me.xa5.discordjavalib.entities.DiscordApi;

public class EventVoiceChannelDelete extends VoiceChannelEvent {
    public EventVoiceChannelDelete(DiscordApi api, Channel channel) {
        super(api, channel);
    }
}