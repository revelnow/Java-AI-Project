package AtomicTicket;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTicketDemo {
    public static void main(String[] args) {
        TicketTask task = new TicketTask();

        // 开启 3 个窗口卖票
        new Thread(task, "窗口1").start();
        new Thread(task, "窗口2").start();
        new Thread(task, "窗口3").start();
    }
}

class TicketTask implements Runnable {
    // 1. 使用 AtomicInteger 代替 int
    // 初始票数为 100
    private AtomicInteger ticket = new AtomicInteger(100);

    @Override
    public void run() {
        while (true) {
            // 2. 核心：decrementAndGet() 
            // 它的底层就是 CAS 循环。它会尝试减 1，如果发现被别人抢先减了，它会自动重试，直到成功。
            // 这比加锁（synchronized）快得多！
            int currentTicket = ticket.decrementAndGet();

            if (currentTicket >= 0) {
                try {
                    Thread.sleep(10); // 模拟出票耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +
                        " 正在卖票，剩余票数：" + currentTicket);
            } else {
                break; // 票卖完了
            }
        }
    }
}