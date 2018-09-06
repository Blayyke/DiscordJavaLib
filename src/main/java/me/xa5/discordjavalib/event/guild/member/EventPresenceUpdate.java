package me.xa5.discordjavalib.event.guild.member;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.entities.Presence;

public class EventPresenceUpdate extends MemberEvent {
    private final Presence oldPresence;

    public EventPresenceUpdate(DiscordApi api, Member member, Presence oldPresence) {
        super(api, member);
        this.oldPresence = oldPresence;
    }

    public Presence getOldPresence() {
        return oldPresence;
    }

    public Presence getNewPresence() {
        return getGuild().getPresence(getMember());
    }
}