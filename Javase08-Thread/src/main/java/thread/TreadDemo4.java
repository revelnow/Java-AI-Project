package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TreadDemo4 {
    /*
        开启线程的第三种方式 - 实现 Callable 接口   场景：需要获取线程执行结果

        1. 编写一个类实现Callable接口
        2. 重写call方法 (此方法存在返回值)
        3. 将线程任务代码写在call方法中
        4. 创建线程资源对象
        5. 创建线程任务对象, 封装线程资源
        6. 创建线程对象, 传入线程任务
        7. 使用线程对象调用start开启线程
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       //4.创建资源对象
        MyCallable myCallable=new MyCallable();

        //5.创建线程任务对象,封装线程资源
        FutureTask<Integer> myFutureTask1=new FutureTask<>(myCallable);
        FutureTask<Integer> myFutureTask2=new FutureTask<>(myCallable);

        //6.创建线程对象,传入线程任务
        Thread t1=new Thread(myFutureTask1);
        Thread t2=new Thread(myFutureTask2);

        //7.使用线程对象调用start开启线程
        t1.start();
        t2.start();

        //获取线程执行结果
        int sum= myFutureTask1.get();
        System.out.println(sum);
        int sum2= myFutureTask2.get();
        System.out.println(sum2);
    }
    // 1. 编写一个类实现Callable接口
    public static class MyCallable implements Callable<Integer> {
        //2. 重写call方法 (此方法存在返回值)
        @Override
        public Integer call() throws Exception {
            // 3. 将线程任务代码写在call方法中
            int sum=0;
            for (int i = 0; i < 500; i++) {
                sum+=i;
            }
            return sum;
        }
    }
}
