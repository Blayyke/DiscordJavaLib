package me.xa5.discordjavalib.entities;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public interface Embed {
    List<EmbedField> getFields();

    String getTitle();

    String getDescription();

    EmbedFooter getFooter();

    EmbedImage getImage();

    EmbedImage getThumbnail();

    EmbedMedia getVideo();

    EmbedAuthor getAuthor();

    Color getColor();

    LocalDateTime getTimestamp();

    class EmbedProvider {
        String name;
        String url;

        EmbedProvider(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

    class EmbedMedia {
        private final String url;
        private final int height;
        private final int width;

        EmbedMedia(String url, int height, int width) {
            this.url = url;
            this.height = height;
            this.width = width;
        }

        public final String getUrl() {
            return url;
        }

        public final int getHeight() {
            return height;
        }

        public final int getWidth() {
            return width;
        }

    }

    final class EmbedImage extends EmbedMedia {
        private String proxyUrl;

        public EmbedImage(String url, int height, int width, String proxyUrl) {
            super(url, height, width);
            this.proxyUrl = proxyUrl;
        }

        public String getProxyUrl() {
            return proxyUrl;
        }
    }

    final class EmbedFooter {
        private String text;
        private String iconUrl;
        private String proxyIconUrl;

        public EmbedFooter(String text, String iconUrl, String proxyIconUrl) {
            this.text = text;
            this.iconUrl = iconUrl;
            this.proxyIconUrl = proxyIconUrl;
        }

        public String getProxyIconUrl() {
            return proxyIconUrl;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public String getText() {
            return text;
        }

    }

    final class EmbedAuthor {
        private String name;
        private String url;
        private String iconUrl;
        private String proxyIconUrl;

        EmbedAuthor(String name, String url, String iconUrl, String proxyIconUrl) {
            this.name = name;
            this.url = url;
            this.iconUrl = iconUrl;
            this.proxyIconUrl = proxyIconUrl;
        }

        public String getName() {
            return name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public String getProxyIconUrl() {
            return proxyIconUrl;
        }

        public String getUrl() {
            return url;
        }
    }

    final class EmbedField {
        private boolean inline;
        private String name;
        private String value;

        public EmbedField(boolean inline, String name, String value) {
            this.inline = inline;
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public boolean isInline() {
            return inline;
        }
    }
}