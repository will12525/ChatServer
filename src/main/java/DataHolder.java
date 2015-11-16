import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william on 11/15/2015.
 */
public class DataHolder extends Thread {
    List<String> messages = new ArrayList<String>();
    List<ClientThread> clients = new ArrayList<ClientThread>();
    List<ClientThread> clientsToRemove = new ArrayList<ClientThread>();
    List<ClientThread> clientMessageTrack = new ArrayList<ClientThread>();
    private boolean serverRunning = false;

    DataHolder(boolean serverRunning)
    {
        this.serverRunning=serverRunning;
    }

    public void run()
    {
        while(serverRunning) {
            if(clientsToRemove.size()>0)
            {
                removeClients();
            }
            if (messages.size()>0) {

                String message = messages.get(0);
                for (ClientThread client : clients) {
                    if (!checkSimilar(client)) {
                        client.write(message);
                    }

                }
                removeMessage(0);
                if (clientMessageTrack.size() > 0) {
                    clientMessageTrack.remove(0);
                }
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean checkSimilar(ClientThread client)
    {
        return clientMessageTrack.size() > 0 && clientMessageTrack.get(0) == client;
    }

    public void addToRemoveClients(ClientThread client)
    {
        clientsToRemove.add(client);
    }
    public void removeClients()
    {
        for(ClientThread client : clientsToRemove)
        {
            addMessage(client.getUsername()+" has left the channel");
            System.out.println(client.getUsername()+" has left the channel");
            client.close();
        }
        clients.removeAll(clientsToRemove);
        clientsToRemove.clear();
    }
    public void addClient(ClientThread client)
    {
        addMessage("User " + client.getUsername() + " has joined");
        System.out.println("User " + client.getUsername() + " has joined");
        clients.add(client);
    }
    public boolean checkForClient(ClientThread client)
    {
        return clients.contains(client);
    }
    public void removeMessage(int index)
    {
        messages.remove(index);
    }
    public void addMessage(String theMessage,ClientThread client)
    {
        clientMessageTrack.add(client);
        messages.add(client.getUsername()+": "+theMessage);
        System.out.println(client.getUsername()+": "+theMessage);
    }
    //usually from server
    public void addMessage(String theMessage)
    {
        messages.add("Server: "+theMessage);
    }

}
