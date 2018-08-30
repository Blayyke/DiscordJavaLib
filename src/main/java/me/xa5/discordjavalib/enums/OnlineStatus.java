package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum OnlineStatus {
    DO_NOT_DISTURB("dnd"), IDLE("idle"), OFFLINE("offline"), ONLINE("online");

    private String key;

    OnlineStatus(String key) {
        this.key = key;
    }

    private String getKey() {
        return key;
    }

    public static OnlineStatus fromKey(String key) {
        return Arrays.stream(values()).filter(v -> v.getKey().equals(key)).findFirst().orElseThrow(() -> new RuntimeException("invalid online status key " + key + "!"));
    }
}