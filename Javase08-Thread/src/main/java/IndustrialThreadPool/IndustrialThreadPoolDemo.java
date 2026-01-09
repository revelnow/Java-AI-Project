package IndustrialThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class IndustrialThreadPoolDemo {
    public static void main(String[] args) {
        // 1. 自定义线程工厂：解决“日志难排查”的问题 (Layer 4: Naming)
        ThreadFactory aiFactory = new ThreadFactory() {
            private final AtomicInteger count = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                // 为每个 AI 线程起一个有意义的名字
                return new Thread(r, "AI-Agent-Worker-" + count.getAndIncrement());
            }
        };

        // 2. 构建工业级线程池 (Layer 5: Scheduling)
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                          // 核心工位
                5,                          // 最大工位
                60, TimeUnit.SECONDS,        // 临时工空闲 60s 后裁员
                new ArrayBlockingQueue<>(5), // 队列定死，防止 OOM (Layer 2: Queue)
                aiFactory,
                new ThreadPoolExecutor.CallerRunsPolicy() // 【关键】背压机制：谁提交谁执行
        );

        // 3. 提交任务：模拟 AI 并发请求
        for (int i = 1; i <= 12; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 正在处理 AI 任务 #" + taskId);
                    Thread.sleep(2000); // 模拟耗时 2s 的 AI 生成 (Layer 5: Sleep)
                } catch (InterruptedException e) {
                    // Layer 1: 妥善处理中断异常
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 4. 优雅停机 (Graceful Shutdown)
        // 不再接收新任务，但会把队列里的干完
        executor.shutdown();
        try {
            // 最多等待 30 秒，如果还没干完就强行关闭
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("--- 所有 AI 任务处理完毕，线程池已安全关闭 ---");
    }
}