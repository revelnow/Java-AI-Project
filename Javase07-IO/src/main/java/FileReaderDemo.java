import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
    /*
    FileReader类：文件字符输入流
    作用：将硬盘文件中的数据以字符的方式读取到内存中
    构造方法：
    public FileReader(File file)：创建一个FileReader对象，用于读取指定的File对象
    public FileReader(String filePath)：创建一个FileReader对象，用于读取指定路径的文件
    常用成员方法：
    public int read()：读取单个字符并返回，读取到文件末尾返回-1
    public int read(char[] cbuf)：将字符读入数组，返回读入数组的字符数，读取到文件末尾返回-1
    public void close()：关闭此流并释放与此流相关联的所有系统资源
     */
    public static void main(String[] args) {

        int len;
        char[] chars=new char[1024];


        try(FileReader fr = new FileReader("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\FileReader.java");) {
            while((len=fr.read(chars))!=-1) {
                String s = new String(chars, 0, len);
                System.out.print(s);
            }
        } catch (IOException e) {
           e.printStackTrace();
        }



    }
}
