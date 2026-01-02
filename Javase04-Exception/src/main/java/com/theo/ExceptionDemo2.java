package com.theo;

public class ExceptionDemo2 {
    /*
    目的：搞清楚异常的作用
        1. 异常可以帮助我们识别程序中的错误情况，从而提高程序的健壮性。
        2. 异常可以作为方法内部的一种特殊的返回值，向调用者传递错误信息。
     */
    public static void main(String[] args) {

        try {
            System.out.println("程序开始了");
            ExceptionDemo2.divide(10,2);
        } catch (Exception e) {
            System.out.println("程序出现异常了，异常信息是："+e.getMessage());
            throw new RuntimeException(e);
        }


    }
    public static int divide(int a, int b) {
        if (b == 0){
            throw new RuntimeException("除数不能为零");
        }
        return a / b;
    }
}
