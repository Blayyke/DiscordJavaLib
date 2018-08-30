package me.xa5.discordjavalib.entities;

public abstract class Icon {
    private static final String BASE_URL = "https://cdn.discordapp.com/";
    private String hash;
    private String entityId;

    private Icon(String hash, String entityId) {
        this.hash = hash;
        this.entityId = entityId;
    }

    public String getHash() {
        return hash;
    }

    public String getEntityId() {
        return entityId;
    }

    abstract String getUrl();

    public static class UserIcon extends Icon {
        public UserIcon(String hash, String id) {
            super(hash, id);
        }

        @Override
        public String getUrl() {
            return BASE_URL + "avatars/" + getEntityId() + "/" + getHash();
        }
    }

    public static class GuildIcon extends Icon {
        public GuildIcon(String hash, String id) {
            super(hash, id);
        }

        @Override
        public String getUrl() {
            return BASE_URL + "icons/" + getEntityId() + "/" + getHash();
        }
    }

    public static class GuildSplash extends Icon {
        public GuildSplash(String hash, String id) {
            super(hash, id);
        }

        @Override
        String getUrl() {
            return BASE_URL + "splashes/" + getEntityId() + "/" + getHash();
        }
    }
}