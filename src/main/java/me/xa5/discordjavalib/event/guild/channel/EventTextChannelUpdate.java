package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;

public class EventTextChannelUpdate extends TextChannelEvent {
    private TextChannel oldTextChannel;

    public EventTextChannelUpdate(DiscordApi api, TextChannel newTextChannel, TextChannel oldTextChannel) {
        super(api, newTextChannel);
        this.oldTextChannel = oldTextChannel;
    }

    public TextChannel getOldTextChannel() {
        return oldTextChannel;
    }
}