package me.xa5.discordjavalib.event.guild.member;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.entities.Role;

import java.util.Collections;
import java.util.List;

public class EventMemberUpdate extends MemberEvent {
    private final List<Role> oldRoles;
    private final String oldNickname;

    public EventMemberUpdate(DiscordApi api, Member member, List<Role> oldRoles, String oldNickname) {
        super(api, member);
        this.oldRoles = oldRoles;
        this.oldNickname = oldNickname;
    }

    public List<Role> getOldRoles() {
        return Collections.unmodifiableList(oldRoles);
    }

    public String getOldNickname() {
        return oldNickname;
    }

    public List<Role> getNewRoles() {
        return getMember().getRoles();
    }

    public String getNewNickname() {
        return getMember().getNickname();
    }
}