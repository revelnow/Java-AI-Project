package com.theo;

import java.io.File;
import java.io.IOException;

public class FileDome2 {
    /*
    路径的分类
        1.绝对路径：包含盘符在内的文件或文件夹的完整路径
        2.相对路径：相对于当前项目根目录下的路径
     */

    public static void main(String[] args) throws IOException {
        //绝对路径
        File file1 = new File("E:\\IJ25.02\\Project\\File\\a.txt");
        file1.createNewFile();
        System.out.println(file1.getAbsoluteFile());

        //相对路径
        File file2 = new File("b.txt");
        file2.createNewFile();
        System.out.println(file2.getAbsoluteFile());
    }
}
