# 📅 2026.01.02: 异常处理与 AI 防御性编程

## 🎯 今日目标
- [x] 掌握异常体系结构（Exception vs Error） 
- [x] 实现 AI 接口调用的 **指数退避重试 (Exponential Backoff)** 逻辑 
- [x] 理解并处理 `InterruptedException`，确保线程中断状态正确恢复 

---

## 💡 核心洞察 (Insight)
* **为何 AI 场景必须重试？**：大模型 API（如 OpenAI, DeepSeek）极不稳定，常因网络抖动或并发限制抛出异常。 
* **指数退避的精髓**：重试等待时间按 1s, 2s, 4s 增长，目的是给服务端“喘息”时间，避免频繁请求导致 IP 被封禁。 
* **捕获准则**：严禁捕获 `Throwable` 或 `Error`。在业务逻辑中，应专注于捕获 `Exception` 及其子类。 

---

## 🛠️ 关键代码实现：指数退避重试工具类

```java
public class RetryUtils {
    public static void executeWithRetry(Runnable task) {
        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                task.run(); // 执行模拟任务
                return; // 成功则退出
            } catch (AiServiceException e) {
                retryCount++;
                if (retryCount >= maxRetries) throw e;

                // 指数退避计算：1s, 2s, 4s
                long waitTime = (long) Math.pow(2, retryCount - 1) * 1000;
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException ie) {
                    // 核心细节：恢复中断状态，体现工程素养
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
```

## 🎙️ 面试高频考点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
| --- | --- |
| 为什么不建议捕获 Throwable？ | Throwable 包含 Error。如果不处理致命错误（如 OOM）而继续运行，系统会进入不可控状态。|
| Thread.sleep 为什么要处理中断？ | 线程在阻塞时可能被外部终止。捕获后必须调用 Thread.currentThread().interrupt() 恢复标志位。|
| Thread.sleep 为什么要处理中断？ | 线程在阻塞时可能被外部终止。捕获后必须调用 Thread.currentThread().interrupt() 恢复标志位。 |
| 重试策略如何优化？ | 除了指数退避，还应引入 Jitter（随机抖动），防止大量客户端同时发起重试产生“惊群效应”。 |


