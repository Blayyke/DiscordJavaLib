package me.xa5.discordjavalib.event.guild.role;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Role;

public class EventRoleDelete extends RoleEvent {
    public EventRoleDelete(DiscordApi api, Role role) {
        super(api, role);
    }
}