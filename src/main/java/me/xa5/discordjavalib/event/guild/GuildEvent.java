package me.xa5.discordjavalib.event.guild;

import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.event.Event;

public abstract class GuildEvent extends Event {
    private final Guild guild;

    public GuildEvent(DiscordApi api, Guild guild) {
        super(api);
        this.guild = guild;
    }

    public final Guild getGuild() {
        return guild;
    }
}