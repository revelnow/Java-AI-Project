package com.theo.Integer;

public class IntegerDemo2 {
    /*
    Integer类的常见方法
    1. 字符串转换为整数：Integer.parseInt(String s) - 将字符串转换为基本数据类型int。
    2. 整数转换为字符串：Integer.toString(int i) - 将基本数据类型int转换为字符串。
    3. 获取整数的最大值和最小值：Integer.MAX_VALUE 和 Integer.MIN_VALUE - 分别表示int类型的最大值和最小值。
    4. 十进制转换为其他进制字符串：Integer.toBinaryString(int i)、Integer.toOctalString(int i)、Integer.toHexString(int i) - 分别将整数转换为二进制、八进制和十六进制的字符串表示。
    5. 其他进制字符串转换为十进制整数：Integer.parseInt(String s, int radix) - 将指定进制的字符串转换为十进制整数。
     */
    public static void main(String[] args) {
        // 1. 字符串转换为整数
        String strNum = "12345";
        int intNum = Integer.parseInt(strNum);
        System.out.println("String to int: " + intNum);

        // 2. 整数转换为字符串
        int num = 6789;
        String strFromInt = Integer.toString(num);
        System.out.println("Int to String: " + strFromInt);

        // 3. 获取整数的最大值和最小值
        System.out.println("Integer MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer MIN_VALUE: " + Integer.MIN_VALUE);

        // 4. 十进制转换为其他进制字符串
        int decimalNum = 255;
        String binaryStr = Integer.toBinaryString(decimalNum);
        String octalStr = Integer.toOctalString(decimalNum);
        String hexStr = Integer.toHexString(decimalNum);
        System.out.println("Binary representation of 255: " + binaryStr);
        System.out.println("Octal representation of 255: " + octalStr);
        System.out.println("Hexadecimal representation of 255: " + hexStr);

        // 5. 其他进制字符串转换为十进制整数
        String binaryInput = "11111111";
        int decimalFromBinary = Integer.parseInt(binaryInput, 2);
        System.out.println("Decimal from binary '11111111': " + decimalFromBinary);
    }
}
