package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.VoiceState;

public final class VoiceStateImpl implements VoiceState {
    private final String userId;
    private final boolean suppressed;
    private final boolean selfVideo;
    private final boolean selfMute;
    private final boolean selfDeaf;
    private final boolean serverMuted;
    private final boolean serverDeafened;

    public VoiceStateImpl(String userId, boolean suppressed, boolean selfVideo, boolean selfMute, boolean selfDeaf, boolean serverMuted, boolean serverDeafened) {
        this.userId = userId;
        this.suppressed = suppressed;
        this.selfVideo = selfVideo;
        this.selfMute = selfMute;
        this.selfDeaf = selfDeaf;
        this.serverMuted = serverMuted;
        this.serverDeafened = serverDeafened;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isSuppressed() {
        return suppressed;
    }

    public boolean isSelfVideo() {
        return selfVideo;
    }

    public boolean isSelfMute() {
        return selfMute;
    }

    public boolean isSelfDeaf() {
        return selfDeaf;
    }

    public boolean isServerMuted() {
        return serverMuted;
    }

    public boolean isServerDeafened() {
        return serverDeafened;
    }
}