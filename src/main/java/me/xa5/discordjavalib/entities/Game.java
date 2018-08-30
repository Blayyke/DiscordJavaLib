package me.xa5.discordjavalib.entities;

import me.xa5.discordjavalib.enums.GameType;
import me.xa5.discordjavalib.util.Assert;

public class Game {
    private String name;
    private GameType type;
    private String url;

    public Game(GameType type, String name) {
        if (type == GameType.STREAMING) throw new IllegalArgumentException("game type STREAMING without URL!");

        this.type = type;
        this.name = name;
    }

    public Game(GameType type, String name, String url) {
        Assert.notNullOrEmpty(url, name);
        this.type = type;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public GameType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
