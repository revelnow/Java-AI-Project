import com.theo.FileTest;

import java.io.File;

public class RecursionDome4 {
    /*
    需求：设计一个方法，删除文件夹
    注意：delete（）方法只能删除空文件夹
     */

    public static void main(String[] args) {
        File dir = FileTest.Getdir();
        deleteDir(dir);
    }

    private static void deleteDir(File dir) {
        //获取该文件夹下的所有文件和文件夹
        File[] files = dir.listFiles();

        //判断是否有权限
       if(files!=null){
           //遍历该文件夹下的所有文件和文件夹
           for (File file : files) {
               //删除文件夹里的文件
               if (file.isFile()) {
                   file.delete();
                   System.out.println("当前删除的文件是"+file.getPath());
                        //delete（）方法只能删除空文件夹，所以需要递归删除文件夹下的所有文件和文件夹
                } else if (file.isDirectory()) {
                  //递归删除文件夹下的所有文件和文件夹
                   deleteDir(file);

               }
           }
       }
         //删除该文件夹
          dir.delete();
          System.out.println("该文件夹已被删除"+dir.getPath());

    }
}








