package thread;

public class ThreadDemo3 {
       /*
        开启线程第二种方式 - 实现Runnable接口 场景：无返回值, 无参数,相比继承Thread类方式更灵活(单继承多接口)

        1. 编写一个类实现Runnable接口
        2. 重写run方法
        3. 将线程任务代码, 写在run方法中
        4. 创建Runnable接口的实现类对象
        5. 创建线程对象, 并将Runnable接口的实现类对象传入
        6. 使用线程对象调用start方法开启线程
     */
       public static void main(String[] args) {
           //4. 创建Runnable接口的实现类对象
           MyRunnable myRunnable = new MyRunnable();
           //5. 创建线程对象, 并将Runnable接口的实现类对象传入
           Thread thread1 = new Thread(myRunnable);
              Thread thread2 = new Thread(myRunnable);
           //6. 使用线程对象调用start方法开启线程
           thread1.start();
           thread2.start();
       }
       //1. 编写一个类实现Runnable接口
       public static class MyRunnable implements Runnable{
              //2. 重写run方法
           @Override
           public void run() {
               //3. 将线程任务代码, 写在run方法中
               for (int i = 0; i < 500; i++) {
                   System.out.println("MyRunnable:" + i);
               }
           }
       }
}
