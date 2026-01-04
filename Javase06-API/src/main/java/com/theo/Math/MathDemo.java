package com.theo.Math;

public class MathDemo {
    /*
    math类的常见方法
    1. 绝对值：Math.abs(x) - 返回x的绝对值。
    2. 最大值和最小值：Math.max(a, b) 和 Math.min(a, b) - 返回两个数中的最大值和最小值。
    3. 幂运算：Math.pow(a, b) - 返回a的b次幂。
    4. 平方根：Math.sqrt(x) - 返回x的平方根。
    5. 四舍五入：Math.round(x) - 返回最接近x的整数。
    6. 向上取整和向下取整：Math.ceil(x) 和 Math.floor(x) - 分别返回大于等于x的最小整数和小于等于x的最大整数。
    7. 随机数生成： Math.random() - 返回一个0.0到1.0之间的随机数。
     */

    public static void main(String[] args) {
        // 1. 绝对值
        int absValue = Math.abs(-10);
        System.out.println("Absolute value of -10: " + absValue);

        // 2. 最大值和最小值
        int maxValue = Math.max(5, 10);
        int minValue = Math.min(5, 10);
        System.out.println("Max value between 5 and 10: " + maxValue);
        System.out.println("Min value between 5 and 10: " + minValue);

        // 3. 幂运算
        double powerValue = Math.pow(2, 3);
        System.out.println("2 raised to the power of 3: " + powerValue);

        // 4. 平方根
        double sqrtValue = Math.sqrt(16);
        System.out.println("Square root of 16: " + sqrtValue);

        // 5. 四舍五入
        double roundValue = Math.round(5.67);
        System.out.println("Rounded value of 5.67: " + roundValue);

        // 6. 向上取整和向下取整
        double ceilValue = Math.ceil(5.2);
        double floorValue = Math.floor(5.8);
        System.out.println("Ceiling value of 5.2: " + ceilValue);
        System.out.println("Floor value of 5.8: " + floorValue);

        // 7. 随机数生成
        double randomValue = Math.random();
        System.out.println("Random value between 0.0 and 1.0: " + randomValue);
    }

}
