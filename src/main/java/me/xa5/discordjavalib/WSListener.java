package me.xa5.discordjavalib;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.WriterConfig;
import com.neovisionaries.ws.client.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterInputStream;

public class WSListener implements WebSocketListener {
    private WSClient wsClient;

    public WSListener(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public void onStateChanged(WebSocket websocket, WebSocketState newState) throws Exception {
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        wsClient.setConnected(true);
    }

    @Override
    public void onConnectError(WebSocket websocket, WebSocketException cause) throws Exception {
        throw new RuntimeException(cause);
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
//        wsClient.getApi().invalidate();
        wsClient.setConnected(false);

        System.out.println("DISCONNECTED::");
        WebSocketFrame frame = closedByServer ? serverCloseFrame : clientCloseFrame;
        System.out.println(frame.getOpcode());
        System.out.println(frame.getCloseCode());
        System.out.println(frame.getCloseReason());
    }

    @Override
    public void onFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onContinuationFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onTextFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {
        onWebsocketMessage(websocket, frame.getPayloadText());
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

        onWebsocketMessage(websocket, result.toString());
    }

    private void onWebsocketMessage(WebSocket websocket, String message) {
        try {
            JsonObject object = Json.parse(message).asObject();

            int op = object.get("op").asInt();
            String eventType = null;
            long sequence = -1;

            if (!object.get("t").isNull()) eventType = object.get("t").asString();
            if (!object.get("s").isNull()) sequence = object.get("s").asLong();

            JsonObject data = object.get("d").isNull() ? null : object.get("d").asObject();
            wsClient.setSequence(sequence);

            switch (op) {
                case 0:
                    // EVENT
                    wsClient.handleEvent(eventType, data);
                    break;
                case 1:
                    wsClient.sendHeartbeat();
                    break;
                case 7:
                    // We need to reconnect
                    throw new NotImplementedException();
                case 9:
                    // INVALIDATE
                    wsClient.getApi().invalidate();
                case 10:
                    // HELLO!
                    wsClient.onHello(data);
                    break;
                case 11:
                    // HEARTBEAT ACKNOWLEDGED
                    wsClient.getApi().setPing(System.currentTimeMillis() - wsClient.heartbeatSendTime());
                    break;
                default:
                    System.out.println("TEXT FRAME:: ");
                    System.out.println("op:: " + op);
                    System.out.println("data:: " + data.toString(WriterConfig.PRETTY_PRINT));
                    throw new RuntimeException("Unknown OpCode!");
            }
        } catch (Exception e) {
            System.out.println(message);
            e.printStackTrace();
        }
    }

    @Override
    public void onCloseFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onPingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onPongFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {

    }

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {

    }

    @Override
    public void onSendingFrame(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onFrameSent(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onFrameUnsent(WebSocket websocket, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onThreadCreated(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

    }

    @Override
    public void onThreadStarted(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

    }

    @Override
    public void onThreadStopping(WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    @Override
    public void onFrameError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onMessageError(WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) throws Exception {

    }

    @Override
    public void onMessageDecompressionError(WebSocket websocket, WebSocketException cause, byte[] compressed) throws Exception {

    }

    @Override
    public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {

    }

    @Override
    public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

    }

    @Override
    public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {

    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void onSendingHandshake(WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {

    }
}