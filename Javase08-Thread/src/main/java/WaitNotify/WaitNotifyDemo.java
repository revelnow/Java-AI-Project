package WaitNotify;

public class WaitNotifyDemo {
    public static void main(String[] args) {
        // 必须有一个公共的“锁对象”，就像那张唯一的门卡
        Object lock = new Object();

        // 开启线程 A
        new Thread(() -> {
            while (true) {
                synchronized (lock) { // 1. 抢锁
                    try {
                        System.out.println("1");

                        // 2. 唤醒别人：嘿，该你了！
                        // (注意：notify 不会立即释放锁，要等代码块执行完)
                        lock.notify();

                        // 3. 自己等待：我把锁交出去，我睡会儿
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程A").start();

        // 开启线程 B
        new Thread(() -> {
            while (true) {
                synchronized (lock) { // 1. 抢锁
                    try {
                        System.out.println("2");

                        // 2. 唤醒别人
                        lock.notify();

                        // 3. 自己等待
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程B").start();
    }
}