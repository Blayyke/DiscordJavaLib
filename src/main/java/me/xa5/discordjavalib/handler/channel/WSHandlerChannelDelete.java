package me.xa5.discordjavalib.handler.channel;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.ChannelCategory;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.VoiceChannel;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.enums.ChannelType;
import me.xa5.discordjavalib.event.guild.category.EventChannelCategoryDelete;
import me.xa5.discordjavalib.event.guild.channel.EventTextChannelDelete;
import me.xa5.discordjavalib.event.guild.channel.EventVoiceChannelDelete;
import me.xa5.discordjavalib.handler.WSEventHandler;

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
                getApi().dispatchEvent(new EventVoiceChannelDelete(getApi(), voiceChannel));
                break;
            case TEXT:
                TextChannel textChannel = guild.getTextChannel(channelId);
                guild.getTextChannelMap().remove(textChannel.getId());
                getApi().dispatchEvent(new EventTextChannelDelete(getApi(), textChannel));
                break;
            case CATEGORY:
                ChannelCategory category = guild.getChannelCategory(channelId);
                guild.getChannelCategoryMap().remove(category.getId());
                getApi().dispatchEvent(new EventChannelCategoryDelete(getApi(), category));
                break;
            default:
                throw new RuntimeException("Unknown ChannelType for CHANNEL_DELETE: " + type);
        }
    }
}