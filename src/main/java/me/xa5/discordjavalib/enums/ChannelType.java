package me.xa5.discordjavalib.enums;

import java.util.Arrays;

public enum ChannelType {
    TEXT(0), DM(1), VOICE(2), GROUP_DM(3), CATEGORY(4);

    private int key;

    ChannelType(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static ChannelType fromKey(int key) {
        return Arrays.stream(values()).filter(v -> v.getKey() == key).findFirst().orElseThrow(() -> new RuntimeException("invalid channel type key " + key + "!"));
    }
}