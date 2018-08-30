package me.xa5.discordjavalib.entities;

public interface Emote extends NameHolder, IdHolder, Mentionable, DJLEntity {
    boolean requiresColons();

    boolean isManaged();

    boolean isAnimated();
}