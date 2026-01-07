package thread_method;

public class TreadNameDemo2 {
    /*
        获取当前线程的名字

        Thread类的方法:

            public String getName() : 获取线程名字
            public void setName() : 设置线程名字
            public static Thread currentThread() : 获取当前线程的对象

     */
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.setName("线程A");
        t2.setName("线程B");

        t1.start();
        t2.start();

    }

    public static class MyRunnable implements Runnable{


        @Override
        public void run() {

            for (int i = 0; i < 500; i++) {
                System.out.println(Thread.currentThread().getName()+" " + i);
            }
        }
    }
}
