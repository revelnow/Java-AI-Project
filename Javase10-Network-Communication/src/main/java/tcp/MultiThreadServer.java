package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
    public static void main(String[] args) {
        System.out.println("多线程服务器启动，支持多人同时上传...");

        try (ServerSocket serverSocket = new ServerSocket(8899)) {
            while (true) {
                // 老板在门口等
                Socket socket = serverSocket.accept();
                System.out.println("检测到新客人，立刻分配分身处理！");

                // 每来一个人，就启动一个新线程（分身）去处理
                // 这个 new Thread().start() 就像是瞬间变出一个新员工
                new Thread(new FileHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


