package me.xa5.discordjavalib.event.guild.channel;

import me.xa5.discordjavalib.entities.Channel;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.GuildChannel;
import me.xa5.discordjavalib.event.guild.GuildEvent;

public abstract class ChannelEvent extends GuildEvent {
    private final Channel channel;

    public ChannelEvent(DiscordApi api, Channel channel) {
        super(api, channel instanceof GuildChannel ? ((GuildChannel) channel).getGuild() : null);
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}