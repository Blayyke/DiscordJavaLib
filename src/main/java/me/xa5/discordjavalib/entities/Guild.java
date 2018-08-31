package me.xa5.discordjavalib.entities;

import me.xa5.discordjavalib.enums.MFALevel;
import me.xa5.discordjavalib.enums.MessageNotificationLevel;
import me.xa5.discordjavalib.enums.VerificationLevel;
import me.xa5.discordjavalib.enums.VoiceRegion;

import java.time.LocalDateTime;
import java.util.List;

public interface Guild extends NameHolder, IdHolder, DJLEntity {
    boolean isUnavailable();

    int getAfkTimeout();

    boolean isLarge();

    MFALevel getMfaLevel();

    VoiceRegion getRegion();

    MessageNotificationLevel getDefaultMessageNotificationLevel();

    ExplicitContentFilterLevel getExplicitContentFilterLevel();

    VerificationLevel getVerificationLevel();

    List<Emote> getEmotes();

    List<Role> getRoles();

    LocalDateTime getJoinDate();

    ChannelCategory getChannelCategory(String id);

    TextChannel getTextChannel(String id);

    VoiceChannel getVoiceChannel(String id);

    TextChannel getSystemChannel();

    VoiceChannel getAfkChannel();

    Role getRole(String roleId);

    Icon.GuildIcon getIcon();

    Icon.GuildSplash getSplash();

    Member getOwner();

    Member getMember(String id);

    Member getMember(User user);

    Presence getPresence(Member member);

    Presence getPresence(String id);

    VoiceState getVoiceState(Member member);

    VoiceState getVoiceState(String id);
}