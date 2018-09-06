package me.xa5.discordjavalib.event.guild.message;

import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.Reaction;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.impl.ReactionImpl;
import me.xa5.discordjavalib.event.Event;

public class EventReactionAdd extends Event {
    private final Guild guild;
    private final TextChannel channel;
    private final String messageId;
    private final ReactionImpl reaction;

    public EventReactionAdd(Guild guild, TextChannel channel, String messageId, ReactionImpl reaction) {
        super(guild.getApi());
        this.guild = guild;
        this.channel = channel;
        this.messageId = messageId;
        this.reaction = reaction;
    }

    public Guild getGuild() {
        return guild;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public String getMessageId() {
        return messageId;
    }

    public TextChannel getChannel() {
        return channel;
    }
}