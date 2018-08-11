from socket import *

serverName = input('write hostname / IP: ')
serverPort = int(input('write port: '))

# AF_INET: means IPv4
# SOCK_STREAM: means TCP socket
clientSocket = socket(AF_INET, SOCK_STREAM)

# establishes a TCP connection between server and client
# which makes the transport layer do a three-way handshake
clientSocket.connect((serverName, serverPort))

message = input('write message: ')

# .encode -> converts to binary
clientSocket.send(message.encode())

# received message with buffer size 1024
modifiedMessage = clientSocket.recv(1024)

print('From server: ', modifiedMessage.decode())
clientSocket.close()

