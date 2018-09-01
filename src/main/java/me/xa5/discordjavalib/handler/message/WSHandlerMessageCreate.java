package me.xa5.discordjavalib.handler.message;

import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.DiscordApi;
import me.xa5.discordjavalib.entities.impl.MessageImpl;
import me.xa5.discordjavalib.event.guild.message.EventMessageCreate;
import me.xa5.discordjavalib.handler.WSEventHandler;
import me.xa5.discordjavalib.util.JsonFactory;

public class WSHandlerMessageCreate extends WSEventHandler {
    public WSHandlerMessageCreate(DiscordApi api) {
        super(api);
    }

    @Override
    public String getInternalEventName() {
        return "message_create";
    }

    @Override
    public void handle(WSClient client, JsonObject data) {
        MessageImpl message = JsonFactory.messageFromJson(getApi(), data);

        getApi().dispatchEvent(new EventMessageCreate(getApi(), message));
    }
}