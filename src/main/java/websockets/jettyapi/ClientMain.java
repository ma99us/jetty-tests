package websockets.jettyapi;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;

public class ClientMain {
    public static void main(String[] args) {
        String dest = "wss://socketsbay.com/wss/v2/2/demo/";
        WebSocketClient client = new WebSocketClient();
        try {
            ClientSocket socket = new ClientSocket();
            client.start();
            URI echoUri = new URI(dest);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            socket.getLatch().await();

            socket.sendMessage("jetty echo");
            socket.sendMessage("jetty test");

            //Thread.sleep(10000l);

            System.in.read();
            System.out.println("Done.");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
