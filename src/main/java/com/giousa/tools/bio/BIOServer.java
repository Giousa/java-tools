package com.giousa.tools.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    public static void main(String[] args) {
        int port = 9090;

        ServerSocket server = null;
        ExecutorService service = Executors.newFixedThreadPool(50);

        try {
            server = new ServerSocket(port);
            System.out.println("server started!");
            while (true) {
                Socket socket = server.accept();

                service.execute(new Handler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("main捕捉异常，finally执行！");
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server = null;
        }
    }

    static class Handler implements Runnable {
        Socket socket = null;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {

                reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), "UTF-8"));
                writer = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
                String readMessage = null;
                while (true) {
                    System.out.println("server reading... ");
                    if ((readMessage = reader.readLine()) == null) {
                        break;
                    }
                    System.out.println(readMessage);
                    writer.println("server recive : " + readMessage);
                    writer.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Handler捕捉异常，finally执行！");

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                socket = null;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                reader = null;
                if (writer != null) {
                    writer.close();
                }
                writer = null;
            }
        }

    }

}
