import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo2 {
    /*
   代码规范
    流资源对象使用完毕后，需要及时关闭，以释放资源
     */
    public static void main(String[] args) throws IOException {
       //1.创建文件输出流对象
        FileOutputStream fos=new FileOutputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\c.txt");
        //2.写出数据
        fos.write("hello".getBytes());
        //3.释放资源
        fos.close();
    }
}
