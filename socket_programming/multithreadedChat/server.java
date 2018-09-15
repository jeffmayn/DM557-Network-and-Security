package socket_programming.multithreadedChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class server extends Thread {


    private Socket socket;

    public server(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String messageDate = "[" + (new Date()).toString() + "] ";
                String clientIP = socket.getRemoteSocketAddress().toString();
                String echoString = input.readLine();

                System.out.println(messageDate + "[" + clientIP + "] " + "[message:" + echoString + "]");
                if(echoString.equals("exit")){
                    break;
                }
                output.println(echoString);
            }



        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // todo: nugg
            }
        }
    }
}
