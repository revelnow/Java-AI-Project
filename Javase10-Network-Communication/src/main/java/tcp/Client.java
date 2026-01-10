package tcp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Client {

    public static void main(String[] args) throws IOException {
        // 1) 文件路径参数化：优先用 args[0]，否则用相对路径 data/1.jpg
        Path filePath = (args.length > 0)
                ? Paths.get(args[0])
                : Paths.get("data", "1.jpg");

        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new FileNotFoundException("文件不存在或不是普通文件: " + filePath.toAbsolutePath());
        }

        // 2) host/port 参数化（可选）：args[1]=host, args[2]=port
        String host = (args.length > 1) ? args[1] : "localhost";
        int port = (args.length > 2) ? Integer.parseInt(args[2]) : 8899;

        // 3) try-with-resources：保证 socket/流在异常时也能正确关闭
        try (Socket socket = new Socket(host, port);
             InputStream serverIn = socket.getInputStream();
             OutputStream serverOut = socket.getOutputStream();
             InputStream fileIn = Files.newInputStream(filePath)) {

            byte[] buffer = new byte[8192];
            int len;
            while ((len = fileIn.read(buffer)) != -1) {
                serverOut.write(buffer, 0, len);
            }
            socket.shutdownOutput();

            // 4) 读取服务端 ACK（UTF-8）
            byte[] ackBuf = new byte[1024];
            int ackLen = serverIn.read(ackBuf);
            if (ackLen == -1) {
                System.out.println("服务端未返回ACK（连接已关闭）");
            } else {
                System.out.println(new String(ackBuf, 0, ackLen, StandardCharsets.UTF_8));
            }
        }
    }
}
