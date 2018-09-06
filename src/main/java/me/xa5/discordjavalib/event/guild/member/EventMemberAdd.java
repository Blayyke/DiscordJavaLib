package me.xa5.discordjavalib.event.guild.member;

import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.MemberImpl;
import me.xa5.discordjavalib.event.Event;

public class EventMemberAdd extends MemberEvent {
    public EventMemberAdd(GuildImpl guild, MemberImpl member) {
        super(guild.getApi(), member);
    }
}
