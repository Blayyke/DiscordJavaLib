package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.MemberImpl;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerGuildMembersChunk extends WSEventHandler {
    public WSHandlerGuildMembersChunk(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_members_chunk";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        JsonArray memberArray = data.get("members").asArray();

        memberArray.forEach(value -> {
            JsonObject memberObj = value.asObject();
            MemberImpl member = JsonFactory.memberFromJson(guild, memberObj);
            guild.getMemberMap().put(member.getUser().getId(), member);
        });
    }
}