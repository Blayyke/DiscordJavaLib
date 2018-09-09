package me.xa5.discordjavalib;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.WriterConfig;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFrame;
import me.xa5.discordjavalib.entities.DiscordApi;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterInputStream;

public class WSListener extends WebSocketAdapter {
    private WSClient wsClient;
    private DiscordApi api;
    private String[] ignoredEvents = {"presences_replace"}; // discord sends this to bots for no apparent reason /shrug

    WSListener(WSClient wsClient, DiscordApi api) {
        this.wsClient = wsClient;
        this.api = api;
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) {
        wsClient.setConnected(true);
        api.getLogger().info("WebSocket connection established! (URL: " + websocket.getURI().toString() + ")");
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) {
//        wsClient.getApi().invalidate();
        wsClient.setConnected(false);

        System.out.println("DISCONNECTED::");
        WebSocketFrame frame = closedByServer ? serverCloseFrame : clientCloseFrame;
        System.out.println(frame.getOpcode());
        System.out.println(frame.getCloseCode());
        System.out.println(frame.getCloseReason());
    }

    @Override
    public void onTextFrame(WebSocket websocket, WebSocketFrame frame) {
        onWebSocketMessage(frame.getPayloadText());
    }

    @Override
    public void onBinaryFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(frame.getPayload());
        InflaterInputStream iis = new InflaterInputStream(bais);

        StringBuilder result = new StringBuilder();
        byte[] buf = new byte[5];
        int rlen;
        while ((rlen = iis.read(buf)) != -1) {
            result.append(new String(Arrays.copyOf(buf, rlen)));
        }

        onWebSocketMessage(result.toString());
    }

    private void onWebSocketMessage(String message) {
        JsonObject object = Json.parse(message).asObject();

        try {
            int op = object.get("op").asInt();
            String eventType = null;

            if (!object.get("t").isNull()) eventType = object.get("t").asString();
            if (object.get("s") != null && !object.get("s").isNull()) wsClient.setSequence(object.get("s").asLong());

            // Filter out events that we don't need
            for (String ignoredEvent : ignoredEvents) if (eventType.equalsIgnoreCase(ignoredEvent)) return;


            switch (op) {
                case 0: {
                    // EVENT
                    JsonObject data = object.get("d").isNull() ? null : object.get("d").asObject();
                    wsClient.handleEvent(eventType, data);
                    break;
                }
                case 1:
                    wsClient.sendHeartbeat();
                    break;
                case 7:
                    // We need to reconnect
                    throw new NotImplementedException();
                case 9:
                    // INVALIDATE
                    wsClient.getApi().invalidate();
                    break;
                case 10: {
                    // HELLO!
                    JsonObject data = object.get("d").asObject();
                    wsClient.onHello(data);
                    break;
                }
                case 11:
                    // HEARTBEAT ACKNOWLEDGED
                    wsClient.getApi().setPing(System.currentTimeMillis() - wsClient.heartbeatSendTime());
                    break;
                default:
                    System.out.println("TEXT FRAME:: ");
                    System.out.println("op:: " + op);
                    System.out.println("data:: " + message);
                    throw new RuntimeException("Unknown OpCode!");
            }
        } catch (Exception e) {
            api.getLogger().warn("Uncaught exception on websocket message handling: " + e.getMessage());
            e.printStackTrace();

            api.getLogger().warn("JSON involved: \n" + object.toString(WriterConfig.PRETTY_PRINT));
        }
    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) {
        cause.printStackTrace();
    }
}