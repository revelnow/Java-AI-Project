package contex;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class ChatContextManager {
    // 1. 核心容器：存放对话内容
    private final LinkedList<String> window = new LinkedList<>();
    // 2. 窗口大小限制：只留最近 5 条
    private final int MAX_SIZE = 5;
    // 3. 显式锁：保证多线程安全
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 向窗口添加新消息
     */
    public void addMessage(String msg) {
        lock.lock(); // 加锁，就像进屋把门反锁
        try {
            // 如果窗口满了，删掉最老的一条消息（头部）
            if (window.size() >= MAX_SIZE) {
                String removed = window.removeFirst();
                System.out.println("【系统降噪】已移除最旧上下文: " + removed);
            }
            // 添加新消息到末尾
            window.addLast(msg);
            System.out.println("【系统记录】新消息入窗: " + msg);
        } finally {
            lock.unlock(); // 必须在 finally 里解锁，防止出异常了门还锁着
        }
    }

    /**
     * 获取当前所有上下文（用于发给 AI）
     */
    public String getFullContext() {
        lock.lock();
        try {
            return String.join("\n", window);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ChatContextManager manager = new ChatContextManager();

        // 模拟多线程并发发送消息（用户和系统助手同时写历史）
        for (int i = 1; i <= 8; i++) {
            final int id = i;
            new Thread(() -> {
                manager.addMessage("消息内容编号：" + id);
            }).start();
        }
    }
}