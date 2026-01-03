import com.theo.FileTest;

import java.io.File;
import java.util.Scanner;

public class RecursionDemo3 {
    /*
     需求：键盘录入一个文件夹路径，找到这个文件夹下所有的.java文件。
     */
    public static void main(String[] args) {
        File dir = FileTest.Getdir();
        getAll(dir);
    }

    private static void getAll(File file ) {


        File[] files = file.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isFile()&&f.getName().endsWith(".java")) {
                    System.out.println(f.getName());
                } else if (f.isDirectory()) {
                    {
                        if (f.listFiles() != null)
                            getAll(f);
                    }
                }
            }
        }
    }
}
