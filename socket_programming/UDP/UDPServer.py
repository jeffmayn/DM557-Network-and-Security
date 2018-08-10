from socket import *

serverPort = int(input('type port which server should listen on: '))
serverSocket = socket(AF_INET, SOCK_DGRAM)

# assigns the user applied port number to the server's socket
serverSocket.bind(('', serverPort))

print("Server is running ...")

while True:
	message, clientAddress = serverSocket.recvfrom(2048)
	
	# clientAddress is a tuple with (host, port)
	print('from client ' 
	+ clientAddress[0] 
	+ ':' + str(clientAddress[1]) 
	+ ' ' + message.decode())

	modifiedMessage = message.decode().upper()
	serverSocket.sendto(modifiedMessage.encode(), clientAddress)
