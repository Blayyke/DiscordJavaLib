package me.xa5.discordjavalib.event.guild.member;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.event.guild.member.MemberEvent;

public class EventMemberRemove extends MemberEvent {
    public EventMemberRemove(DiscordApi api, Member member) {
        super(api, member);
    }
}