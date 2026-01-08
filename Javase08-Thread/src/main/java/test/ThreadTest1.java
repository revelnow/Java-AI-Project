package test;

public class ThreadTest1 {
        /*
        需求：某电影院目前正在上映国产大片，共有100张票，而它有3个窗口卖票，请设计一个程序模拟该电影院卖票
                - 多条线程共享操作同一份资源
     */
        public static void main(String[] args) {

            //5. 创建Runnable接口的实现类对象
            TicketWindow ticket=new TicketWindow();

            //6. 创建一个线程对象, 并将Runnable接口的实现类对象传入
            Thread t1=new Thread(ticket,"窗口1");
            Thread t2=new Thread(ticket,"窗口2");
            Thread t3=new Thread(ticket,"窗口3");

            //7. 使用线程对象调用start方法开启线程
            t1.start();
            t2.start();
            t3.start();
        }
        //1. 编写一个类实现Runnable接口
        public static class TicketWindow implements Runnable{
            //2.设置共享的资源
           private int ticket=1000;

            //3. 重写run方法
            @Override
            public void run() {
                while(true){
                    //4.同步代码块
                    synchronized (TicketWindow.class) {
                        if(ticket>0){
                            System.out.println("窗口"+Thread.currentThread().getName()+"卖出了第"+ticket+"张票");
                            ticket--;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
}
