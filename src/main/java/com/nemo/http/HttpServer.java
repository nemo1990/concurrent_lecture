package com.nemo.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8888);

        ExecutorService service = Executors.newCachedThreadPool();


        while (true) {
            Socket socket = serverSocket.accept();
            service.execute(new HttpTask(socket));
        }
    }
}

class HttpTask implements Runnable {

    private Socket socket;

    public HttpTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream ins = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            int len = 0;
            while (len == 0) {
                len = ins.available();
            }
            System.out.println("长度 " + len);

            byte[] bytes = new byte[len];
            ins.read(bytes);
            System.out.println(new String(bytes));

            ins.close();
            os.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

