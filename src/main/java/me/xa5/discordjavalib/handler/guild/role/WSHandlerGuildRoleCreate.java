package me.xa5.discordjavalib.handler.guild.role;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.RoleImpl;
import me.xa5.discordjavalib.event.guild.role.EventRoleCreate;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerGuildRoleCreate extends WSEventHandler {
    public WSHandlerGuildRoleCreate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_role_create";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        RoleImpl role = JsonFactory.roleFromJson(client.getApi(), guild, data.get("role").asObject());
        guild.getRoleMap().put(role.getId(), role);

        getApi().dispatchEvent(new EventRoleCreate(getApi(), role));
    }
}