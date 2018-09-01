package me.xa5.discordjavalib.event.guild.member;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.event.guild.GuildEvent;

public class MemberEvent extends GuildEvent {
    private final Member member;

    public MemberEvent(DiscordApi api, Member member) {
        super(api, member.getGuild());
        this.member = member;
    }

    public final Member getMember() {
        return member;
    }
}