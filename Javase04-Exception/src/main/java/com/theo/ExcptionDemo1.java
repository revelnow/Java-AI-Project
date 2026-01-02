package com.theo;

import java.io.FileNotFoundException;

public class ExcptionDemo1 {
    /*
    什么是异常？
    异常是程序在运行过程中出现的错误情况，可能会导致程序崩溃或产生不正确的结果。
    Java 提供了强大的异常处理机制，帮助开发者识别和处理这些错误情况，从而提高程序的健壮性和用户体验。
    异常的分类：
    1. 检查型异常（Checked Exceptions）：这些异常在编译时必须被处理，否则程序无法通过编译。例如，文件未找到异常（FileNotFoundException）。
    2. 非检查型异常（Unchecked Exceptions）：这些异常在运行时发生，不需要在编译时强制处理。例如，空指针异常（NullPointerException）。
    3. 错误（Errors）：这些是严重的问题，通常由系统引起，程序无法处理。例如，内存溢出错误（OutOfMemoryError）。
     */
    public static void main(String[] args) {
        //运行时异常示例
        int[] numbers = {1, 2, 3};
        try {
            System.out.println(numbers[5]); // 访问数组越界
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到数组越界异常: " + e.getMessage());
        }

        //检查型异常示例
        try {
            try {
                java.io.FileReader file = new java.io.FileReader("non_existent_file.txt");
            } catch (FileNotFoundException e) {
                System.out.println("捕获到文件未找到异常: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
