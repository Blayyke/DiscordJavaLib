package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.Embed;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class EmbedImpl implements Embed {
    private List<EmbedField> fields;
    private String title;
    private String description;
    private EmbedFooter footer;
    private EmbedImage image;
    private EmbedImage thumbnail;

    public EmbedImpl(List<EmbedField> fields, String title, String description, EmbedFooter footer, EmbedImage image, EmbedImage thumbnail) {
        this.fields = fields;
        this.title = title;
        this.description = description;
        this.footer = footer;
        this.image = image;
        this.thumbnail = thumbnail;
    }

    @Override
    public List<EmbedField> getFields() {
        return fields;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public EmbedFooter getFooter() {
        return footer;
    }

    @Override
    public EmbedImage getImage() {
        return image;
    }

    @Override
    public EmbedImage getThumbnail() {
        return thumbnail;
    }

    @Override
    public EmbedMedia getVideo() {
        return null;
    }

    @Override
    public EmbedAuthor getAuthor() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return null;
    }
}