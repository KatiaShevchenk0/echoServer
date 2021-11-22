package com.company;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args){
        while (true) {
            try {
                try {
                    clientSocket = new Socket("localhost", 4005);
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    System.out.println("When you say 'stop' - you exit. Say something:");
                    String word = reader.readLine();

                    out.write(word + "\n");
                    out.flush();

                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                    if (Objects.equals(word, "stop")) {
                        System.out.println("Client close.");
                        break;

                    }
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }

        }

    }
}