package test;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest3 {
        /*
        使用 Lock 锁, 我们可以更清晰的看到哪里加了锁，哪里释放了锁
        Lock 是接口, 无法直接创建对象, 需要创建 ReentrantLock
     */
        public static void main(String[] args) {
            TicketTask ticket=new TicketTask();

            Thread t1=new Thread(ticket,"窗口1");
            Thread t2=new Thread(ticket,"窗口2");
            Thread t3=new Thread(ticket,"窗口3");

            t1.start();
            t2.start();
            t3.start();
        }
        public static class TicketTask implements Runnable{

            private static int ticket = 100;
            ReentrantLock lock = new ReentrantLock();
            @Override
            public void run() {
                while(true){
                    try {
                        lock.lock();
                        if(ticket==0){
                            break;
                        }
                        System.out.println("窗口"+Thread.currentThread().getName()+"卖出了第"+ticket+"张票");
                        ticket --;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
}
