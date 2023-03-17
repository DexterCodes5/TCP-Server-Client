package dev.dex;

import java.io.*;
import java.net.*;

public class Main {

    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected");
                new Worker(socket).start();
            }

        } catch (IOException ex) {
            System.out.println("Server Exception");
            ex.printStackTrace();
        }
    }
}

class WorkerRunnable implements Runnable {
    private Socket socket;

    public WorkerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String echoString = input.readLine();
                System.out.println("Input received");
//                try {
//                    sleep(15000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }

                if (echoString.equals("exit")) {
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
