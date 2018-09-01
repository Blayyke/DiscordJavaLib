package me.xa5.discordjavalib.handler.channel;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.ChannelCategoryImpl;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.TextChannelImpl;
import me.xa5.discordjavalib.entities.impl.VoiceChannelImpl;
import me.xa5.discordjavalib.enums.ChannelType;
import me.xa5.discordjavalib.event.guild.category.EventChannelCategoryCreate;
import me.xa5.discordjavalib.event.guild.channel.EventTextChannelCreate;
import me.xa5.discordjavalib.event.guild.channel.EventVoiceChannelCreate;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerChannelCreate extends WSEventHandler {
    public WSHandlerChannelCreate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "channel_create";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());

        ChannelType type = ChannelType.fromKey(data.get("type").asInt());
        switch (type) {
            case VOICE:
                VoiceChannelImpl voiceChannel = JsonFactory.voiceChannelFromJson(client.getApi(), data, guild);
                guild.getVoiceChannelMap().put(voiceChannel.getId(), voiceChannel);
                getApi().dispatchEvent(new EventVoiceChannelCreate(getApi(), voiceChannel));
                break;
            case TEXT:
                TextChannelImpl textChannel = JsonFactory.textChannelFromJson(client.getApi(), data, guild);
                guild.getTextChannelMap().put(textChannel.getId(), textChannel);
                getApi().dispatchEvent(new EventTextChannelCreate(getApi(), textChannel));
                break;
            case CATEGORY:
                ChannelCategoryImpl category = JsonFactory.categoryFromJson(client.getApi(), guild, data);
                guild.getChannelCategoryMap().put(category.getId(), category);
                getApi().dispatchEvent(new EventChannelCategoryCreate(getApi(), category));
                break;
            default:
                throw new RuntimeException("Unknown ChannelType for CHANNEL_CREATE: " + type);
        }
    }
}