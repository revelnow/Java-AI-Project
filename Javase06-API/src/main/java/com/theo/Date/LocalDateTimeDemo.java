package com.theo.Date;

public class LocalDateTimeDemo {
    /*
    LocalDateTime类：表示日期和时间的类
    常用成员方法：
    public static LocalDateTime now()：获取当前日期和时间
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second)：根据指定的年、月、日、时、分、秒创建LocalDateTime对象
    public int getMinute()：获取分钟
     */
    public static void main(String[] args) {
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        System.out.println("Current date and time: " + currentDateTime);

        java.time.LocalDateTime specificDateTime = java.time.LocalDateTime.of(2022, 12, 25, 14, 30, 0);
        System.out.println("Specific date and time: " + specificDateTime);

        int minute = specificDateTime.getMinute();
        System.out.println("Minute of specific date and time: " + minute);
    }
}
