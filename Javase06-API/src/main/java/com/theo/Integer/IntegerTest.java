package com.theo.Integer;

public class IntegerTest {
    /*
    String s = "10,20,30,40,50"
    请将该字符串转换为int并存入数组中
    随后求出最大值打印在控制台
     */
    public static void main(String[] args) {
        String s = "10,20,30,40,50" ;
        String[] strArray = s.split(",") ;
        int[] intArray = new int[strArray.length] ;
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
            System.out.print(intArray[i]+" ");
        }
    }
}
