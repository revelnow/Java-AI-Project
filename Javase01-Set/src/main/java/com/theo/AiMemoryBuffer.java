package com.theo;

import java.util.LinkedHashSet;
import java.util.Set;

public class AiMemoryBuffer {
    /*
    假设你正在开发一个 AI Agent，你需要一个 ChatBuffer。它必须满足：
         有序性：保证对话历史顺序。
         唯一性：如果用户重复发送了同样的消息，记忆池应该能识别并去重（通过自定义逻辑）。
     */
    public static void main(String[] args) {
        AiMemoryBuffer buffer = new AiMemoryBuffer();
        buffer.pushMessage("你好，我是用户");
        buffer.pushMessage("帮我写个代码");
        buffer.pushMessage("你好，我是用户"); // 重复输入

        // 预期输出：后两个，且“你好，我是用户”排在最后（因为它是最新被提及的
        buffer.getMemory().forEach(System.out::println);



    }
    // 使用 LinkedHashSet 来保证有序性和唯一性
    Set<String> memory = new LinkedHashSet<>();

    public void pushMessage(String message) {
        /**
         * 添加对话。如果对话内容已存在，则先删除旧的，再存入新的，
         * 这样可以保证最近提到的信息排在后面（类似于 LRU 缓存雏形）。
         */
        if (memory.contains(message)) {
            memory.remove(message);
        }
        memory.add(message);
    }
    public Set<String> getMemory() {
        /**
         * 获取当前的对话记忆
         */
        return memory;
    }
}

