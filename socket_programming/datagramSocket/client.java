import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class client {

  public static void main(String[] args){

    run();

  }

  public static void run(){
        try {
            InetAddress address = InetAddress.getLoopbackAddress();
            Integer serverPort = 12000;

            DatagramSocket socket = new DatagramSocket();

            System.out.println("UDPClient: We fake the input of a message: 'Hello DM557!'\n");
            String message = "'Hello DM557! (UDP)'";

            byte[] buf = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, serverPort);
            socket.send(packet);

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());

            System.out.println("UDPClient: Received:" + received);
        } catch( IOException exception) {
            System.out.println("UDPClient: Error in client: " + exception.getMessage());
    }
  }

}
