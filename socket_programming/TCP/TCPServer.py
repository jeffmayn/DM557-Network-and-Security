from socket import *

serverPort = int(input('type port which server should listen on: '))
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))

# makes the server listen for TCP connection requests from client
# parameter specifies the maximum number of queued connections
serverSocket.listen(1)

print('Server is listening ..')

while True:
	connectionSocket, addr = serverSocket.accept()
	message = connectionSocket.recv(1024).decode()
	
	print('from client ' 
	+ addr[0] 
	+ ':' + str(addr[1]) 
	+ ' ' + message)


	capMessage = message.upper()
	connectionSocket.send(capMessage.encode())
	connectionSocket.close()
