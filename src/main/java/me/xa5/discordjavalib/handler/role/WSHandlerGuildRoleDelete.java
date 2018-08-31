package me.xa5.discordjavalib.handler.role;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.handler.WSEventHandler;

public class WSHandlerGuildRoleDelete extends WSEventHandler {
    public WSHandlerGuildRoleDelete(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_role_delete";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        String roleId = data.get("role_id").asString();
        guild.getRoleMap().remove(roleId);

        //todo dispatch event
    }
}