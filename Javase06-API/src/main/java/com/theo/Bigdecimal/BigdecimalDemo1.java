package com.theo.Bigdecimal;

import java.math.BigDecimal;

public class BigdecimalDemo1 {
    /*
    BigDecimal类：用于解决小数运算中出现的不精确问题
    BigDecimal创建对象：
    public BigDecimal(double val)：不推荐，无法保证小数运算的精确
    public BigDecimal(String val)
    public static BigDecimal valueOf(double val)


    BigDecimal常用成员方法：
    public BigDecimal add(BigDecimal b)：加法
    public BigDecimal subtract(BigDecimal b)：减法
    public BigDecimal multiply(BigDecimal b)：乘法
    public BigDecimal divide(BigDecimal b)：除法
    public BigDecimal divide(另一个BigDecimal对象, 精确几位, 舍入模式)：除法
    注意：如果使用BigDecimal运算，出现了除不尽的情况就会出现异常
     */
    public static void main(String[] args) {
        BigDecimal a=new BigDecimal("0.1");
        BigDecimal b=new BigDecimal("0.2");
        System.out.println(a.add(b));

        BigDecimal c=BigDecimal.valueOf(1);
        BigDecimal d=BigDecimal.valueOf(3);
        //System.out.println(c.divide(d));//出现异常
        System.out.println(c.divide(d,2,BigDecimal.ROUND_HALF_UP));

    }
}
