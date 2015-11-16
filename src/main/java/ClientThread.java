import java.io.*;
import java.net.Socket;

/**
 * Created by lawrencew on 11/10/2015.
 */
public class ClientThread extends Thread{

    String username ="";
    BufferedReader bReader;
    PrintWriter pWriter;
    Socket socket;
    DataHolder data;

    public ClientThread(final Socket socket, final DataHolder data)
    {
        this.socket=socket;
        this.data=data;
        try{
            bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pWriter = new PrintWriter(socket.getOutputStream(),true);
            username = bReader.readLine();
            data.addMessage("User " + username + " has joined",this);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        this.start();
    }

    public void write(String message)
    {
        System.out.println("DEBUG HELLO");
        pWriter.println(message);
    }

    public void run()
    {
        String message;

            while(true)
            {
                try {
                    message = bReader.readLine();
                   if(message!=null)
                   {
                       data.addMessage(message, this);
                   }
                }
                catch(IOException e)
                {

                }
            }
    }

   /* public void close()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public String getUsername()
    {
        return username;
    }

}
