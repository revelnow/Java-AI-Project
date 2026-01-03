package com.theo;

import java.util.HashMap;
import java.util.Map;

public class PromptManager {
    /*
    构建一个 **AI 提示词模板仓库**
     */
    public static void main(String[] args) {
        // 1. 定义一个 Map：Key 是人设名称（String），Value 是提示词内容（String）
        Map<String, String> promptRepo = new HashMap<>();

        // 2. 存入数据 (put)
        promptRepo.put("翻译官", "你是一个精通多国语言的翻译官，请帮我翻译以下内容...");
        promptRepo.put("程序员", "你是一个资深架构师，请帮我审查这段 Java 代码...");
        promptRepo.put("诗人", "你是一个浪漫主义诗人，请为我创作一首现代诗...");

        // 3. 获取数据 (get) - $O(1)$ 级别的极速查找
        String role = "程序员";
        String content = promptRepo.get(role);

        System.out.println("【" + role + "】的提示词是：\n" + content);

        // 4. 检查是否存在某个 Key
        if (promptRepo.containsKey("厨师")) {
            System.out.println("找到厨师模板");
        } else {
            System.out.println("目前还没有厨师的模板，正在添加...");
            promptRepo.put("厨师", "你是一个米其林三星主厨...");
        }

        // 5. 遍历所有模板 (体验 Map 的遍历方式)
        System.out.println("\n--- 当前所有 AI 模板清单 ---");
        for (String key : promptRepo.keySet()) {
            System.out.println("角色: " + key);
        }
    }
}