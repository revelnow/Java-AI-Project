package pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {
    /*
    自定义线程池：ThreadPoolExecutor

    参数1核心线程数量（正式员工）
    参数2最大线程数量（正式员工+ 临时工）
    参数3空闲时间参数4时间单位
    参数5任务队列（指定排队人数）
    参数6线程对象工厂参数
    7拒绝策略
     */
    public static void main(String[] args) {
    ThreadPoolExecutor pool=new ThreadPoolExecutor(2,
            5,
            10,
            TimeUnit.SECONDS,
            new java.util.concurrent.ArrayBlockingQueue<>(5),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 9; i++) {
           pool.submit(new Runnable() {
               @Override
               public void run() {
                   System.out.println(Thread.currentThread().getName()+"提交了线程任务");
               }
           });

        }
    }
}
