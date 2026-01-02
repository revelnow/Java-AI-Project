package com.theo;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcptionDemo3 {
    /*
    目标：异常的处理机制
     */
    public static void main(String[] args)  {
        System.out.println("程序开始了");
        try {
            System.out.println("成功了");
            test("2020-10-10 10:10:10");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败了，异常信息是："+e.getMessage());
        }

        System.out.println("程序结束了");

    }
    public static void test(String s) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s);//编译时异常，必须处理
        System.out.println();


    }
}
