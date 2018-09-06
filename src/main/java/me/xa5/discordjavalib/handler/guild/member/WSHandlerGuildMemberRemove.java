package me.xa5.discordjavalib.handler.guild.member;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.event.guild.member.EventMemberRemove;
import me.xa5.discordjavalib.handler.WSEventHandler;

public class WSHandlerGuildMemberRemove extends WSEventHandler {
    public WSHandlerGuildMemberRemove(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_member_remove";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        JsonObject user = data.get("user").asObject();
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        Member member = guild.getMember(user.get("id").asString());

        client.getApi().dispatchEvent(new EventMemberRemove(getApi(), member));

        guild.getMemberMap().remove(user.get("id").asString());
    }
}