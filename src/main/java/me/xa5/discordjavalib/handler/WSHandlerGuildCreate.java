package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.*;
import me.xa5.discordjavalib.entities.impl.*;
import me.xa5.discordjavalib.enums.*;
import me.xa5.discordjavalib.util.DJLUtil;
import me.xa5.discordjavalib.util.JsonFactory;

import java.util.HashMap;
import java.util.Map;

public class WSHandlerGuildCreate extends WSEventHandler {
    public WSHandlerGuildCreate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_create";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        boolean unavailable = data.get("unavailable").asBoolean();
        String id = data.get("id").asString();
        GuildImpl guild = client.getApi().getOrCreateGuild(id, unavailable);
        guild.setUnavailable(unavailable);

        JsonArray channels = data.get("channels").asArray();
        channels.forEach(value -> {
            JsonObject obj = value.asObject();
            ChannelType type = ChannelType.fromKey(obj.get("type").asInt());
            switch (type) {
                case CATEGORY:
                    ChannelCategoryImpl category = JsonFactory.categoryFromJson(getApi(), obj);
                    guild.getChannelCategoryMap().put(category.getId(), category);
                    break;
                case TEXT:
                    TextChannelImpl textChannel = JsonFactory.textChannelFromJson(getApi(), obj, guild);
                    guild.getTextChannelMap().put(textChannel.getId(), textChannel);
                    break;
                case VOICE:
                    VoiceChannelImpl voiceChannel = JsonFactory.voiceChannelFromJson(getApi(), obj, guild);
                    guild.getVoiceChannelMap().put(voiceChannel.getId(), voiceChannel);
                    break;
            }
        });

        JsonArray emojis = data.get("emojis").asArray();
        emojis.forEach(value -> {
            EmoteImpl emote = JsonFactory.emoteFromJson(getApi(), value.asObject());
            guild.getEmoteMap().put(emote.getId(), emote);
        });

        JsonArray roles = data.get("roles").asArray();
        roles.forEach(value -> {
            RoleImpl role = JsonFactory.roleFromJson(getApi(), value.asObject());
            guild.getRoleMap().put(role.getId(), role);
        });

        guild.setSplash(data.get("splash") == null || data.get("splash").isNull() ? null : new Icon.GuildSplash(data.get("splash").asString(), id));
        guild.setIcon(data.get("icon") == null || data.get("icon").isNull() ? null : new Icon.GuildIcon(data.get("icon").asString(), id));
        guild.setAfkChannel(guild.getVoiceChannel(data.get("afk_channel_id").isNull() ? null : data.get("afk_channel_id").asString()));
        guild.setSystemChannel(guild.getTextChannel(data.get("system_channel_id").isNull() ? null : data.get("system_channel_id").asString()));

        guild.setVerificationLevel(VerificationLevel.fromKey(data.get("verification_level").asInt()));
        guild.setExplicitContentFilterLevel(ExplicitContentFilterLevel.fromKey(data.get("explicit_content_filter").asInt()));
        guild.setDefaultMessageNotificationLevel(MessageNotificationLevel.fromKey(data.get("default_message_notifications").asInt()));
        guild.setRegion(VoiceRegion.fromKey(data.get("region").asString()));
        guild.setMFALevel(MFALevel.fromKey(data.get("mfa_level").asInt()));

        guild.setAfkTimeout(data.get("afk_timeout").asInt());
        guild.setLarge(data.get("large").asBoolean());
        guild.setName(data.get("name").asString());
        guild.setJoinDate(DJLUtil.parseDate(data.get("joined_at").asString()));

        // Handle members

        JsonArray voiceStates = data.get("voice_states").asArray();
        Map<String, VoiceStateImpl> voiceStateMap = new HashMap<>();
        voiceStates.forEach(value -> {
            VoiceStateImpl voiceState = JsonFactory.voiceStateFromJson(value.asObject());
            voiceStateMap.put(voiceState.getUserId(), voiceState);
        });
        guild.setVoiceStateMap(voiceStateMap);

        JsonArray presences = data.get("presences").asArray();
        Map<String, Presence> presenceMap = new HashMap<>();
        presences.forEach(value -> {
            Presence presence = JsonFactory.presenceFromJson(getApi(), value.asObject());
            presenceMap.put(presence.getUserId(), presence);
        });
        guild.setPresenceMap(presenceMap);

        int memberCount = data.get("member_count").asInt();
        JsonArray members = data.get("members").asArray();
        if (memberCount > members.size()) {
            client.sendGuildSync(guild.getId());
            return;
        }

        members.forEach(value -> {
            JsonObject memberObj = value.asObject();
            MemberImpl member = JsonFactory.memberFromJson(guild, memberObj);
            guild.getMemberMap().put(member.getUser().getId(), member);
        });

        guild.setOwnerId(data.get("owner_id").asString());
    }
}