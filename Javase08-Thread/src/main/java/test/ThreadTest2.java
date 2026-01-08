package test;

public class ThreadTest2 {
        /*
        同步方法: 在方法的返回值类型前面加入 synchronized 关键字

        方法分为静态和非静态
        静态方法的锁对象是字节码对象，非静态方法的锁对象是 this
     */
        public static void main(String[] args) {

            Ticket ticket=new Ticket();

            Thread t1=new Thread(ticket,"窗口1");
            Thread t2=new Thread(ticket,"窗口2");
            Thread t3=new Thread(ticket,"窗口3");

            t1.start();
            t2.start();
            t3.start();

        }
        public static class Ticket implements Runnable{
            private static int ticket = 100;
            @Override
            public void run() {
                while(true){
                    if (method()) break;
                }
            }

            private synchronized boolean method() {
                if(ticket==0){
                    return true;
                }
                System.out.println("窗口"+Thread.currentThread().getName()+"卖出了第"+ticket+"张票");
                ticket --;
                return false;
            }
        }
}
