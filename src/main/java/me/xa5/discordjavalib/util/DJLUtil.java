package me.xa5.discordjavalib.util;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DJLUtil {
    private static DateTimeFormatter DISCORD_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.from(DISCORD_FORMATTER.parse(date));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse date \'" + date + "\'! Please notify the developer of this issue.", e);
        }
    }

    public static String formatDate(LocalDateTime timestamp) {
        return DISCORD_FORMATTER.format(timestamp);
    }

    public static Color parseColor(int color) {
        return Color.decode(String.format("#%06X", 0xFFFFFF & color));
    }

    public static int formatColor(Color color) {
        return ((color.getRed() & 0x0ff) << 16) | ((color.getGreen() & 0x0ff) << 8) | (color.getBlue() & 0x0ff);
    }
}