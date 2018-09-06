package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class MemberImpl implements Member {
    private final LocalDateTime joinDate;
    private List<Role> roles;
    private final User user;
    private final boolean isDeafened;
    private final boolean isMuted;
    private String nickname;
    private Guild guild;
    private DiscordApi api;

    public MemberImpl(DiscordApi api, LocalDateTime joinDate, List<Role> roles, User user, boolean isDeafened, boolean isMuted, String nickname, Guild guild) {
        this.api = api;
        this.guild = guild;
        this.joinDate = joinDate;
        this.roles = roles;
        this.user = user;
        this.isDeafened = isDeafened;
        this.isMuted = isMuted;
        this.nickname = nickname;
    }

    @Override
    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    @Override
    public boolean isDeafened() {
        return isDeafened;
    }

    @Override
    public boolean isMuted() {
        return isMuted;
    }

    @Override
    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String getDisplayName() {
        return hasNickname() ? getNickname() : user.getName();
    }

    @Override
    public boolean hasNickname() {
        return getNickname() != null;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }
}