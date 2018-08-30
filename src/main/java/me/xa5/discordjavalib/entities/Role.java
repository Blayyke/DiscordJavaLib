package me.xa5.discordjavalib.entities;

import java.awt.*;

public interface Role extends IdHolder, NameHolder, Mentionable, DJLEntity {
    int getPosition();

    long getRawPermissions();

    boolean isMentionable();

    boolean isManaged();

    boolean isHoisted();

    Color getColor();
}