package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum MFALevel {
    NONE(0), ELEVATED(1);

    private int key;

    MFALevel(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static MFALevel fromKey(int key) {
        return Arrays.stream(values()).filter(v -> v.getKey() == key).findFirst().orElseThrow(() -> new RuntimeException("invalid MFA level key " + key + "!"));
    }
}