import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatServer {

    private final int port = 5000;
    private boolean running = true;

    public ChatServer() throws Exception
    {
        ServerSocket sSocket = new ServerSocket(port);
        System.out.println("Server ready on port " + port);
        while(running) {
            Socket socket = sSocket.accept();
            new ChatServerHandler(socket).start();
        }
    }

    public static void main(String[] args) throws Exception{
        new ChatServer();
    }
}
