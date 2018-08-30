package me.xa5.discordjavalib.util;

public class Assert {
    public static void notNullOrEmpty(String str, String name) {
        notNull(str, name);
        notEmpty(str, name);
    }

    private static void notNull(Object obj, String name) {
        if (obj == null) throw new IllegalArgumentException(name + " cannot be null!");
    }

    private static void notEmpty(String str, String name) {
        if (str.isEmpty()) throw new IllegalArgumentException(name + " cannot be empty!");
    }
}