package dev.dex;

import java.io.*;
import java.net.*;

public class Worker extends Thread {
    private Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = reader.readLine();
                System.out.println("Received client input: " + echoString);
                System.out.println("Input received");
                if (echoString.equals("exit")) {
                    break;
                }

                try {
                    sleep(15000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                writer.println("Echo from server: " + echoString);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
