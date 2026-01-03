package com.theo;

import java.io.File;
import java.util.Scanner;

public class FileTest {
    /*
       需求：键盘录入一个文件夹路径，如果输入错误，则提示重新输入，直到输入正确为止。
     */

    public static void main(String[] args) {
        System.out.println(Getdir());
        System.out.println("结束");

    }

    public static File Getdir() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入一个文件夹路径：");
            String s = sc.nextLine();


            File f = new File(s);
            if(!f.exists()){
                System.out.println("输入错误");
            } else if (f.isFile()) {
                System.out.println("输入错误");
            } else if (f.isDirectory()) {
                System.out.println("输入正确");
                return f;
            }
        }
    }
}
