# Reading from and Writing to a Socket
from the tutorial found on: https://docs.oracle.com/javase/tutorial/networking/sockets/index.html

The example program implements a client, EchoClient, that connects to an echo server. The echo server receives data from its client and echoes it back. The example EchoServer implements an echo server. (Alternatively, the client can connect to any host that supports the Echo Protocol.)
The EchoClient example creates a socket, thereby getting a connection to the echo server. It reads input from the user on the standard input stream, and then forwards that text to the echo server by writing the text to the socket. The server echoes the input back through the socket to the client. The client program reads and displays the data passed back to it from the server.
Note that the EchoClient example both writes to and reads from its socket, thereby sending data to and receiving data from the echo server.