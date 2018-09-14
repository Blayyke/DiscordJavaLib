package me.xa5.discordjavalib;

import me.xa5.discordjavalib.entities.impl.DiscordApiImpl;
import me.xa5.discordjavalib.enums.HttpMethod;
import me.xa5.discordjavalib.exception.InvalidMethodException;
import okhttp3.*;

import java.io.IOException;

public class DJLHttp {
    private final OkHttpClient httpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private DiscordApiImpl api;

    public DJLHttp(DiscordApiImpl api) {
        this.api = api;
        httpClient = api.getHttpClient();
    }


    private Request get(DiscordEndpoint endpoint) {
        ensureCorrectMethod(endpoint, HttpMethod.GET);
        return builder(endpoint).get().build();
    }

    public Request.Builder builder(DiscordEndpoint endpoint, Object... endpointFormatting) {
        return new Request.Builder().header("Authorization", "Bot " + api.getToken()).url(endpoint.getUrl(endpointFormatting));
    }

    public Response getBlocking(DiscordEndpoint endpoint) throws IOException {
        return httpClient.newCall(get(endpoint)).execute();
    }

    public void getAsync(DiscordEndpoint endpoint, Callback callback) {
        httpClient.newCall(get(endpoint)).enqueue(callback);
    }

    private Request post(DiscordEndpoint endpoint, RequestBody body, Object... endpointFormatting) {
        ensureCorrectMethod(endpoint, HttpMethod.POST);
        return builder(endpoint, endpointFormatting).post(body).build();
    }

    public Response postBlocking(DiscordEndpoint endpoint, RequestBody body, Object... endpointFormatting) throws IOException {
        return httpClient.newCall(post(endpoint, body, endpointFormatting)).execute();
    }

    public void postAsync(DiscordEndpoint endpoint, RequestBody body, Callback callback, Object... endpointFormatting) {
        httpClient.newCall(post(endpoint, body, endpointFormatting)).enqueue(callback);
    }

    private void ensureCorrectMethod(DiscordEndpoint endpoint, HttpMethod method) {
        if (endpoint.getHttpMethod() == method) return;
        throw new InvalidMethodException("invalid http method for endpoint!");
    }
}