package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Role;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.MemberImpl;

import java.util.ArrayList;
import java.util.List;

public class WSHandlerGuildMemberUpdate extends WSEventHandler {
    public WSHandlerGuildMemberUpdate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_member_update";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        MemberImpl member = (MemberImpl) guild.getMember(data.get("user").asObject().get("id").asString());

        String oldNick = member.getNickname();
        String nick = data.get("nick").isNull() ? null : data.get("nick").asString();
        member.setNickname(nick);

        List<Role> oldRoles = new ArrayList<>(member.getRoles());
        List<Role> roles = new ArrayList<>();
        data.get("roles").asArray().forEach(value -> {
            roles.add(guild.getRole(value.asString()));
        });
        member.setRoles(roles);

        //todo dispatch event; provide old&new data
    }
}