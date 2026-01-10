package tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class PoolServer {
    public static void main(String[] args) throws Exception {
        int port = 8899;
        Path saveDir = Paths.get("data", "uploads");

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2, 4, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("PoolServer listening on " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                pool.execute(new FileHandler(socket, saveDir));
            }
        }
    }
}
