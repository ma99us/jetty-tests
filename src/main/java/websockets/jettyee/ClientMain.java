package websockets.jettyee;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

public class ClientMain {
    public static void main(String[] args) {
        try {
            String dest = "wss://socketsbay.com/wss/v2/2/demo/";
            ClientSocket socket = new ClientSocket();
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(socket, new URI(dest));
            socket.getLatch().await();

            socket.sendMessage("ee echo356");
            socket.sendMessage("ee test356");

//            Thread.sleep(10000l);

            System.in.read();
            System.out.println("Done.");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
