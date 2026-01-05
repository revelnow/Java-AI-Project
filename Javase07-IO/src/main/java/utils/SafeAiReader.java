package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 工业级 AI 知识库读取器
 * 审计重点：路径穿越防护、UTF-8 字符安全、重叠切片逻辑
 */
public class SafeAiReader {

    public static List<String> readAndChunk(String userInputPath, int chunkSize, int overlapSize) throws IOException {




        // 1. 路径隔离审计 (Sandboxing)
        Path baseDir = Paths.get("E:/Java-AI-Project/data/kb").toRealPath();
        Path targetPath = baseDir.resolve(userInputPath).normalize();

        if (!targetPath.startsWith(baseDir)) {
            // 对内记录详细日志，对外抛出模糊异常
            System.err.println("[SECURITY] 拦截到越权访问尝试: " + targetPath);
            throw new SecurityException("非法的文件访问请求");
        }

        List<String> chunks = new ArrayList<>();

        // 2. 使用 try-with-resources 确保资源关闭
        // 使用 BufferedReader + InputStreamReader 确保中文不乱码
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(targetPath.toFile()), StandardCharsets.UTF_8))) {

            char[] buffer = new char[chunkSize - overlapSize];
            String overlapContent = "";
            int len;

            while ((len = br.read(buffer)) != -1) {
                // 3. 滑动窗口切片逻辑 (Sliding Window)
                String currentChunk = overlapContent + new String(buffer, 0, len);
                chunks.add(currentChunk);

                // 更新接力棒，用于下一个块的上下文保留
                if (currentChunk.length() > overlapSize) {
                    overlapContent = currentChunk.substring(currentChunk.length() - overlapSize);
                }
            }
        }
        return chunks;
    }

    public class AiFileReadException extends RuntimeException {
        public AiFileReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}