package com.theo.Date;

public class LocalTimeDemo {
    /*
    LocalTime类：表示不含日期的时间类
    常用成员方法：
    public static LocalTime now()：获取当前时间
    public static LocalTime of(int hour, int minute, int second)：根据指定的时、分、秒创建LocalTime对象
    public int getHour()：获取小时
     */
    public static void main(String[] args) {
        java.time.LocalTime currentTime = java.time.LocalTime.now();
        System.out.println("Current time: " + currentTime);

        java.time.LocalTime specificTime = java.time.LocalTime.of(14, 30, 0);
        System.out.println("Specific time: " + specificTime);

        int hour = specificTime.getHour();
        System.out.println("Hour of specific time: " + hour);
    }
}
