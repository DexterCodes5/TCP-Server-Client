package dev.dex;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // This computer's IP = 127.0.0.1
        try (Socket socket = new Socket("localhost", 5000)) {
            socket.setSoTimeout(5000);

            BufferedReader receiveFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter sendToServer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.print("Enter string to be sent to server: ");
                echoString = scanner.nextLine();
                sendToServer.println(echoString);
                if (!echoString.equals("exit")) {
                    response = receiveFromServer.readLine();
                    System.out.println(response);
                }
            } while (!echoString.equals("exit"));

        } catch (SocketTimeoutException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            System.out.println("Client Exception");
            ex.printStackTrace();
        }

    }
}
