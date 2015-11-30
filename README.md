# ChatServer
# ChatClient
What it does: This application allows multiple people to connect to a server and chat with each other. It uses two 
applications, a server and client version. 

How it does it: There are two parts, the client and the server. 
	The server
		When a user opens the client a socket is opened that connects to the server
		The server adds all opened sockets to a list of clients and opens a separate thread to read from each client. 
		There is a class called ClientThread that holds information on each opened socket along with the thread that reads 
		everything sent from the client. The ClientThread also has a method that can send a message to the client.
		A class called DataHolder holds a list of all clients connected along with a list of all strings that have been sent
		by clients and server. When a thread in DataHolder class recognizes a new message in the list of messages it sends it 
		off to every client connected except for the client that sent the message. DataHolder also detects when a client has
		disconnected then proceeds to broadcast to everyone the user has left and closes the socket. There is also a check for
		when a new client joins, the server tells everyone the user that has joined.
	The client
		Is a lot simpler compared to the server.
		When the client is executed it opens the socket to the server and starts a new thread to constantly read in message
		from the server. Another method is constantly checking for user input, when there is user input, it sends the input to 
		the server.

Struggles: Figuring out how to constantly read from the server while on client and from the client when on server. 
Solution: Multithreading, this allowed me to have classes with multiple while loops to constantly check the printstreams. 

Moving forward: Make it its own gui.

I learned how to multithread 
