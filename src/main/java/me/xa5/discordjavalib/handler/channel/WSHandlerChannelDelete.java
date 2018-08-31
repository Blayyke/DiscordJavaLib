package me.xa5.discordjavalib.handler.channel;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.VoiceChannel;
import me.xa5.discordjavalib.entities.impl.ChannelCategoryImpl;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.TextChannelImpl;
import me.xa5.discordjavalib.entities.impl.VoiceChannelImpl;
import me.xa5.discordjavalib.enums.ChannelType;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerChannelDelete extends WSEventHandler {
    public WSHandlerChannelDelete(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "channel_delete";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());
        String channelId = data.get("id").asString();

        ChannelType type = ChannelType.fromKey(data.get("type").asInt());
        switch (type) {
            case VOICE:
                VoiceChannel voiceChannel = guild.getVoiceChannel(channelId);
                guild.getVoiceChannelMap().remove(voiceChannel.getId());
                //todo dispatch voicechanneldeleteevent
                break;
            case TEXT:
                TextChannel textChannel = guild.getTextChannel(channelId);
                guild.getTextChannelMap().remove(textChannel.getId(), textChannel);
                //todo dispatch textchanneldeleteevent
                break;
            case CATEGORY:
                ChannelCategory channelCategory = guild.getChannelCategory(channelId);
                guild.getChannelCategoryMap().remove(channelCategory.getId());
                //todo dispatch channelcategorydeleteevent
                break;
            default:
                throw new RuntimeException("Unknown ChannelType for CHANNEL_DELETE: " + type);
        }
    }
}