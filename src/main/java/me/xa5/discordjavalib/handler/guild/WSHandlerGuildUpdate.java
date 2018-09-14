package me.xa5.discordjavalib.handler.guild;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.ExplicitContentFilterLevel;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.Icon;
import me.xa5.discordjavalib.enums.MFALevel;
import me.xa5.discordjavalib.enums.MessageNotificationLevel;
import me.xa5.discordjavalib.enums.VerificationLevel;
import me.xa5.discordjavalib.enums.VoiceRegion;
import me.xa5.discordjavalib.event.guild.EventGuildUpdate;
import me.xa5.discordjavalib.handler.WSEventHandler;

public class WSHandlerGuildUpdate extends WSEventHandler {
    public WSHandlerGuildUpdate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "guild_update";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        Guild guild = getApi().getGuild(data.get("guild_id").asString());

        boolean newWidgetEnabled = data.get("widget_enabled").asBoolean();
        String newWidgetChannelId = data.get("widget_channel_id").isNull() ? null : data.get("widget_channel_id").asString();
        VerificationLevel newVerificationLevel = VerificationLevel.fromKey(data.get("verification_level").asInt());
        String newSystemChannelId = data.get("system_channel_id").isNull() ? null : data.get("system_channel_id").asString();
        Icon.GuildSplash newSplash = data.get("splash").isNull() ? null : new Icon.GuildSplash(data.get("splash").asString(), guild.getId());
        VoiceRegion newRegion = VoiceRegion.fromKey(data.get("region").asString());
        String newOwnerId = data.get("owner_id").asString();
        String newName = data.get("name").asString();
        MFALevel newMfaLevel = MFALevel.fromKey(data.get("mfa_level").asInt());
        Icon.GuildIcon newIcon = data.get("icon").isNull() ? null : new Icon.GuildIcon(data.get("icon").asString(), guild.getId());
        ExplicitContentFilterLevel newExplicitContentFilterLevel = ExplicitContentFilterLevel.fromKey(data.get("explicit_content_filter").asInt());
        MessageNotificationLevel newDefaultMessageNotificationLevel = MessageNotificationLevel.fromKey(data.get("default_message_notifications").asInt());
        int newAfkTimeout = data.get("afk_timeout").asInt();
        String newAfkChannelId = data.get("afk_channel_id").asString();

        getApi().dispatchEvent(new EventGuildUpdate(guild, newWidgetEnabled, newWidgetChannelId, newVerificationLevel, newSystemChannelId, newSplash, newRegion,
                newOwnerId, newName, newMfaLevel, newIcon, newExplicitContentFilterLevel, newDefaultMessageNotificationLevel, newAfkTimeout, newAfkChannelId));
    }
}