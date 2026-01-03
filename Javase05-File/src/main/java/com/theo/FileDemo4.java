package com.theo;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class FileDemo4 {
    /*
    file的常用方法
        1. public String getAbsolutePath()：获取绝对路径
        2. public String getPath()：获取路径
        3. public String getName()：获取名称
        4. public long length()：获取长度（字节数） 注：不能获取目录的长度
        5. public boolean exists()：判断是否存在
        创建与删除
        6. public boolean createNewFile()：创建文件
        7. public boolean mkdir()：创建单级目录
        8. public boolean mkdirs()：创建多级目录
        9. public boolean delete()：删除文件或目录
     */
    public static void main(String[] args) throws IOException {
        //1. public String getAbsolutePath()：获取绝对路径
        File file1 = new File("a.txt");
        System.out.println("绝对路径："+file1.getAbsolutePath());

        //2. public String getPath()：获取路径
        File file2 = new File("b.txt");
        System.out.println("路径："+file2.getPath());

        //3. public String getName()：获取名称
        File file3 = new File("c.txt");
        System.out.println("名称："+file3.getName());

        //4. public long length()：获取长度（字节数） 注：不能获取目录的长度
        File file4 = new File("a.txt");
        System.out.println(file4.length());

        //5. public boolean exists()：判断是否存在
        File file5 = new File("d.txt");
        System.out.println("是否存在："+file5.exists());

        //6. public boolean createNewFile()：创建文件
        File file6 = new File("e.txt");
        System.out.println(file6.createNewFile());

        //7. public boolean mkdir()：创建单级目录
        File file7 = new File("Javase05-File/src/main/java/com/theo/Dir1");
        System.out.println(file7.mkdir());

        //8. public boolean mkdirs()：创建多级目录
        File file8 = new File("Javase05-File/src/main/java/com/theo/Dir2/Dir21/Dir211");
        System.out.println(file8.mkdirs());

        //9. public boolean delete()：删除文件或目录
        file7.delete();
        file8.delete();

    }
}
