package me.xa5.discordjavalib.event.guild.role;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.RoleImpl;

public class EventRoleCreate extends RoleEvent {
    public EventRoleCreate(DiscordApi api, RoleImpl role) {
        super(api, role);
    }
}