package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerReady extends WSEventHandler {
    public WSHandlerReady(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "ready";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
//        System.out.println("HANDLING READY::");
//        System.out.println(data.toString(WriterConfig.PRETTY_PRINT));
//
        JsonObject user = data.get("user").asObject();
        client.getApi().setBotAccount(JsonFactory.botFromJson(user, getApi()));
        client.setSessionId(data.get("session_id").asString());

        JsonArray guilds = data.get("guilds").asArray();
        guilds.forEach(obj -> {
            JsonObject guildObj = obj.asObject();
            GuildImpl guild = new GuildImpl(getApi(), guildObj.get("unavailable").asBoolean(), guildObj.get("id").asString());

            client.getApi().getGuildMap().put(guild.getId(), guild);
        });
    }
}