package me.xa5.discordjavalib.entities.impl;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import me.xa5.discordjavalib.DJLHttp;
import me.xa5.discordjavalib.DiscordEndpoint;
import me.xa5.discordjavalib.WSClient;
import me.xa5.discordjavalib.entities.*;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class TextChannelImpl implements TextChannel {
    private String topic;
    private String lastMessageId;
    private int position;
    private ChannelCategory parentCategory;
    private String id;
    private String name;
    private DiscordApi api;
    private Guild guild;

    public TextChannelImpl(DiscordApi api, String lastMessageId, int position, ChannelCategory parentCategory, String id, String name, String topic, Guild guild) {
        this.api = api;
        this.topic = topic;
        this.lastMessageId = lastMessageId;
        this.position = position;
        this.parentCategory = parentCategory;
        this.id = id;
        this.name = name;
        this.guild = guild;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getLastMessageId() {
        return lastMessageId;
    }

    @Override
    public Guild getGuild() {
        return guild;
    }

    @Override
    public void message(String text) {
        api.getLogger().debug("SENDING MESSAGE: " + text);
        WSClient wsClient = ((DiscordApiImpl) api).getWsClient();
        DJLHttp http = wsClient.getHttp();

        JsonObject payload = Json.object();
        payload.set("content", text);
        System.out.println(payload);

        http.postAsync(DiscordEndpoint.SEND_MESSAGE, RequestBody.create(DJLHttp.JSON, payload.toString()), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.err.println("FAILURE:: ");
                System.err.println(e.getMessage());
                System.out.println("URL: " + call.request().url());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) return;
                System.out.println(response.headers());

                System.out.println("UNKNOWN RESPONSE:: ");
                System.out.println("CODE: " + response.code());
                System.out.println("URL: " + call.request().url());
                System.out.println(response.body().string());
            }
        }, getId());
    }

    @Override
    public void message(Embed embed) {
        WSClient wsClient = ((DiscordApiImpl) api).getWsClient();
//        wsClient.getHttp().postBlocking(DiscordEndpoint.SEND_MESSAGE, RequestBody.create(DJLHttp.JSON));
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public ChannelCategory getParentCategory() {
        return parentCategory;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DiscordApi getApi() {
        return api;
    }
}