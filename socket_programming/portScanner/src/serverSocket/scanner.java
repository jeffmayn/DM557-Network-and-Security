package serverSocket;

import java.net.ServerSocket;

public class scanner {

    public static void main(String []args){
        int port;

        for (port = 1; port <= 1024; port++){
            try {
                ServerSocket s = new ServerSocket(port);

            } catch (Exception ex){
                System.out.println("Server on port: " + port);

            }
        }
    }
}
