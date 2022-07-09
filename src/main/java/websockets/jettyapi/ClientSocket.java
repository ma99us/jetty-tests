package websockets.jettyapi;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@WebSocket
public class ClientSocket {
    private Session session;

    CountDownLatch latch= new CountDownLatch(1);

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {
        System.out.println("Websocket message received: " + message);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Websocket connected");
        this.session=session;
        latch.countDown();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Websocket closed due to " + statusCode + ": " + reason);
    }

    @OnWebSocketError
    public void onWebSocketError(Throwable t) {
        System.out.println("WebSocket error: " + t.getMessage());
    }

    public void sendMessage(String str) {
        try {
            session.getRemote().sendString(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
