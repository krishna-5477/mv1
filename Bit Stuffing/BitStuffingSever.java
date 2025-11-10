import java.io.*;
import java.net.*;

public class BitStuffingServer {
    public static void main(String[] args) throws IOException {
        int port = 6789;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number. Using default port 6789.");
            }
        }

        ServerSocket skt = new ServerSocket(port);
        System.out.println("Server started on port: " + port);
        Socket socket = skt.accept();

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String s = dis.readUTF();
        System.out.println("Stuffed data from client: " + s);
        System.out.println("Unstuffed data: ");

        int cnt = 0;
        for (int i = 8; i < s.length() - 8; i++) {
            char ch = s.charAt(i);
            if (ch == '1') {
                cnt++;
                System.out.print(ch);
                if (cnt == 5) {
                    i++;
                    cnt = 0;
                }
            } else {
                System.out.print(ch);
                cnt = 0;
            }
        }

        System.out.println();
        socket.close();
        skt.close();
    }
}
