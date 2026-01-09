package etl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataCleaner {
    public static void main(String[] args) {
        // 模拟从文件读取的原始脏数据
        List<String> rawData = Arrays.asList(
                "  Java并发编程  ",
                "",
                "AI Agent开发",
                "Java并发编程", // 与第一行去空格后重复
                "    ",           // 只有空格的脏数据
                "机器学习基础"
        );

        // 使用 Stream API 进行“数据脱敏与清洗”
        List<String> cleanedData = rawData.stream()
                .filter(Objects::nonNull)        // 防空：防止 NPE 崩溃
                .map(String::trim)               // 转换：首尾去空格
                .filter(line -> !line.isEmpty()) // 过滤：去掉纯空格行
                .map(String::toLowerCase)        // 标准化：统一小写方便去重
                .distinct()                      // 去重：确保知识唯一
                .limit(100)                      // 截断：只取前100条（防止一次性塞爆 AI）
                .collect(Collectors.toList());
        // 输出清洗结果
        System.out.println("清洗后的知识库：");
        cleanedData.forEach(System.out::println);
    }
}