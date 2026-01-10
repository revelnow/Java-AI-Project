package tcp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler implements Runnable {
    private final Socket socket;
    private final Path saveDir;

    public FileHandler(Socket socket, Path saveDir) {
        this.socket = socket;
        this.saveDir = saveDir;
    }

    public FileHandler(Socket socket) {
        this(socket, Paths.get("data", "uploads"));
    }

    @Override
    public void run() {
        // 每个连接处理一次上传：读入 -> 写出 -> 回ACK
        try (Socket s = socket;
             InputStream in = s.getInputStream();
             OutputStream out = s.getOutputStream()) {

            Files.createDirectories(saveDir);

            String filename = "upload_" + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS")) + ".bin";
            Path target = saveDir.resolve(filename);

            try (OutputStream fileOut = Files.newOutputStream(
                    target, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                byte[] buf = new byte[8192];
                int len;
                while ((len = in.read(buf)) != -1) {
                    fileOut.write(buf, 0, len);
                }
            }

            out.write(("OK: saved to " + target.toString()).getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            // 这里先用 RuntimeException 包装，后续你可以升级成自定义 NetException
            throw new RuntimeException("FileHandler 处理失败: " + e.getMessage(), e);
        }
    }
}
