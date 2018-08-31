package me.xa5.discordjavalib.handler.message;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.handler.WSEventHandler;

public class WSHandlerMessageDelete extends WSEventHandler {
    public WSHandlerMessageDelete(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "message_delete";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        Guild guild = client.getApi().getGuild(data.get("guild_id").asString());
        TextChannel channel = guild.getTextChannel(data.get("channel_id").asString());
        String messageId = data.get("id").asString();

        //todo dispatch event
    }
}