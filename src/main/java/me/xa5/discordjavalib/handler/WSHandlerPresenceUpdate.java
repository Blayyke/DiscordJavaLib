package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Presence;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.event.guild.member.EventPresenceUpdate;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerPresenceUpdate extends WSEventHandler {
    public WSHandlerPresenceUpdate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "presence_update";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        Presence oldPresence = guild.getPresence(data.get("user").asObject().get("id").asString());
        Presence newPresence = JsonFactory.presenceFromJson(client.getApi(), data);
        guild.getPresenceMap().put(newPresence.getUserId(), newPresence);
        getApi().dispatchEvent(new EventPresenceUpdate(getApi(), guild, oldPresence));
    }
}