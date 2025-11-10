import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BitStuffingClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6789);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter data: ");
        String data = sc.nextLine();
        int cnt = 0;
        String s = "";

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (ch == '1') {
                cnt++;
                if (cnt < 5)
                    s += ch;
                else {
                    s = s + ch + '0';
                    cnt = 0;
                }
            } else {
                s += ch;
                cnt = 0;
            }
        }

        s = "01111110" + s + "01111110";
        System.out.println("Data stuffed in client: " + s);
        System.out.println("Sending to server for unstuffing");
        dos.writeUTF(s);
    }
}
