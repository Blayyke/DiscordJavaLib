package me.xa5.discordjavalib.event.guild.role;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Role;
import me.xa5.discordjavalib.entities.impl.RoleImpl;

public class EventRoleUpdate extends RoleEvent {
    private final RoleImpl oldRole;

    public EventRoleUpdate(DiscordApi api, RoleImpl role, RoleImpl tempRole) {
        super(api, tempRole);
        this.oldRole = role;
    }

    public Role getOldRole() {
        return oldRole;
    }
}