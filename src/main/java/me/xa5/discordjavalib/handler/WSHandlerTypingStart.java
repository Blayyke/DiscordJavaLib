package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.Member;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.event.guild.message.EventTypingStart;

public class WSHandlerTypingStart extends WSEventHandler {
    public WSHandlerTypingStart(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "typing_start";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        Guild guild = client.getApi().getGuild(data.get("guild_id").asString());
        TextChannel channel = guild.getTextChannel(data.get("channel_id").asString());
        Member member = guild.getMember(data.get("user_id").asString());
        long timestamp = data.get("timestamp").asLong();

        client.getApi().dispatchEvent(new EventTypingStart(getApi(), member, guild, channel, timestamp));
    }
}