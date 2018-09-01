package me.xa5.discordjavalib.entities;

import com.sun.istack.internal.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public interface Message extends DJLEntity, IdHolder {
    TextChannel getChannel();

    User getAuthor();

    Member getMember();

    @Nullable
    Guild getGuild();

    LocalDateTime getEditTimestamp();

    LocalDateTime getTimestamp();

    String getWebhookId();

    String getContent();

    default boolean isFromWebHook() {
        return getWebhookId() != null;
    }

    boolean mentionsEveryone();

    boolean isPinned();

    boolean isTts();

    List<User> getMentionedUsers();

    List<Role> getMentionedRoles();

    List<Attachment> getAttachments();

    List<Embed> getEmbeds();

    //todo List<Attachment> getAttachments();

    //todo List<Embed> getEmbeds();

    //todo List<Reaction> getReactions();

    //application?
    //activity?
    //nonce?
}