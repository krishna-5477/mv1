import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        Socket clientSocket = null;
        BufferedReader consoleReader = null;
        BufferedReader serverReader = null;
        PrintWriter out = null;

        try {
            clientSocket = new Socket("127.0.0.1", 9222);
            System.out.println("Connected to server.");

            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            do {
                System.out.print("Client: ");
                line = consoleReader.readLine();
                out.println(line);

                String response = serverReader.readLine();
                System.out.println("Server response: " + response);

                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
            } while (line != null);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (consoleReader != null) consoleReader.close();
                if (serverReader != null) serverReader.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
