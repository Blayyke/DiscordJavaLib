package me.xa5.discordjavalib.handler.channel;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.TextChannel;
import me.xa5.discordjavalib.entities.VoiceChannel;
import me.xa5.discordjavalib.entities.impl.ChannelCategoryImpl;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.entities.impl.TextChannelImpl;
import me.xa5.discordjavalib.entities.impl.VoiceChannelImpl;
import me.xa5.discordjavalib.enums.ChannelType;
import me.xa5.discordjavalib.event.guild.category.EventChannelCategoryUpdate;
import me.xa5.discordjavalib.event.guild.channel.EventTextChannelUpdate;
import me.xa5.discordjavalib.event.guild.channel.EventVoiceChannelUpdate;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerChannelUpdate extends WSEventHandler {
    public WSHandlerChannelUpdate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "channel_update";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        GuildImpl guild = (GuildImpl) client.getApi().getGuild(data.get("guild_id").asString());

        ChannelType type = ChannelType.fromKey(data.get("type").asInt());
        switch (type) {
            case VOICE:
                VoiceChannelImpl newVoiceChannel = JsonFactory.voiceChannelFromJson(getApi(), data, guild);
                VoiceChannel oldVoiceChannel = guild.getVoiceChannel(newVoiceChannel.getId());
                guild.getVoiceChannelMap().put(newVoiceChannel.getId(), newVoiceChannel);

                getApi().dispatchEvent(new EventVoiceChannelUpdate(getApi(), newVoiceChannel, oldVoiceChannel));
                break;
            case TEXT:
                TextChannelImpl newTextChannel = JsonFactory.textChannelFromJson(client.getApi(), data, guild);
                TextChannel oldTextChannel = guild.getTextChannel(newTextChannel.getId());
                guild.getTextChannelMap().put(newTextChannel.getId(), newTextChannel);

                getApi().dispatchEvent(new EventTextChannelUpdate(getApi(), newTextChannel, oldTextChannel));
                break;
            case CATEGORY:
                ChannelCategoryImpl newCategory = JsonFactory.categoryFromJson(client.getApi(), guild, data);
                TextChannel oldCategory = guild.getTextChannel(newCategory.getId());
                guild.getChannelCategoryMap().put(newCategory.getId(), newCategory);

                getApi().dispatchEvent(new EventChannelCategoryUpdate(getApi(), newCategory, oldCategory));
                break;
            default:
                throw new RuntimeException("Unknown ChannelType for CHANNEL_CREATE: " + type);
        }
    }
}