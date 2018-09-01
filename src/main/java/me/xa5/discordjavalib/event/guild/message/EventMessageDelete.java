package me.xa5.discordjavalib.event.guild.message;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.event.MessageEvent;

public class EventMessageDelete extends MessageEvent {
    private final TextChannel channel;
    private final String messageId;

    public EventMessageDelete(DiscordApi api, TextChannel channel, String messageId) {
        super(api, null);
        this.channel = channel;
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public TextChannel getChannel() {
        return channel;
    }
}