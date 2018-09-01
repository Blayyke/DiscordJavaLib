package me.xa5.discordjavalib.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.entities.*;
import me.xa5.discordjavalib.entities.impl.*;
import me.xa5.discordjavalib.enums.GameType;
import me.xa5.discordjavalib.enums.OnlineStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JsonFactory {
    public static JsonObject from(Game game) {
        if (game == null) return Json.object();

        JsonObject obj = Json.object()
                .set("name", game.getName())
                .set("type", game.getType().getKey());
        if (game.getUrl() != null)
            obj.set("url", game.getUrl());
        return obj;
    }

    public static BotUser botFromJson(JsonObject user, DiscordApi api) {
        boolean verified = user.get("verified").asBoolean();
        String name = user.get("username").asString();
        boolean mfaEnabled = user.get("mfa_enabled").asBoolean();
        String id = user.get("id").asString();
        String discriminator = user.get("discriminator").asString();

        return new BotUserImpl(api, name, mfaEnabled, id, discriminator, verified,
                user.get("avatar") == null || user.get("avatar").isNull() ? null : new Icon.UserIcon(user.get("avatar").asString(), id)
        );
    }

    public static EmoteImpl emoteFromJson(DiscordApi api, JsonObject emote) {
        return new EmoteImpl(
                api, emote.get("animated").asBoolean(), emote.get("managed").asBoolean(), emote.get("id").asString(), emote.get("name").asString(), emote.get("require_colons").asBoolean()
        );
    }

    public static RoleImpl roleFromJson(DiscordApi api, Guild guild, JsonObject role) {
        return new RoleImpl(
                api, role.get("name").asString(), DJLUtil.parseColor(role.get("color").asInt()), role.get("position").asInt(), role.get("permissions").asLong(), role.get("mentionable").asBoolean(), role.get("managed").asBoolean(), role.get("hoist").asBoolean(), role.get("id").asString(),
                guild);
    }

    public static ChannelCategoryImpl categoryFromJson(DiscordApi api, Guild guild, JsonObject category) {
        return new ChannelCategoryImpl(
                api, guild, category.get("id").asString(), category.get("name").asString(), category.get("position").asInt()
        );
    }

    public static TextChannelImpl textChannelFromJson(DiscordApi api, JsonObject channel, GuildImpl guild) {
        ChannelCategory parent = channel.get("parent_id").isNull() ? null : guild.getChannelCategory(channel.get("parent_id").asString());
        return new TextChannelImpl(
                api, channel.get("last_message_id").isNull() ? null : channel.get("last_message_id").asString(), channel.get("position").asInt(), parent,
                channel.get("id").asString(), channel.get("name").asString(), channel.get("topic").isNull() ? null : channel.get("topic").asString(), guild
        );
    }

    public static VoiceChannelImpl voiceChannelFromJson(DiscordApi api, JsonObject channel, GuildImpl guild) {
        ChannelCategory parent = channel.get("parent_id").isNull() ? null : guild.getChannelCategory(channel.get("parent_id").asString());
        return new VoiceChannelImpl(
                api, parent, channel.get("id").asString(), channel.get("name").asString(), channel.get("bitrate").asInt(), channel.get("user_limit").asInt(),
                channel.get("position").asInt(), guild
        );
    }

    public static MemberImpl memberFromJson(GuildImpl guild, JsonObject obj) {
        LocalDateTime joinDate = DJLUtil.parseDate(obj.get("joined_at").asString());
        boolean isDeafened = obj.get("deaf").asBoolean();
        boolean isMuted = obj.get("mute").asBoolean();
        String nickname = obj.get("nick") == null || obj.get("nick").isNull() ? null : obj.get("nick").asString();

        JsonArray roleArray = obj.get("roles").asArray();
        List<Role> roles = new ArrayList<>();
        roleArray.forEach(roleObj -> {
            String roleId = roleObj.asString();
            roles.add(guild.getRole(roleId));
        });

        JsonObject userObj = obj.get("user").asObject();
        User user = ((DiscordApiImpl) guild
                .getApi())
                .getOrCreateUser(userObj);

        return new MemberImpl(guild, joinDate, roles, user, isDeafened, isMuted, nickname);
    }

    public static UserImpl userFromJson(DiscordApi api, JsonObject userObj) {
        return new UserImpl(api,
                userObj.get("id").asString(),
                userObj.get("discriminator").asString(),
                userObj.get("bot") != null && userObj.get("bot").asBoolean(),
                userObj.get("username").asString(),
                userObj.get("avatar") == null || userObj.get("avatar").isNull() ? null : new Icon.UserIcon(userObj.get("avatar").asString(), userObj.get("id").asString()));
    }

    public static Game gameFromJson(DiscordApi api, JsonObject game) {
        return new Game(
                GameType.fromKey(game.get("type").asInt()),
                game.get("name").asString());
    }

    public static VoiceStateImpl voiceStateFromJson(JsonObject state) {
        String userId = state.get("user_id").asString();
        boolean suppressed = state.get("suppress").asBoolean();
//            session_id
        boolean selfVideo = state.get("self_video").asBoolean();
        boolean selfMute = state.get("self_mute").asBoolean();
        boolean selfDeaf = state.get("self_deaf").asBoolean();

        boolean serverMuted = state.get("mute").asBoolean();
        boolean serverDeafened = state.get("deaf").asBoolean();

        return new VoiceStateImpl(userId, suppressed, selfVideo, selfMute, selfDeaf, serverMuted, serverDeafened);
    }

    public static Presence presenceFromJson(DiscordApi api, JsonObject presence) {
        String userId = presence.get("user").asObject().get("id").asString();
        OnlineStatus status = OnlineStatus.fromKey(presence.get("status").asString());
        Game game = null;
        if (!presence.get("game").isNull())
            game = JsonFactory.gameFromJson(api, presence.get("game").asObject());
        return new Presence(userId, status, game);
    }

    public static MessageImpl messageFromJson(DiscordApi api, JsonObject message) {
        //todo support DMs
        String id = message.get("id").asString();
        TextChannel channel = api.getTextChannel(message.get("channel_id").asString());
        User author = api.getUser(message.get("author").asObject().get("id").asString());
        Guild guild = channel.getGuild();
        LocalDateTime editTimestamp = message.get("edit_timestamp") == null || message.get("edit_timestamp").isNull() ? null : DJLUtil.parseDate(message.get("edit_timestamp").asString());
        LocalDateTime timestamp = DJLUtil.parseDate(message.get("timestamp").asString());
        String webhookId = message.get("webhook_id") == null || message.get("webhook_id").isNull() ? null : message.get("webhook_id").asString();
        String content = message.get("content").asString();
        boolean mentionsEveryone = message.get("mention_everyone").asBoolean();
        boolean pinned = message.get("pinned").asBoolean();
        boolean tts = message.get("tts").asBoolean();

        List<User> mentionedUsers = new ArrayList<>();
        JsonArray userArray = message.get("mentions").asArray();
        userArray.forEach(value -> {
            JsonObject obj = value.asObject();
            mentionedUsers.add(api.getUser(obj.get("id").asString()));
        });

        List<Role> mentionedRoles = new ArrayList<>();
        JsonArray rolesArray = message.get("mention_roles").asArray();
        rolesArray.forEach(value -> {
            JsonObject obj = value.asObject();
            mentionedRoles.add(api.getRole(obj.get("id").asString()));
        });

        return new MessageImpl(api, id, channel, author, guild, editTimestamp, timestamp, webhookId, content, mentionsEveryone, pinned, tts, mentionedUsers, mentionedRoles);
    }
}