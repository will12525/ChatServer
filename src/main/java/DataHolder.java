import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william on 11/15/2015.
 */
public class DataHolder extends Thread {
    List<String> messages = new ArrayList<String>();
    List<ServerThread> clients = new ArrayList<ServerThread>();
    private HashMap<ServerThread,String> messageFrom = new HashMap<ServerThread, String>();
    private boolean newMessage = false;
    private boolean serverRunning = false;


    DataHolder(boolean serverRunning)
    {
        this.serverRunning=serverRunning;
    }

    public void run()
    {
        while(serverRunning) {

            //System.out.println("Client size: "+ clients.size());
            System.out.println("Messages size: "+messages.size());
            if (messages.size()>0) {
                String message = messages.get(0);
                for (ServerThread client : clients) {
                    client.write(message);
                }
                removeMessage(0);
                newMessage = false;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeClient(ServerThread client)
    {
        clients.remove(client);
    }
    public void addClient(ServerThread client)
    {
        clients.add(client);
    }

    public void removeMessage(int index)
    {
        messages.remove(index);
    }
    public void addMessage(String theMessage,ServerThread client)
    {
        System.out.println("DEBUG "+theMessage);
        newMessage = true;
        messageFrom.put(client,theMessage);
        messages.add(theMessage);
    }

}
