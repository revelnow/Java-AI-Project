package resilience;

import java.util.concurrent.Callable;

public class RetryUtils {

    // 设置最大延迟天花板：30秒
    private static final long MAX_DELAY = 30000;

    public static <T> T execute(Callable<T> task, int maxAttempts, long initialDelay) throws Exception {
        int count = 0;
        long currentDelay = initialDelay;

        while (count < maxAttempts) {
            try {
                // 成功则直接返回，方法结束
                return task.call();
            } catch (Exception e) {
                count++;
                // 如果次数用尽，直接把最后的异常甩出去
                if (count >= maxAttempts) {
                    System.err.println("【最终告警】重试次数耗尽，任务失败！");
                    throw e;
                }

                System.out.println("【异常捕捉】第 " + count + " 次失败，" + currentDelay + "ms 后重试...");

                try {
                    // 线程休眠，等待重试
                    Thread.sleep(currentDelay);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new Exception("任务被中断，停止重试");
                }

                // 指数翻倍，但不能超过天花板
                currentDelay = Math.min(currentDelay * 2, MAX_DELAY);
            }
        }
        throw new Exception("未知逻辑错误");
    }
}