package me.xa5.discordjavalib.entities;

public interface VoiceChannel extends GuildChannel {
    int getBitrate();

    int getUserLimit();
}