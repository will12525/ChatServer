import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatServer {

    private final int port = 5000;
    private boolean running = true;
    List<Socket> clients = new ArrayList<Socket>();

    public ChatServer() throws Exception
    {
        final DataHolder data = new DataHolder(true);
        data.start();

        ServerSocket sSocket = new ServerSocket(port);
        System.out.println("Server ready on port " + port);

        while(running) {
            Socket socket = sSocket.accept();
            ClientThread clientThread = new ClientThread(socket,data);

            if(!clients.contains(socket))
            {
                data.addClient(clientThread);
                clients.add(socket);
                System.out.println(clients.size());
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new ChatServer();
    }
}