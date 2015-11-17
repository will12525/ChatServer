import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatServer extends Thread{

    private final int port = 5000;
    private boolean running = true;
    final DataHolder data;

    public ChatServer() throws Exception
    {
         data = new DataHolder(true);
        data.start();

        ServerSocket sSocket = new ServerSocket(port);
        System.out.println("Server ready on port " + port);

        this.start();

        while(running) {
           // Socket socket = sSocket.accept();
             new ClientThread(sSocket.accept(),data);

            /*if(!data.checkForClient(clientThread))
            {
                data.addClient(clientThread);
            }*/
        }

    }
    public void run()
    {
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            try{
                String message = serverInput.readLine();
                if(message!=null) {
                    data.addMessage(message);
                }
            }catch (IOException e)
            {

            }
        }


    }
    public static void main(String[] args) throws Exception{
        new ChatServer();
    }
}