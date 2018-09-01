package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.impl.TextChannelImpl;

public class EventTextChannelCreate extends ChannelEvent {
    private final TextChannelImpl textChannel;

    public EventTextChannelCreate(DiscordApi api, TextChannelImpl textChannel) {
        super(api, textChannel);
        this.textChannel = textChannel;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }
}