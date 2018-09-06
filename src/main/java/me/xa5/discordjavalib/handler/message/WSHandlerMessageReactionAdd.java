package me.xa5.discordjavalib.handler.message;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.impl.ReactionImpl;
import me.xa5.discordjavalib.event.guild.message.EventReactionAdd;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerMessageReactionAdd extends WSEventHandler {
    public WSHandlerMessageReactionAdd(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "message_reaction_add";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        Guild guild = client.getApi().getGuild(data.get("guild_id").asString());
        TextChannel channel = client.getApi().getTextChannel(data.get("channel_id").asString());
        String messageId = data.get("message_id").asString();

        ReactionImpl reaction = JsonFactory.reactionFromJson(data.get("emoji").asObject());
        client.getApi().dispatchEvent(new EventReactionAdd(guild, channel, messageId, reaction));
    }
}