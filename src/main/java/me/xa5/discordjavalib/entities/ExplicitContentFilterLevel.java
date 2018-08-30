package me.xa5.discordjavalib.entities;

import java.util.Arrays;

public enum ExplicitContentFilterLevel {
    DISABLED(0), MEMBERS_WITHOUT_ROLES(1), ALL_MEMBERS(2);

    private int key;

    ExplicitContentFilterLevel(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ExplicitContentFilterLevel fromKey(int key) {
        return Arrays.stream(values()).filter(v -> v.getKey() == key).findFirst().orElseThrow(() -> new RuntimeException("invalid explicit content filter level key " + key + "!"));
    }
}