from socket import *
import sys # In order to terminate the program

serverSocket = socket(AF_INET, SOCK_STREAM)
#Prepare a sever socket
#Fill in start

serverPort = int(input('write port which server should listen on: '))
serverSocket.bind(('', serverPort))
serverSocket.listen(1)

#Fill in end

while True:
	#Establish the connection
	print('Ready to serve...')
	connectionSocket, addr = serverSocket.accept()
	
	try:
		message = connectionSocket.recv(1024)               
		filename = message.split()[1]                 
		f = open(filename[1:])                        
		outputdata = f.read()                   
		#Send one HTTP header line into socket
		#Fill in start
		connectionSocket.send("HTTP/1.1 200 OK\r\n\r\n".encode())
		#Fill in end                
	#Send the content of the requested file to the client
		for i in range(0, len(outputdata)):           
			connectionSocket.send(outputdata[i].encode())
		connectionSocket.send("\r\n\r\n".encode())
		connectionSocket.close()
	except IOError:
		#Send response message for file not found
		#Fill in start        
		#Fill in end
		#Close client socket
		#Fill in start
		#Fill in end            
		serverSocket.close()
sys.exit()
