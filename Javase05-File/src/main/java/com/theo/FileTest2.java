package com.theo;

import java.io.File;
import java.util.Scanner;

public class FileTest2 {
    /*
    需求：键盘录入一个文件夹路径，找到这个文件夹下所有的.java文件。
     */
    public static void main(String[] args) {

        method();
    }
    //遍历文件夹方法
    public  static void method(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个文件夹路径：");
        String s = sc.nextLine();

        File file = new File(s);
        File[] date = file.listFiles();
        for (File soutJava : date) {
            if(soutJava.getName().endsWith(".java")){
                System.out.println(soutJava.getAbsoluteFile());
            }
        }
    }
}
