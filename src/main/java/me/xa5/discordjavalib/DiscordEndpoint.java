package me.xa5.discordjavalib;

import me.xa5.discordjavalib.enums.HttpMethod;

public class DiscordEndpoint {
    private static final String BASE_URL = "https://discordapp.com/api/v" + DJLConstants.API_VERSION;
    private HttpMethod httpMethod;
    private String endpoint;

    // ENDPOINTS
    public static final DiscordEndpoint GET_GATEWAY = new DiscordEndpoint("gateway", HttpMethod.GET);
    public static final DiscordEndpoint SEND_MESSAGE = new DiscordEndpoint("channels/%s/messages", HttpMethod.POST);
    // END ENDPOINTS

    private DiscordEndpoint(String endpoint, HttpMethod httpMethod) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl(Object... formatting) {
        return BASE_URL + "/" + format(formatting);
    }

    public String format(Object... objects) {
        return String.format(endpoint, objects);
    }
}