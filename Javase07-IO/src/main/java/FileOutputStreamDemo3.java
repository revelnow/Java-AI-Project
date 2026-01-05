import java.io.FileOutputStream;

public class FileOutputStreamDemo3 {
    /*
    jdk7新特性：自动关闭流
    try(创建流对象;创建流对象;...){
        //使用流对象进行读写操作
    }catch(异常类 变量名){
        //异常处理代码
    }
     说明：实现了AutoCloseable接口的流对象都可以使用该特性
     该特性可以省去finally块中关闭流对象的代码
     该特性会在try代码块执行完毕后自动关闭流对象（无论try代码块是否出现异常）

     */
    public static void main(String[] args) {
        try(FileOutputStream fos1=new FileOutputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\a.txt");
            FileOutputStream fos2=new FileOutputStream("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\b.txt")) {
            fos1.write(97);
            fos2.write(98);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
