package me.xa5.discordjavalib.handler.role;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.RoleImpl;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerGuildRoleUpdate extends WSEventHandler {
    public WSHandlerGuildRoleUpdate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_role_update";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        RoleImpl tempRole = JsonFactory.roleFromJson(client.getApi(), data.get("role").asObject());
        RoleImpl role = (RoleImpl) guild.getRole(tempRole.getId());

        // At this point `role` has the old data; use this for dispatching event.
        // `tempRole` has the new date ^

        role.setName(tempRole.getName());
        role.setPosition(tempRole.getPosition());
        role.setColor(tempRole.getColor());
        role.setRawPermissions(tempRole.getRawPermissions());
        role.setHoisted(tempRole.isHoisted());
        role.setManaged(tempRole.isManaged());
        role.setMentionable(tempRole.isMentionable());

        //todo dispatch event
    }
}