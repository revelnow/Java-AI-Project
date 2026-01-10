package tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 8899;
        Path saveDir = Paths.get("data", "uploads");

        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server listening on " + port);
            Socket socket = ss.accept();
            new FileHandler(socket, saveDir).run(); // 单线程直接 run
        }
    }
}
