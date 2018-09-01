package me.xa5.discordjavalib.entities.impl;

import com.sun.istack.internal.Nullable;
import me.xa5.discordjavalib.entities.*;

import java.time.LocalDateTime;
import java.util.List;

public class MessageImpl implements Message {
    private DiscordApi api;

    private TextChannel channel;
    private User author;
    private Guild guild;

    private LocalDateTime editTimestamp;
    private LocalDateTime timestamp;

    private String webhookId;
    private String content;
    private String id;

    private boolean mentionsEveryone;
    private boolean pinned;
    private boolean tts;

    private List<User> mentionedUsers;
    private List<Role> mentionedRoles;

    public MessageImpl(DiscordApi api, String id, TextChannel channel, User author, Guild guild, LocalDateTime editTimestamp, LocalDateTime timestamp, String webhookId, String content,
                       boolean mentionsEveryone, boolean pinned, boolean tts, List<User> mentionedUsers, List<Role> mentionedRoles) {
        this.api = api;
        this.channel = channel;
        this.author = author;
        this.guild = guild;
        this.editTimestamp = editTimestamp;
        this.timestamp = timestamp;
        this.webhookId = webhookId;
        this.content = content;
        this.id = id;
        this.mentionsEveryone = mentionsEveryone;
        this.pinned = pinned;
        this.tts = tts;
        this.mentionedUsers = mentionedUsers;
        this.mentionedRoles = mentionedRoles;
    }


    @Override
    public TextChannel getChannel() {
        return channel;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    @Nullable
    public Member getMember() {
        return getGuild().getMember(getAuthor());
    }

    @Override
    @Nullable
    public Guild getGuild() {
        return guild;
    }

    @Override
    @Nullable
    public LocalDateTime getEditTimestamp() {
        return editTimestamp;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    @Nullable
    public String getWebhookId() {
        return webhookId;
    }

    // Can be null in the case of an embed-only message
    @Override
    @Nullable
    public String getContent() {
        return content;
    }

    @Override
    public boolean mentionsEveryone() {
        return mentionsEveryone;
    }

    @Override
    public boolean isPinned() {
        return pinned;
    }

    @Override
    public boolean isTts() {
        return tts;
    }

    @Override
    public List<User> getMentionedUsers() {
        return mentionedUsers;
    }

    @Override
    public List<Role> getMentionedRoles() {
        return mentionedRoles;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }

    @Override
    public String getId() {
        return id;
    }
}
