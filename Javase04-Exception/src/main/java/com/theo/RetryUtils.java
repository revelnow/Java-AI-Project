package com.theo;

public class RetryUtils {

    /**
     * 执行带有指数退避重试逻辑的任务
     * @param task 模拟的 AI 请求任务
     */
    public static void executeWithRetry(Runnable task) {
        int maxRetries = 3; // 最高重试 3 次
        int retryCount = 0;
        boolean success = false;

        while (retryCount < maxRetries && !success) {
            try {
                task.run();
                success = true; // 如果执行成功，跳出循环
                System.out.println("AI 请求成功！");
            } catch (AiServiceException e) {
                retryCount++;
                System.err.println("请求失败（第 " + retryCount + " 次）: " + e.getMessage());

                if (retryCount >= maxRetries) {
                    System.err.println("已达到最大重试次数，放弃请求。");
                    throw e;
                }

                // 指数退避逻辑：计算等待时间（1s, 2s, 4s...）
                long waitTime = (long) Math.pow(2, retryCount - 1) * 1000;
                System.out.println("等待 " + (waitTime / 1000) + " 秒后重试...");

                try {
                    // 关键线程知识点：使当前线程进入阻塞状态
                    Thread.sleep(waitTime);
                } catch (InterruptedException ie) {
                    // 正确处理中断：恢复线程的中断状态
                    System.err.println("重试被中断！");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}