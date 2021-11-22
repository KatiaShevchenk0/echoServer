package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket; //для спілкування
    private static ServerSocket server; //сервер сокет
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args){
        while (true) {
                try {
                    server = new ServerSocket(4005);
                    System.out.println("Start!");
                    clientSocket = server.accept();

                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                        String word = in.readLine();
                        System.out.println(word);

                        if (word.equals("stop")) {
                            System.out.println("Finish!");
                            break;
                        }
                        out.write("Echo : " + word + "\n");
                        out.flush();
                    } finally {
                        clientSocket.close();
                        in.close();
                        out.close();
                        server.close();
                    }
                } catch (IOException e) {
                    System.err.println(e);
                }
        }

    }
}



