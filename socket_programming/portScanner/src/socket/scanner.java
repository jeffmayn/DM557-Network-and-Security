//package socket;

import java.net.InetSocketAddress;
import java.net.Socket;

public class scanner {

    public static void main(String []args){
        int timeout = 1;
        int port;
        String host = "127.0.0.1";

        for (port = 1; port <= 1024; port++){
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(host, port), timeout);
                s.close();
                System.out.println("Port: " + port + " is open");

            } catch (Exception ex){
               // System.out.println("Port: " + port + " is closed");

            }
        }
    }
}
