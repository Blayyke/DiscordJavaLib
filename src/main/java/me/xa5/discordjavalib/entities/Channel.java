package me.xa5.discordjavalib.entities;

public interface Channel extends IdHolder, NameHolder, DJLEntity {
    int getPosition();

    ChannelCategory getParentCategory();
}