import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo1 {
    /*
    构造方法：
            public FileOutputStream(File file)：创建文件输出流以写入由指定的File对象表示的文件
            public FileOutputStream(String name)：创建文件输出流以写入由指定的文件名表示的文件
    成员方法：
            public void write(int i)：写出一个字节
            public void write(byte[] b)：写出一个字节数组
            public void write(byte[] b, int off, int len)：写出字节数组的一部分
    细节：
             - 输出流关联文件，文件如果不存在：会自动创建出来
             如果文件存在：会清空现有的内容，然后再进行写入操作

     */
    public static void main(String[] args) throws IOException {
        //1.创建文件输出流对象
        FileOutputStream fos =new FileOutputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\a.txt");

        //2.写出数据
        fos.write(97);
        fos.write(98);

        //1.创建文件输出流对象
        FileOutputStream fos2 =new FileOutputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\b.txt", true);
        //2.写出数据
        byte[] bytes={65,66,67,68,69};
        fos2.write(bytes);
        fos2.write("你好".getBytes());

        //3.释放资源
        fos.close();
        fos2.close();


    }
}
