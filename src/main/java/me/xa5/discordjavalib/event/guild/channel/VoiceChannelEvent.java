package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.VoiceChannel;

public class VoiceChannelEvent extends ChannelEvent {
    private VoiceChannel voiceChannel;

    public VoiceChannelEvent(DiscordApi api, VoiceChannel channel) {
        super(api, channel);
        this.voiceChannel = channel;
    }

    public VoiceChannel getVoiceChannel() {
        return voiceChannel;
    }
}