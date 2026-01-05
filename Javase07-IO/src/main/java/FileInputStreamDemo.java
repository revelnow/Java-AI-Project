import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {
    /*
    FileInputStream 字节输入流读取数据

        1. 构造方法:

           FileInputStream(String name) 输入流关联文件,文件路径以字符串形式给出
           FileInputStream(File file)   输入流关联文件, 文件路径以File对象形式给出

        2. 成员方法:public int read() 读取单个字节
           public int read(byte[] bys) 读取一个字节数组- 将读取到的字节, 存入数组容器, 返回读取到的有效字节个数

     */
    public static void main(String[] args) throws IOException {
        FileInputStream fis =new FileInputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\a.txt");
        int i;
        while ((i=fis.read())!=-1){
            System.out.print((char)i);
        }

        System.out.println();
        FileInputStream fis2 =new FileInputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\a.txt");
        byte[] arr=new byte[2];
        while ((i=fis2.read(arr))!=-1){
            String s=new String(arr, 0, i);
            System.out.print(s);
        }
        fis.close();
        fis2.close();


    }
}
