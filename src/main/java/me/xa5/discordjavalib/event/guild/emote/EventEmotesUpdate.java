package me.xa5.discordjavalib.event.guild.emote;

import me.xa5.discordjavalib.entities.Emote;
import me.xa5.discordjavalib.entities.impl.EmoteImpl;
import me.xa5.discordjavalib.entities.impl.GuildImpl;
import me.xa5.discordjavalib.event.guild.GuildEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventEmotesUpdate extends GuildEvent {
    private final List<Emote> oldEmotes;
    private final List<Emote> newEmotes;

    public EventEmotesUpdate(GuildImpl guild, Map<String, EmoteImpl> emoteMap) {
        super(guild.getApi(), guild);
        this.oldEmotes = Collections.unmodifiableList(new ArrayList<>(guild.getEmoteMap().values()));
        this.newEmotes = Collections.unmodifiableList(new ArrayList<>(emoteMap.values()));
    }

    public List<Emote> getNewEmotes() {
        return newEmotes;
    }

    public List<Emote> getOldEmotes() {
        return oldEmotes;
    }
}