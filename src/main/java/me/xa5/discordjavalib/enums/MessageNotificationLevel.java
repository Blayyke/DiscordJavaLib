package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum MessageNotificationLevel {
    ALL_MESSAGES(0), MENTIONS_ONLY(1);

    private final int key;

    MessageNotificationLevel(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MessageNotificationLevel fromKey(int key) {
        return Arrays.stream(values()).filter(v -> v.getKey()== key).findFirst().orElseThrow(() -> new RuntimeException("invalid notification level key " + key + "!"));
    }
}