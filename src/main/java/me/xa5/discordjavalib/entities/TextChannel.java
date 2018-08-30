package me.xa5.discordjavalib.entities;

public interface TextChannel extends Channel {
    String getTopic();
    String getLastMessageId();
}