package me.xa5.discordjavalib.event.guild.role;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Role;
import me.xa5.discordjavalib.event.guild.GuildEvent;

public abstract class RoleEvent extends GuildEvent {
    private final Role role;

    public RoleEvent(DiscordApi api, Role role) {
        super(api, role.getGuild());
        this.role = role;
    }

    public final Role getRole() {
        return role;
    }
}