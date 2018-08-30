package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum VoiceRegion {
    UNKNOWN("unknown");

    private String region;

    VoiceRegion(String region) {
        this.region = region;
    }

    public static VoiceRegion fromKey(String region) {
        return Arrays.stream(values()).filter(v -> v.getRegion().equalsIgnoreCase(region)).findFirst().orElse(UNKNOWN);
    }

    private String getRegion() {
        return region;
    }
}