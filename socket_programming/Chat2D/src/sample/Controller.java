package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;


public class Controller {

    @FXML
    private TextField hostName;
    @FXML
    private TextField portNumber;
    @FXML
    private TextField chatField;

    private FxSocketClient socket;



    public void OnButtonClicked(){
        String host = hostName.getText();
        int port = Integer.parseInt(portNumber.getText());

        run("jeff", host, port, 5000);
    }

    @FXML
    public void ButtonSendMessage(ActiveEvent event){
        socket.
        String message = chatField.getText();
        System.out.println(message);

    }


    public static void run(String username, String host, int port, int timeOut){
        try(Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(timeOut);
            BufferedReader echoes = new BufferedReader(new InputStreamReader((socket.getInputStream())));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);


            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;


            do {
                System.out.print(username + ": ");


                echoString = scanner.nextLine();

                stringToEcho.println(echoString);
                if (!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println("[server reply]: " + response);
                }
            } while (!echoString.equals("exit"));
        } catch (SocketTimeoutException e){
            System.out.println("The socket timed out");

        } catch (IOException e){
            System.out.println("Client error: " + e.getMessage());
        }
    }

    public static String user(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Pick a username: ");
        String username = sc.nextLine();


        return username;
    }

}
