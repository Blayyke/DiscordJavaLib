package me.xa5.discordjavalib;

import me.xa5.discordjavalib.enums.HttpMethod;

public class DiscordEndpoint {
    private static final String BASE_URL = "https://discordapp.com/api/v" + DJLConstants.API_VERSION;
    private HttpMethod httpMethod;
    private String endpoint;

    // ENDPOINTS
    public static DiscordEndpoint GET_GATEWAY = new DiscordEndpoint("/gateway", HttpMethod.GET);
    // END ENDPOINTS

    private DiscordEndpoint(String endpoint, HttpMethod httpMethod) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return BASE_URL + "/" + endpoint;
    }
}