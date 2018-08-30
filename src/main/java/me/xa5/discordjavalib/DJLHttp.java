package me.xa5.discordjavalib;

import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import me.xa5.discordjavalib.enums.HttpMethod;
import me.xa5.discordjavalib.exception.InvalidMethodException;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class DJLHttp {
    private final OkHttpClient httpClient;

    public DJLHttp(DiscordApiImpl api) {
        httpClient = api.getHttpClient();
    }

    public void get(DiscordEndpoint endpoint, Callback callback) {
        ensureCorrectMethod(endpoint, HttpMethod.GET);
        Request getRequest = new Request.Builder().url(endpoint.getUrl()).get().build();
        httpClient.newCall(getRequest).enqueue(callback);
    }

    public Response getBlocking(DiscordEndpoint endpoint) throws IOException {
        ensureCorrectMethod(endpoint, HttpMethod.GET);
        Request getRequest = new Request.Builder().url(endpoint.getUrl()).get().build();
        return httpClient.newCall(getRequest).execute();
    }

    private void ensureCorrectMethod(DiscordEndpoint endpoint, HttpMethod method) {
        if (endpoint.getHttpMethod() == method) return;
        throw new InvalidMethodException("invalid http method for endpoint!");
    }
}