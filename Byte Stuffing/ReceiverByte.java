import java.io.*;
import java.net.*;

public class ReceiverByte {
    public static void main(String[] args) throws IOException {
        ServerSocket servsock = new ServerSocket(45678);
        System.out.println("Receiver is waiting for connection...");
        Socket socket = servsock.accept();
        System.out.println("Connection established with Sender!");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true) {
            String res = dis.readUTF();
            System.out.println("Message Received... Successfully!!!");
            System.out.println("The Stuffed Message is: " + res);

            String out = "";
            for (int i = 1; i < res.length() - 1; i++) {
                char ch = res.charAt(i);
                if (ch == 'E') {
                    i++;
                    out = out + res.charAt(i);
                } else {
                    out = out + ch;
                }
            }

            System.out.println("The Destuffed Message is: " + out);
            dos.writeUTF("success");

            if (out.equalsIgnoreCase("bye")) {
                System.out.println("Messaging is over... EXITING");
                break;
            }
        }

        socket.close();
        dis.close();
        dos.close();
        servsock.close();
    }
}
