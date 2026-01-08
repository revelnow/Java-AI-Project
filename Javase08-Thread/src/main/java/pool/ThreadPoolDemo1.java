package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {
    /*
        JDK自带的线程池 (了解)

        Executors 中提供静态方法来创建线程池

        1. static ExecutorService newCachedThreadPool()     创建一个默认的线程池
        2. static newFixedThreadPool(int nThreads)          创建一个指定最多线程数量的线程池
     */
    public static void main(String[] args) {
        // 获取线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(10);

        // 提交线程任务到线程池
        for (int i = 1; i <= 100; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "提交了线程任务");
                }
            });
        }

        pool.shutdown();
    }
}
