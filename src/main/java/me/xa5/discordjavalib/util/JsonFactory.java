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

    public static MemberImpl memberFromJson(DiscordApi api, JsonObject obj, GuildImpl guild) {
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
        return new MemberImpl(api, joinDate, roles, user, isDeafened, isMuted, nickname, guild);
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

        User author = message.get("author") == null || message.get("author").isNull() ? null : api.getUser(message.get("author").asObject().get("id").asString());
        Guild guild = channel.getGuild();
        LocalDateTime editTimestamp = message.get("edit_timestamp") == null || message.get("edit_timestamp").isNull() ? null : DJLUtil.parseDate(message.get("edit_timestamp").asString());
        LocalDateTime timestamp = message.get("timestamp") == null || message.get("timestamp").isNull() ? null : DJLUtil.parseDate(message.get("timestamp").asString());
        String webhookId = message.get("webhook_id") == null || message.get("webhook_id").isNull() ? null : message.get("webhook_id").asString();
        String content = message.get("content") == null || message.get("content").isNull() ? null : message.get("content").asString();
        boolean mentionsEveryone = message.get("mention_everyone") != null && !message.get("mention_everyone").isNull() && message.get("mention_everyone").asBoolean();
        boolean pinned = message.get("pinned") != null && !message.get("pinned").isNull() && message.get("pinned").asBoolean();
        boolean tts = message.get("tts") != null && !message.get("tts").isNull() && message.get("tts").asBoolean();

        List<User> mentionedUsers = new ArrayList<>();
        if (message.get("mentions") != null && !message.get("mentions").isNull()) {
            JsonArray userArray = message.get("mentions").asArray();
            userArray.forEach(value -> {
                JsonObject obj = value.asObject();
                mentionedUsers.add(api.getUser(obj.get("id").asString()));
            });
        }

        List<Role> mentionedRoles = new ArrayList<>();
        if (message.get("mention_roles") != null && !message.get("mention_roles").isNull()) {
            JsonArray rolesArray = message.get("mention_roles").asArray();
            rolesArray.forEach(value -> {
                JsonObject obj = value.asObject();
                mentionedRoles.add(api.getRole(obj.get("id").asString()));
            });
        }

        List<Attachment> attachments = new ArrayList<>();
        if (message.get("attachments") != null && !message.get("attachments").isNull()) {
            JsonArray attachmentArray = message.get("attachments").asArray();
            attachmentArray.forEach(value -> {
                JsonObject obj = value.asObject();
                attachments.add(attachmentFromJson(api, obj));
            });
        }

        List<Embed> embeds = new ArrayList<>();
        if (message.get("embeds") != null && !message.get("embeds").isNull()) {
            JsonArray embedArray = message.get("embeds").asArray();
            embedArray.forEach(value -> {
                JsonObject obj = value.asObject();
                embeds.add(embedFromJson(obj));
            });
        }

        return new MessageImpl(api, id, channel, author, guild, editTimestamp, timestamp, webhookId, content, mentionsEveryone, pinned, tts, mentionedUsers, mentionedRoles, attachments, embeds);
    }

    public static EmbedImpl embedFromJson(JsonObject obj) {
        List<Embed.EmbedField> fields = new ArrayList<>();
        if (obj.get("fields") != null && !obj.get("fields").isNull()) {
            JsonArray fieldArray = obj.get("fields").asArray();
            fieldArray.forEach(value -> {
                JsonObject field = value.asObject();
                fields.add(new Embed.EmbedField(field.get("inline").asBoolean(), field.get("name").asString(), field.get("value").asString()));
            });
        }

        String title = obj.get("title") == null ? null : obj.get("title").asString();
        String description = obj.get("description") == null ? null : obj.get("description").asString();
        Embed.EmbedFooter footer = null;
        if (obj.get("footer") != null)
            footer = embedFooterFromJson(obj.get("footer").asObject());
        Embed.EmbedImage image = null;
        if (obj.get("image") != null)
            image = embedImageFromJson(obj.get("footer").asObject());
        Embed.EmbedImage thumbnail = null;
        if (obj.get("thumbnail") != null)
            thumbnail = embedImageFromJson(obj.get("thumbnail").asObject());

        return new EmbedImpl(fields, title, description, footer, image, thumbnail);
    }

    private static Embed.EmbedImage embedImageFromJson(JsonObject footer) {
        String url = footer.get("url").asString();
        int height = footer.get("height").asInt();
        int width = footer.get("width").asInt();
        String proxyUrl = footer.get("proxy_url") == null || footer.get("proxy_url").isNull() ? null : footer.get("proxy_url").asString();
        return new Embed.EmbedImage(url, height, width, proxyUrl);
    }

    private static Embed.EmbedFooter embedFooterFromJson(JsonObject footer) {
        String text = footer.get("text") == null || footer.get("text").isNull() ? null : footer.get("text").asString();
        String iconUrl = footer.get("icon_url") == null || footer.get("icon_url").isNull() ? null : footer.get("icon_url").asString();
        String proxyIconUrl = footer.get("proxy_icon_url") == null || footer.get("proxy_icon_url").isNull() ? null : footer.get("proxy_icon_url").asString();
        return new Embed.EmbedFooter(text, iconUrl, proxyIconUrl);
    }

    public static AttachmentImpl attachmentFromJson(DiscordApi api, JsonObject attachment) {
        String proxyUrl = attachment.get("proxy_url").asString();
        String fileName = attachment.get("filename").asString();
        String url = attachment.get("url").asString();
        String id = attachment.get("id").asString();
        int height = attachment.get("height") == null ? -1 : attachment.get("height").asInt();
        int width = attachment.get("width") == null ? -1 : attachment.get("width").asInt();
        int size = attachment.get("size").asInt();

        return new AttachmentImpl(api, proxyUrl, fileName, url, id, size, height, width);
    }

    public static ReactionImpl reactionFromJson(JsonObject data) {
        return new ReactionImpl(data.get("animated").asBoolean(), data.get("name").asString(), data.get("id").isNull()? null:data.get("id").asString());
    }

    public static JsonObject newPayload(int opCode, JsonObject data) {
        return Json.object()
                .set("op", opCode)
                .set("d", data);
    }

    public static JsonObject newPayload(int opCode, long data) {
        return Json.object()
                .set("op", opCode)
                .set("d", data);
    }
}