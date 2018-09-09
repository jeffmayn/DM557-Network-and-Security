package serverSocket;

import java.io.IOException;
import java.net.ServerSocket;

public class server {

    public static void main(String []args){

        try {
            ServerSocket s = new ServerSocket(435);
            System.out.println("Server is running on port: " + s.getLocalPort());
        } catch (IOException ex){
            System.err.println(ex);
        }
    }
}
