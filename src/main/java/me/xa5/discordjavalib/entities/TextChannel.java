package me.xa5.discordjavalib.entities;

public interface TextChannel extends GuildChannel {
    String getTopic();

    String getLastMessageId();

    Guild getGuild();

    void message(String text);

    void message(Embed embed);
}