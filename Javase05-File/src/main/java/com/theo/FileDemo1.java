package com.theo;

import java.io.File;

public class FileDemo1 {
    /*
    创建file的几种方式
        1. new File(String pathname)
        2. new File(String parent, String child)
        3. new File(File parent, String child)
     */

    public static void main(String[] args) {
        //1. new File(String pathname)
        File file1 = new File("E:\\IJ25.02\\Project\\File\\a.txt");
        System.out.println(file1.getAbsoluteFile());

        //2. new File(String parent, String child)
        File file2 =new File("E:\\IJ25.02\\Project\\File","b.txt");
        System.out.println(file2.getAbsoluteFile());

        //3. new File(File parent, String child)
        File file3= new File(new File("E:\\IJ25.02\\Project\\File"),"c.txt");
        System.out.println(file3.getAbsoluteFile());

    }
}
