import java.io.*;
import java.net.ServerSocket;
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
        //System.out.println("new call");
        //ClientThread clientThread = new ClientThread(socket,data);




        this.socket=socket;
        this.data=data;
        try{
            bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pWriter = new PrintWriter(socket.getOutputStream(),true);
            username = bReader.readLine();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        data.addClient(this);
        this.start();
    }

    public void write(String message)
    {
        pWriter.println(message);
    }

    public void run()
    {
        String message;

        while(true)
        {
            try {
                if((message=bReader.readLine())!=null)
                {
                    if(message.equals("exit"))
                    {
                        data.addToRemoveClients(this);
                    }
                    else {
                        data.addMessage(message, this);
                    }
                }
            }
            catch(IOException e)
            {

            }
        }
    }

    public void close()
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername()
    {
        return username;
    }

}
