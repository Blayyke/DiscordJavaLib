package me.xa5.discordjavalib.entities;

public interface User extends NameHolder, IdHolder, Mentionable, DJLEntity {
    String getDiscriminator();

    boolean isBot();

    Icon.UserIcon getAvatar();
}