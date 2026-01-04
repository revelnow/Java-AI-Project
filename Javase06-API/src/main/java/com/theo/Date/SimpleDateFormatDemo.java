package com.theo.Date;

import java.text.SimpleDateFormat;

public class SimpleDateFormatDemo {
    /*
    SimpleDateFormat类：用于格式化和解析日期的类
    构造方法：
    public SimpleDateFormat()：创建一个默认格式的SimpleDateFormat对象
    public SimpleDateFormat(String pattern)：创建一个指定格式的SimpleDateFormat对象
    常用成员方法：
    public String format(Date date)：将Date对象格式化为字符串
    public Date parse(String source)：将字符串解析为Date对象
     */
    public static void main(String[] args) {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr=sim.format(new java.util.Date());
        System.out.println("Formatted date string: "+dateStr);

        String dateStr1=sim.format(new java.util.Date());
        try {
            java.util.Date date=sim.parse(dateStr1);
            System.out.println("Parsed Date object: "+date);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
