package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.VoiceChannel;
import me.xa5.discordjavalib.entities.impl.VoiceChannelImpl;

public class EventVoiceChannelUpdate extends VoiceChannelEvent {
    private VoiceChannel oldVoiceChannel;

    public EventVoiceChannelUpdate(DiscordApi api, VoiceChannelImpl newVoiceChannel, VoiceChannel oldVoiceChannel) {
        super(api, newVoiceChannel);
        this.oldVoiceChannel = oldVoiceChannel;
    }

    public VoiceChannel getOldVoiceChannel() {
        return oldVoiceChannel;
    }
}