package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum VerificationLevel {
    NONE(0), LOW(1), MEDIUM(2), HIGH(3), VERY_HIGH(4);

    private int key;

    VerificationLevel(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static VerificationLevel fromKey(int key) {
        return Arrays.stream(values()).filter(v -> v.getKey() == key).findFirst().orElseThrow(() -> new RuntimeException("invalid verification level key " + key + "!"));
    }
}