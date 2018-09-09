package me.xa5.discordjavalib.handler.guild;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.event.guild.EventGuildDelete;
import me.xa5.discordjavalib.handler.WSEventHandler;

public class WSHandlerGuildDelete extends WSEventHandler {
    public WSHandlerGuildDelete(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_delete";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        Guild guild = client.getApi().getGuild(data.get("id").asString());
        client.getApi().getGuildMap().remove(guild.getId());

        client.getApi().dispatchEvent(new EventGuildDelete(getApi(), guild));
    }
}