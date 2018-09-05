package me.xa5.discordjavalib.handler.guild.emotes;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.EmoteImpl;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.event.guild.emote.EventEmotesUpdate;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

import java.util.HashMap;
import java.util.Map;

public class WSHandlerGuildEmotesUpdate extends WSEventHandler {
    public WSHandlerGuildEmotesUpdate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_emojis_update";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        JsonArray emoteArray = data.get("emojis").asArray();
        Map<String, EmoteImpl> emoteMap = new HashMap<>();
        emoteArray.forEach(jsonValue -> {
            JsonObject emoteObj = jsonValue.asObject();
            EmoteImpl emote = JsonFactory.emoteFromJson(client.getApi(), emoteObj);
            emoteMap.put(emote.getId(), emote);
        });

        client.getApi().dispatchEvent(new EventEmotesUpdate(guild, emoteMap));
        guild.getEmoteMap().clear();
        guild.getEmoteMap().putAll(emoteMap);
    }
}