package nanan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    private JButton BtnSend;
    private JPanel panel1;
    private JTextField hostText;
    private JTextField portText;
    private JLabel portLabel;
    private JLabel hostLabel;
    private JScrollPane chatField;
    private JButton connectButton;
    private JTextArea chat;
    private JTextField message;

    private String host;
    private int port;




    public EchoClient() {

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                host = hostText.getText();
                port = Integer.parseInt(portText.getText());

                if (connectButton.getText() == "Connect"){
                    try (

                        // creates new socket object and applies IP and Port number to it.
                        Socket echoSocket = new Socket(host, port);

                        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader( new InputStreamReader(echoSocket.getInputStream()) );
                        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in) )


                    ){
                        chat.setText(chat.getText() + "[SYSTEM]: Connected to: " + host + ":" + port + "\n");
                        connectButton.setText("Disconnect");




                    } catch (UnknownHostException u) {
                        System.err.println("Don't know about host " + host);
                      //  System.exit(1);
                    } catch (IOException u) {
                        chat.setText(chat.getText() + "[SYSTEM]: Couldn't connect to: " + host + ":" + port + "\n");
                        System.err.println("Couldn't get I/O for the connection to " +
                                host);
                       // System.exit(1);
                    }


                } else {

                    chat.setText(chat.getText() + "[SYSTEM]: Connection closed! " + "\n");
                    connectButton.setText("Connect");
                    // TODO: close connection

                }
            }

        });
        BtnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectButton.getText() == "Disconnected"){

                    chat.setText(chat.getText() + "[SYSTEM]: You are not connected to anybody :-(\n");
                } else {





                }
            }
        });
    }



    public static void main(String[] args){
        JFrame frame = new JFrame("Chat");
        frame.setContentPane(new EchoClient().panel1);
        frame.setPreferredSize(new Dimension(435, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);



    }



    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}
