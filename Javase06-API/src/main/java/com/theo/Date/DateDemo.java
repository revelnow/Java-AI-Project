package com.theo.Date;

import java.util.Date;

public class DateDemo {
    /*
    Date类：表示日期和时间的类
    构造方法：
    public Date()：创建一个表示当前时间的Date对象
    public Date(long date)：创建一个指定时间的Date对象，参数为从1970年1月1日00:00:00 GMT开始计算的毫秒数
    常用成员方法：
    public long getTime()：返回自1970年1月1日00:00:00 GMT以来的毫秒数
    public void setTime(long time)：设置Date对象的时间，参数为从1970年1月1日00:00:00 GMT开始计算的毫秒数
     */
    public static void main(String[] args) {
        Date d1=new Date();
        System.out.println(d1);

        long time=d1.getTime();
        System.out.println("Milliseconds since epoch: "+time);

        Date d2=new Date(0L);
        System.out.println("Date representing epoch time: "+d2);

    }
}
