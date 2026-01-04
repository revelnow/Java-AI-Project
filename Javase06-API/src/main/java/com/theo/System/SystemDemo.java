package com.theo.System;

public class SystemDemo {
    /*
    System类的常见方法
    1. 当前时间：System.currentTimeMillis() - 返回自1970年1月1日00:00:00 UTC以来的毫秒数。
    2. 数组复制：System.arraycopy(src, srcPos, dest, destPos, length) - 将数组的一部分复制到另一个数组中。
    3. 退出程序：System.exit(status) - 终止当前运行的Java虚拟机。
    4. 获取环境变量：System.getenv(name) - 返回指定环境变量的值。
    5. 获取系统属性：System.getProperty(key) - 返回指定系统属性的值。
     */

    public static void main(String[] args) {
        // 1. 当前时间
        long currentTime = System.currentTimeMillis();
        System.out.println("Current time in milliseconds since epoch: " + currentTime);

        /* 2. 数组复制
        src：源数组
        srcPos：源数组中的起始位置
        dest：目标数组
        destPos：目标数组中的起始位置
        length：要复制的数组元素的数量
         */
        int[] srcArray = {1, 2, 3, 4, 5};
        int[] destArray = new int[5];
        System.arraycopy(srcArray, 0, destArray, 0, srcArray.length);
        System.out.print("Copied array: ");
        for (int num : destArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 3. 获取环境变量
        String pathEnv = System.getenv("PATH");
        System.out.println("PATH environment variable: " + pathEnv);

        // 4. 获取系统属性
        String javaVersion = System.getProperty("java.version");
        System.out.println("Java version: " + javaVersion);

        // Note: Uncommenting the following line will terminate the program
        // System.exit(0);
    }
}
