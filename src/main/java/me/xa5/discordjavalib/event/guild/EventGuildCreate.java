package me.xa5.discordjavalib.event.guild;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.GuildImpl;

public class EventGuildCreate extends GuildEvent {
    public EventGuildCreate(DiscordApi api, GuildImpl guild) {
        super(api, guild);
    }
}