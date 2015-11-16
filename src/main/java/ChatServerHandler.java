import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ChatServerHandler extends Thread{

    private Socket socket;
    public ChatServerHandler(Socket socket)
    {
        this.socket=socket;
    }
    public void run()
    {
        try {
            String message;
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("User "+bf.readLine()+" is now connected to the server");

           while((message = bf.readLine())!=null)
           {
               System.out.println("incoming message "+message);
               pw.println("Serve rechoing Client message "+message);
           }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
