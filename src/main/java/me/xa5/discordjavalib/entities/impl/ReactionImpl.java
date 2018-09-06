package me.xa5.discordjavalib.entities.impl;

import com.sun.istack.internal.Nullable;
import me.xa5.discordjavalib.entities.Reaction;

public class ReactionImpl implements Reaction {
    private boolean animated;
    private String name;
    private String id;

    public ReactionImpl(boolean animated, String name, String id) {
        this.animated = animated;
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean isAnimated() {
        return animated;
    }

    @Override
    @Nullable
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}