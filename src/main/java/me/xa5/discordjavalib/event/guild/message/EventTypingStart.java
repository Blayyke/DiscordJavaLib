package me.xa5.discordjavalib.event.guild.message;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.event.guild.channel.TextChannelEvent;

public class EventTypingStart extends TextChannelEvent {
    private final Member member;
    private final long timestamp;

    public EventTypingStart(DiscordApi api, Member member, Guild guild, TextChannel channel, long timestamp) {
        super(api, channel);
        this.member = member;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Member getMember() {
        return member;
    }
}
