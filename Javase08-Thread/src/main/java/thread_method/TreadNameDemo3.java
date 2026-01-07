package thread_method;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TreadNameDemo3 {
        /*
        获取当前线程的名字

        Thread类的方法:

            public String getName() : 获取线程名字
            public void setName() : 设置线程名字
            public static Thread currentThread() : 获取当前线程的对象

     */
        public static void main(String[] args) {
            MyCallable myCallable = new MyCallable();

            FutureTask<Integer> myFutureTask1 = new FutureTask<>(myCallable);
            FutureTask<Integer> myFutureTask2 = new FutureTask<>(myCallable);

            Thread t1 = new Thread(myFutureTask1, "线程A");
            Thread t2 = new Thread(myFutureTask2, "线程B");

            t1.start();
            t2.start();

        }
    // 1. 编写一个类实现Callable接口
    public static class MyCallable implements Callable<Integer> {
        //2. 重写call方法 (此方法存在返回值)
        @Override
        public Integer call() throws Exception {
            // 3. 将线程任务代码写在call方法中
            int sum = 0;
            for (int i = 0; i < 500; i++) {
                sum += i;
            }
            System.out.println(Thread.currentThread().getName() + "计算的结果是：" + sum);
            return sum;
        }
    }
}
