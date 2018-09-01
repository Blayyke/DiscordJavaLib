package me.xa5.discordjavalib.util;

import java.util.Collection;
import java.util.List;

public class Assert {
    public static void notNullOrEmpty(String str, String name) {
        notNull(str, name);
        notEmpty(str, name);
    }

    public static void notNull(Object obj, String name) {
        if (obj == null) throw new IllegalArgumentException(name + " cannot be null!");
    }

    private static void notEmpty(String str, String name) {
        if (str.isEmpty()) throw new IllegalArgumentException(name + " cannot be empty!");
    }

    public static void noneNull(Object[] objects, String name) {
        for (Object o : objects)
            if (o == null) throw new IllegalArgumentException(name + " cannot contain null object!");
    }

    public static void noneNull(Collection objects, String name) {
        for (Object o : objects)
            if (o == null) throw new IllegalArgumentException(name + " cannot contain null object!");
    }

    public static void noneNull(List objects, String name) {
        for (Object o : objects)
            if (o == null) throw new IllegalArgumentException(name + " cannot contain null object!");
    }
}