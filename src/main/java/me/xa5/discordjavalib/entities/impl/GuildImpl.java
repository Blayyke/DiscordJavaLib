package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.*;
import me.xa5.discordjavalib.enums.MFALevel;
import me.xa5.discordjavalib.enums.MessageNotificationLevel;
import me.xa5.discordjavalib.enums.VerificationLevel;
import me.xa5.discordjavalib.enums.VoiceRegion;

import java.time.LocalDateTime;
import java.util.*;

public class GuildImpl implements Guild {
    private final String id;
    private boolean unavailable;
    private String name;
    private int afkTimeout;
    private boolean large;
    private MFALevel mfaLevel;
    private VoiceRegion region;
    private MessageNotificationLevel defaultMessageNotificationLevel;
    private ExplicitContentFilterLevel explicitContentFilterLevel;
    private VerificationLevel verificationLevel;

    private Map<String, RoleImpl> roleMap = new HashMap<>();
    private Map<String, EmoteImpl> emoteMap = new HashMap<>();
    private Map<String, MemberImpl> memberMap = new HashMap<>();
    private Map<String, TextChannelImpl> textChannelMap = new HashMap<>();
    private Map<String, VoiceChannelImpl> voiceChannelMap = new HashMap<>();
    private Map<String, ChannelCategoryImpl> channelCategoryMap = new HashMap<>();
    private LocalDateTime joinDate;
    private TextChannel systemChannel;
    private VoiceChannel afkChannel;
    private DiscordApi api;
    private Icon.GuildIcon icon;
    private Icon.GuildSplash splash;
    private String ownerId;

    public GuildImpl(DiscordApi api, boolean unavailable, String id) {
        this.api = api;
        this.id = id;
        this.unavailable = unavailable;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isUnavailable() {
        return unavailable;
    }

    public void setUnavailable(boolean unavailable) {
        this.unavailable = unavailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAfkTimeout(int afkTimeout) {
        this.afkTimeout = afkTimeout;
    }

    @Override
    public int getAfkTimeout() {
        return afkTimeout;
    }

    public void setLarge(boolean large) {
        this.large = large;
    }

    @Override
    public boolean isLarge() {
        return large;
    }

    public void setMFALevel(MFALevel mfaLevel) {
        this.mfaLevel = mfaLevel;
    }

    @Override
    public MFALevel getMfaLevel() {
        return mfaLevel;
    }

    public void setRegion(VoiceRegion region) {
        this.region = region;
    }

    @Override
    public VoiceRegion getRegion() {
        return region;
    }

    public void setDefaultMessageNotificationLevel(MessageNotificationLevel defaultMessageNotificationLevel) {
        this.defaultMessageNotificationLevel = defaultMessageNotificationLevel;
    }

    @Override
    public MessageNotificationLevel getDefaultMessageNotificationLevel() {
        return defaultMessageNotificationLevel;
    }

    public void setExplicitContentFilterLevel(ExplicitContentFilterLevel filterLevel) {
        this.explicitContentFilterLevel = filterLevel;
    }

    @Override
    public ExplicitContentFilterLevel getExplicitContentFilterLevel() {
        return explicitContentFilterLevel;
    }

    public void setVerificationLevel(VerificationLevel verificationLevel) {
        this.verificationLevel = verificationLevel;
    }

    @Override
    public VerificationLevel getVerificationLevel() {
        return verificationLevel;
    }

    public Map<String, EmoteImpl> getEmoteMap() {
        return emoteMap;
    }

    @Override
    public List<Emote> getEmotes() {
        return Collections.unmodifiableList(new ArrayList<>(emoteMap.values()));
    }

    public Map<String, RoleImpl> getRoleMap() {
        return roleMap;
    }

    @Override
    public List<Role> getRoles() {
        return Collections.unmodifiableList(new ArrayList<>(roleMap.values()));
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    @Override
    public ChannelCategory getChannelCategory(String id) {
        return channelCategoryMap.get(id);
    }

    @Override
    public TextChannel getTextChannel(String id) {
        return textChannelMap.get(id);
    }

    @Override
    public VoiceChannel getVoiceChannel(String id) {
        return voiceChannelMap.get(id);
    }

    public Map<String, ChannelCategoryImpl> getChannelCategoryMap() {
        return channelCategoryMap;
    }

    public Map<String, TextChannelImpl> getTextChannelMap() {
        return textChannelMap;
    }

    public Map<String, VoiceChannelImpl> getVoiceChannelMap() {
        return voiceChannelMap;
    }

    public List<ChannelCategory> getChannelCategories() {
        return Collections.unmodifiableList(new ArrayList<>(channelCategoryMap.values()));
    }

    public List<TextChannel> getTextChannels() {
        return Collections.unmodifiableList(new ArrayList<>(textChannelMap.values()));
    }

    public List<VoiceChannel> getVoiceChannels() {
        return Collections.unmodifiableList(new ArrayList<>(voiceChannelMap.values()));
    }

    public void setSystemChannel(TextChannel systemChannel) {
        this.systemChannel = systemChannel;
    }

    @Override
    public TextChannel getSystemChannel() {
        return systemChannel;
    }

    public void setAfkChannel(VoiceChannel afkChannel) {
        this.afkChannel = afkChannel;
    }

    @Override
    public VoiceChannel getAfkChannel() {
        return afkChannel;
    }

    @Override
    public Role getRole(String roleId) {
        return roleMap.get(roleId);
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }

    public Map<String, MemberImpl> getMemberMap() {
        return memberMap;
    }

    @Override
    public Icon.GuildIcon getIcon() {
        return icon;
    }

    @Override
    public Icon.GuildSplash getSplash() {
        return splash;
    }

    public void setIcon(Icon.GuildIcon icon) {
        this.icon = icon;
    }

    public void setSplash(Icon.GuildSplash splash) {
        this.splash = splash;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Member getOwner() {
        return getMember(ownerId);
    }

    @Override
    public Member getMember(String id) {
        return memberMap.get(id);
    }
}