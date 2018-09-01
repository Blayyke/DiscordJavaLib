package me.xa5.discordjavalib.entities;

public interface Attachment extends IdHolder, DJLEntity {
    String getProxyUrl();

    String getFileName();

    String getUrl();

    int getHeight();

    int getWidth();

    int getSize();

    default boolean isImage() {
        return getWidth() != -1 && getHeight() != -1;
    }
}