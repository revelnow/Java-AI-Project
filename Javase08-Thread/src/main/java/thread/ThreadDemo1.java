package thread;

public class ThreadDemo1 {
    /*
        Java程序默认是多线程的.

            1. main线程
            2. 垃圾回收线程
     */
    public static void main(String[] args) {
        for(int i = 1; i <= 500000; i++){
            new Demo();
        }
    }
}


class Demo {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾被清理了...");
    }
}