package me.xa5.discordjavalib.event.guild;

import me.xa5.discordjavalib.entities.ExplicitContentFilterLevel;
import me.xa5.discordjavalib.entities.Guild;
import me.xa5.discordjavalib.entities.Icon;
import me.xa5.discordjavalib.enums.MFALevel;
import me.xa5.discordjavalib.enums.MessageNotificationLevel;
import me.xa5.discordjavalib.enums.VerificationLevel;
import me.xa5.discordjavalib.enums.VoiceRegion;

public class EventGuildUpdate extends GuildEvent {
    private final boolean newWidgetEnabled;
    private final String newWidgetChannelId;
    private final VerificationLevel newVerificationLevel;
    private final String newSystemChannelId;
    private final Icon.GuildSplash newSplash;
    private final VoiceRegion newRegion;
    private final String newOwnerId;
    private final String newName;
    private final MFALevel newMfaLevel;
    private final Icon.GuildIcon newIcon;
    private final ExplicitContentFilterLevel newExplicitContentFilterLevel;
    private final MessageNotificationLevel newDefaultMessageNotificationLevel;
    private final int newAfkTimeout;
    private final String newAfkChannelId;

    public EventGuildUpdate(Guild guild, boolean newWidgetEnabled, String newWidgetChannelId, VerificationLevel newVerificationLevel, String newSystemChannelId, Icon.GuildSplash newSplash, VoiceRegion newRegion, String newOwnerId, String newName, MFALevel newMfaLevel, Icon.GuildIcon newIcon, ExplicitContentFilterLevel newExplicitContentFilterLevel, MessageNotificationLevel newDefaultMessageNotificationLevel, int newAfkTimeout, String newAfkChannelId) {
        super(guild.getApi(), guild);
        this.newWidgetEnabled = newWidgetEnabled;
        this.newWidgetChannelId = newWidgetChannelId;
        this.newVerificationLevel = newVerificationLevel;
        this.newSystemChannelId = newSystemChannelId;
        this.newSplash = newSplash;
        this.newRegion = newRegion;
        this.newOwnerId = newOwnerId;
        this.newName = newName;
        this.newMfaLevel = newMfaLevel;
        this.newIcon = newIcon;
        this.newExplicitContentFilterLevel = newExplicitContentFilterLevel;
        this.newDefaultMessageNotificationLevel = newDefaultMessageNotificationLevel;
        this.newAfkTimeout = newAfkTimeout;
        this.newAfkChannelId = newAfkChannelId;
    }

    public boolean newIsWidgetEnabled() {
        return newWidgetEnabled;
    }

    public String getNewWidgetChannelId() {
        return newWidgetChannelId;
    }

    public VerificationLevel getNewVerificationLevel() {
        return newVerificationLevel;
    }

    public String getNewSystemChannelId() {
        return newSystemChannelId;
    }

    public Icon.GuildSplash getNewSplash() {
        return newSplash;
    }

    public VoiceRegion getNewRegion() {
        return newRegion;
    }

    public String getNewOwnerId() {
        return newOwnerId;
    }

    public String getNewName() {
        return newName;
    }

    public MFALevel getNewMfaLevel() {
        return newMfaLevel;
    }

    public Icon.GuildIcon getNewIcon() {
        return newIcon;
    }

    public ExplicitContentFilterLevel getNewExplicitContentFilterLevel() {
        return newExplicitContentFilterLevel;
    }

    public MessageNotificationLevel getNewDefaultMessageNotificationLevel() {
        return newDefaultMessageNotificationLevel;
    }

    public int getNewAfkTimeout() {
        return newAfkTimeout;
    }

    public String getNewAfkChannelId() {
        return newAfkChannelId;
    }
}