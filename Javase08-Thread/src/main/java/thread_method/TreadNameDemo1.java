package thread_method;

import thread.ThreadDemo2;

public class TreadNameDemo1 {
        /*
        线程设置名字和获取名字

        Thread类的方法:

            public String getName() : 获取线程名字
            public void setName() : 设置线程名字
            public static Thread currentThread() : 获取当前线程的对象

     */
        public static void main(String[] args) {

            MyThread t1 = new MyThread();
            MyThread t2 = new MyThread();

            t1.setName("线程A");
            t2.setName("线程B");



            t1.start();
            t2.start();


        }

    public static class MyThread extends Thread {

        @Override
        public void run() {

            for (int i = 0; i < 500; i++) {
                System.out.println(super.getName()+"进程开始调用" + "--->" + i);
            }
        }
    }
}
