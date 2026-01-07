package thread_method;

public class ThreadMethodDemo2 {
        /*
        线程优先级的方法:

            public setPriority(int newPriority) : 设置线程优先级
            public final int getPriority() : 获取线程优先级
     */
        public static void main(String[] args) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 500; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    }
                }
            }, "线程A");

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 500; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + i);
                    }
                }
            }, "线程B");

            // 设置线程优先级
            t1.setPriority(10);
            t2.setPriority(2);

            t1.start();
            t2.start();
        }
}
