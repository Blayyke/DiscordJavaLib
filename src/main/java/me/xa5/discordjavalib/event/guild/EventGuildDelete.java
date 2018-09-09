package me.xa5.discordjavalib.event.guild;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;

public class EventGuildDelete extends GuildEvent {
    public EventGuildDelete(DiscordApi api, Guild guild) {
        super(api, guild);
    }
}