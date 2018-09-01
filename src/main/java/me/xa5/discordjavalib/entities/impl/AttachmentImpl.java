package me.xa5.discordjavalib.entities.impl;

import me.xa5.discordjavalib.entities.Attachment;
import me.xa5.discordjavalib.entities.DiscordApi;

public class AttachmentImpl implements Attachment {
    private DiscordApi api;
    private String proxyUrl;
    private String fileName;
    private String url;
    private String id;
    private int size;
    private int height;
    private int width;

    public AttachmentImpl(DiscordApi api, String proxyUrl, String fileName, String url, String id, int size, int height, int width) {
        this.api = api;
        this.proxyUrl = proxyUrl;
        this.fileName = fileName;
        this.url = url;
        this.id = id;
        this.size = size;
        this.height = height;
        this.width = width;
    }

    @Override
    public String getProxyUrl() {
        return proxyUrl;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Get the size, in bytes, of this attachment.
     *
     * @return The size of the attachment in bytes.
     */
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }
}