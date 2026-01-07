package thread_method;

public class ThreadMethodDemo1 {
    /*
    休眠线程的方法

    public static void sleep(long time) : 让线程休眠指定的时间，单位为毫秒
 */
    public static void main(String[] args) throws InterruptedException {
        for(int i = 5; i >= 1; i--){
            System.out.println("倒计时:" + i + "秒");
            // 项目经理要求此处代码运行缓慢, 让甲方加钱优化.
            Thread.sleep(1000);
        }
    }

}
