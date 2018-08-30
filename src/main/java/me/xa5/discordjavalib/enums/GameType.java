package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum GameType {
    PLAYING(0), STREAMING(1);

    private int key;

    GameType(int key) {
        this.key = key;
    }

    public static GameType fromKey(int key) {
        return Arrays.stream(values()).filter(v -> v.getKey() == key).findFirst().orElseThrow(() -> new RuntimeException("invalid game type key " + key + "!"));
    }

    public int getKey() {
        return key;
    }
}