package me.xa5.discordjavalib.handler.guild.member;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.MemberImpl;
import me.xa5.discordjavalib.event.guild.member.EventMemberAdd;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerGuildMemberAdd extends WSEventHandler {
    public WSHandlerGuildMemberAdd(DiscordApiImpl api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_member_add";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        MemberImpl member = JsonFactory.memberFromJson(getApi(), data, guild);
        guild.getMemberMap().put(member.getUser().getId(), member);
        client.getApi().dispatchEvent(new EventMemberAdd(guild, member));
    }
}