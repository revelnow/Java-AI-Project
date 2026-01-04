package com.theo.Date;

public class LocalDateDemo {
    /*
    LocalDate类：表示不含时间的日期类
    常用成员方法：
    public static LocalDate now()：获取当前日期
    public static LocalDate of(int year, int month, int dayOfMonth)：根据指定的年、月、日创建LocalDate对象
    public int getYear()：获取年份
     */
    public static void main(String[] args) {
        java.time.LocalDate today = java.time.LocalDate.now();
        System.out.println("Today's date: " + today);

        java.time.LocalDate specificDate = java.time.LocalDate.of(2022, 12, 25);
        System.out.println("Specific date: " + specificDate);

        int year = specificDate.getYear();
        System.out.println("Year of specific date: " + year);
    }
}
