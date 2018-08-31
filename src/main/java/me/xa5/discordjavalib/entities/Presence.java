package me.xa5.discordjavalib.entities;

import me.xa5.discordjavalib.enums.OnlineStatus;

public class Presence {
    private final String userId;
    private final OnlineStatus status;
    private final Game game;

    public Presence(String userId, OnlineStatus status, Game game) {
        this.userId = userId;
        this.status = status;
        this.game = game;
    }

    public OnlineStatus getStatus() {
        return status;
    }

    public Game getGame() {
        return game;
    }

    public String getUserId() {
        return userId;
    }
}