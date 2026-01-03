package com.theo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AiDataStreamer {
    /*
    AI 生成数据的清洗（ETL）
       假设 AI 给你返回了一堆原始的标签数据，但其中混入了空字符串、重复项，且格式不统一。我们需要：
        1.过滤掉长度不足 2 的无效标签。
        2.去除前后空格并统一转为大写。
        3.去重。
        4.排序后存入列表。
     */
    public static void main(String[] args) {
        // 模拟来自 AI 的原始、杂乱的数据输出
        List<String> rawAiOutput = Arrays.asList(" java ", " ", "ai ", "agent", "java", "rag ", " prompt", "ai");

        System.out.println("--- 原始数据 ---");
        System.out.println(rawAiOutput);

        // 使用 Stream 流进行工业级清洗
        List<String> cleanedData = rawAiOutput.stream()
                .map(String::trim)                       // 1. 去除空格
                .filter(s -> s.length() >= 2)      // 2. 过滤掉长度小于2的（如空串或单字符）
                .map(String::toUpperCase)                // 3. 统一转大写
                .distinct()                              // 4. 去重（底层靠的是之前学的 Set 原理）
                .sorted()                                // 5. 自然排序
                .toList();                               // 6. 收集结果

        System.out.println("\n--- 清洗后的 AI 核心关键词 ---");
        cleanedData.forEach(System.out::println);
    }
}
