package com.mageets.tcpinsteadofhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    ServerSocket serverSocket;

    public TcpServer() {
        System.out.println("I shouldn't see this!!!!");
        start();
    }

    public void start() {
        System.out.println("Starting the server....");
        try {
            serverSocket = new ServerSocket(9000);
            while (true) {
                new ClientHandler(serverSocket.accept())
                        .run();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintStream out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                System.out.println();
                out = new PrintStream(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while((inputLine = in.readLine()) != null) {
                    System.out.printf("Message from client...%s%n", inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
