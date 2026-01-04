package com.theo.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterDemo {
    /*
    DateTimeFormatter类：用于格式化和解析日期时间的类
    常用成员方法：
    public static DateTimeFormatter ofPattern(String pattern)：根据指定的模式创建DateTimeFormatter
    public String format(TemporalAccessor temporal)：将TemporalAccessor对象格式化为字符串
     */
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Current date: " + date);
        String formattedDate = formatter.format(date);
        System.out.println("Formatted date: " + formattedDate);

    }
}
