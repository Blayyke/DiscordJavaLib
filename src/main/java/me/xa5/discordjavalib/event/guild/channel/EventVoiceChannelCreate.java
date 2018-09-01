package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.VoiceChannel;
import me.xa5.discordjavalib.entities.impl.VoiceChannelImpl;

public class EventVoiceChannelCreate extends ChannelEvent {
    private final VoiceChannelImpl voiceChannel;

    public EventVoiceChannelCreate(DiscordApi api, VoiceChannelImpl voiceChannel) {
        super(api, voiceChannel);
        this.voiceChannel = voiceChannel;
    }

    public VoiceChannel getVoiceChannel() {
        return voiceChannel;
    }
}