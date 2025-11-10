import java.io.*;
import java.util.*;
import java.net.*;

public class SenderByte {
    public static void main(String args[]) throws IOException {
        InetAddress ip = InetAddress.getLocalHost();
        int port = 45678;
        Scanner sc = new Scanner(System.in);

        Socket s = new Socket(ip, port);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        while (true) {
            System.out.println("Enter the Message to be Sent: ");
            String data = sc.nextLine();
            String res = "F";

            for (int i = 0; i < data.length(); i++) {
                if (data.charAt(i) == 'F' || data.charAt(i) == 'E') {
                    res = res + "E" + data.charAt(i);
                } else {
                    res = res + data.charAt(i);
                }
            }

            res = res + "F";
            System.out.println("The data being sent (with byte stuffing): " + res);
            dos.writeUTF(res);
            System.out.println("Sending Message...");
            System.out.println("Thanks for the Feedback Server!!");
            break;
        }

        dis.close();
        dos.close();
        s.close();
        sc.close();
    }
}
