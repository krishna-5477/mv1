import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("UDP Server started. Waiting for messages...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected. Server shutting down...");
                    break;
                }

                String reply = "Received: " + clientMessage;
                sendData = reply.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
