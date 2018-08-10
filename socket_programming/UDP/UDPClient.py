from socket import *

serverName = input('write hostname / IP: ')
serverPort = int(input('write port: '))

# AF_INET: means IPv4
# SOCK_DGRAM: means UDP socket
clientSocket = socket(AF_INET, SOCK_DGRAM)
message = input('write message: ')

# .sendto() -> attaches the dest. address to the message
# .encode() -> encode to bytes
clientSocket.sendto(message.encode(), (serverName, serverPort))

# a packets data is put into modifiedMessage
# a packets address is put into serverAddress (IP and port)
# recvfrom takes buffer size 2048
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)

print(modifiedMessage.decode())
clientSocket.close()


