import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class PropertiesDemo2 {
    /*

     Properties 和 IO 有关的方法
     void load(InputStream inStream)    从输入字节流读取属性列表（键和元素对）
     void load(Reader reader)          从输入字符流读取属性列表（键和元素对）
     void store(OutputStream out, String comments)   将集合的键值对写出到文件（字节流）
     void store(Writer writer, String comments)      将集合的键值对写出到文件（字符流）

     */
    public static void main(String[] args) throws  Exception {
        //从输入字符流读取属性列表（键和元素对）
        Properties prop = new Properties();
        FileReader fr =new FileReader("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\test.properties");
        prop.load(fr);
        fr.close();
        System.out.println(prop);

        //将集合的键值对写出到文件（字符流）
        Properties prop2 = new Properties();
        prop2.setProperty("address","beijing");
        FileWriter fw = new FileWriter("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\test2.properties");
        prop2.store(fw,"my info");
        fw.close();
        System.out.println(prop2);

    }
}
