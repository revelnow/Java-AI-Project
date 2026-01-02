package com.theo;

import java.io.File;

public class FileDemo3 {
    /*
    File常用的api
        1.boolean exists()：此File表示的文件或目录是否实际存在。
        2.boolean isDirectory()：此File表示的是否为目录。
        3.boolean isFile()：此File表示的是否为文件。
     */
    public static void main(String[] args) {

        File file1 = new File("b.txt");
        System.out.println("文件是否存在："+file1.exists());
        System.out.println("file1是否为目录："+file1.isDirectory());
        System.out.println("file1是否为文件："+file1.isFile());
        System.out.println("========================");
    }
}
