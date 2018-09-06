package me.xa5.discordjavalib.entities;

import java.time.LocalDateTime;
import java.util.List;

public interface Member extends DJLEntity {
    List<Role> getRoles();

    boolean isDeafened();

    boolean isMuted();

    LocalDateTime getJoinDate();

    User getUser();

    String getNickname();

    String getDisplayName();

    boolean hasNickname();

    Guild getGuild();
}