package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Safe AI Reader:
 * - 防路径穿越：resolve -> normalize -> toRealPath + startsWith(baseDirRealPath)
 * - UTF-8 字符流读取（按行）
 * - 滑动窗口切片：chunkSize + overlapSize
 *
 * 你可以把它放在：Javase06-IO/src/main/java/.../SafeAiReader.java（或你自己的包路径）
 */
public class SafeAiReader {

    private final Path baseDirRealPath;
    private final int chunkSize;
    private final int overlapSize;
    private final int stride;

    public SafeAiReader(Path baseDir, int chunkSize, int overlapSize) {
        Objects.requireNonNull(baseDir, "baseDir must not be null");

        if (chunkSize <= 0) {
            throw new IllegalArgumentException("chunkSize must be positive");
        }
        if (overlapSize < 0 || overlapSize >= chunkSize) {
            throw new IllegalArgumentException("overlapSize must be in [0, chunkSize)");
        }

        try {
            // 这里用 toRealPath：可识别符号链接，拿到真实物理路径（安全边界）
            this.baseDirRealPath = baseDir.toRealPath(LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            throw new AiFileReadException("Failed to resolve baseDir real path: " + baseDir, e);
        }

        this.chunkSize = chunkSize;
        this.overlapSize = overlapSize;
        this.stride = chunkSize - overlapSize;
    }

    /**
     * 读取 baseDir 下的指定文件，并切片返回 chunks
     */
    public List<String> readChunks(String fileName) {
        FileUtils.validateFileName(fileName); // 第一层：输入净化（可选，但推荐）

        try {
            Path safeFile = resolveAndValidate(fileName);
            String content = readAllUtf8Lines(safeFile);
            return chunk(content);
        } catch (IOException e) {
            // 统一异常语义封口：对外是“AI 文件读取失败”
            throw new AiFileReadException("Failed to read AI input file: " + fileName, e);
        }
    }

    /**
     * 将用户输入的 fileName 安全地定位到 baseDir 内的真实文件路径
     */
    private Path resolveAndValidate(String fileName) throws IOException {
        // resolve + normalize：先把 .. 之类折叠
        Path normalized = baseDirRealPath.resolve(fileName).normalize();

        // 真实路径（不跟随符号链接更安全；如果你希望跟随可调整）
        Path real = normalized.toRealPath(LinkOption.NOFOLLOW_LINKS);

        // 最终安全边界：真实路径必须在 baseDirRealPath 内
        if (!real.startsWith(baseDirRealPath)) {
            throw new SecurityException("Path traversal detected: " + fileName);
        }

        if (!Files.isRegularFile(real)) {
            throw new NoSuchFileException("Not a regular file: " + real);
        }

        return real;
    }

    /**
     * UTF-8 按行读取，拼成一个字符串（保留换行）
     */
    private String readAllUtf8Lines(Path file) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (!first) sb.append('\n');
                sb.append(line);
                first = false;
            }
        }

        return sb.toString();
    }

    /**
     * 滑动窗口切片：chunkSize + overlapSize
     * - content 为空：返回空 list
     * - content 小于 chunkSize：返回单块
     * - 末尾不足 chunkSize：按剩余长度输出最后一块
     */
    private List<String> chunk(String content) {
        List<String> chunks = new ArrayList<>();
        if (content == null || content.isEmpty()) return chunks;

        int n = content.length();
        if (n <= chunkSize) {
            chunks.add(content);
            return chunks;
        }

        int start = 0;
        while (start < n) {
            int end = Math.min(start + chunkSize, n);
            chunks.add(content.substring(start, end));
            if (end == n) break;
            start += stride;
        }

        return chunks;
    }

    // ===== 你可以保留一个最小 main 用于快速验证（可删） =====
    public static void main(String[] args) {
        // 运行前：准备一个目录 data 和文件 data/example.txt
        SafeAiReader reader = new SafeAiReader(Paths.get("data"), 500, 100);
        List<String> chunks = reader.readChunks("example.txt");
        System.out.println("chunks=" + chunks.size());
        for (int i = 0; i < Math.min(chunks.size(), 3); i++) {
            System.out.println("---- chunk " + i + " ----");
            System.out.println(chunks.get(i));
        }
    }
}

/**
 * 业务语义异常：AI 文件读取失败
 */
class AiFileReadException extends RuntimeException {
    public AiFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}

