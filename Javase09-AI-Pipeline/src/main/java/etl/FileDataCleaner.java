package etl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileDataCleaner {

    /**
     * @param inputPath  输入文件
     * @param outputPath 输出文件（若不存在会创建；若存在会覆盖）
     * @param deduplicate 是否去重（大文件慎用：distinct会占内存）
     */
    public static void cleanFile(Path inputPath, Path outputPath, boolean deduplicate) throws IOException {
        if (inputPath == null || outputPath == null) {
            throw new IllegalArgumentException("inputPath/outputPath 不能为空");
        }
        if (!Files.exists(inputPath) || !Files.isRegularFile(inputPath)) {
            throw new NoSuchFileException("输入文件不存在或不是普通文件: " + inputPath);
        }

        // 确保输出目录存在
        Path parent = outputPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        // try-with-resources: 关闭 Stream + Writer
        try (Stream<String> lines = Files.lines(inputPath, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(
                     outputPath,
                     StandardCharsets.UTF_8,
                     StandardOpenOption.CREATE,
                     StandardOpenOption.TRUNCATE_EXISTING
             )) {

            Stream<String> pipeline = lines
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase);

            if (deduplicate) {
                pipeline = pipeline.distinct();
            }

            // 一行一行写出，不把全量结果存内存
            pipeline.forEachOrdered(s -> {
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (IOException e) {
                    // Stream forEach 里不能直接 throws，只能包一层 unchecked
                    throw new RuntimeException("写入失败: " + outputPath, e);
                }
            });
        } catch (RuntimeException e) {
            // 把 forEach 内部的 RuntimeException 解包成 IOException（便于上层处理）
            if (e.getCause() instanceof IOException io) {
                throw io;
            }
            throw e;
        }
    }

    // 最小可运行 demo
    public static void main(String[] args) throws IOException {
        Path in = Paths.get("data", "raw.txt");
        Path out = Paths.get("data", "clean.txt");

        cleanFile(in, out, false); // 大文件默认 false
        System.out.println("清洗完成 => " + out.toAbsolutePath());
    }
}
