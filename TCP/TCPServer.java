import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9222)) {
            System.out.println("Server started on port 9222. Waiting for client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Client: " + message);
                if (message.equalsIgnoreCase("quit")) {
                    out.println("Goodbye!");
                    break;
                }
                out.println("Server received: " + message);
            }

            clientSocket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
