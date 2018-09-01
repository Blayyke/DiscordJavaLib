package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;

public class TextChannelEvent extends ChannelEvent {
    private final TextChannel textChannel;

    public TextChannelEvent(DiscordApi api, TextChannel textChannel) {
        super(api, textChannel);
        this.textChannel = textChannel;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }
}