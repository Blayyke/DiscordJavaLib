package me.xa5.discordjavalib.handler;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;

public abstract class WSEventHandler {
    private DiscordApi api;

    public WSEventHandler(DiscordApi api) {
        this.api = api;
    }

    public abstract String getInternalEventName();

    public abstract void handle(WSClient client, JsonObject data);

    public DiscordApi getApi() {
        return api;
    }
}