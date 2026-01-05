import java.io.FileReader;

public class FileWriterDemo {

    /*
      FileWriter字符输出流写出数据：
       构造方法：
       FileWriter(String fileName):
       字符输出流关联文件，路径以字符串形式给出
       FileWriter(String fileName, boolean append):
       参数2：追加写入的开关
       FileWriter(File file):
       字符输出流关联文件，路径以File对象形式给出
       FileWriter(File file, boolean append):
       参数2：追加写入的开关

       成员方法：public void write(int c)
       写出单个字符
       public void write(char[] cbuff)
       写出一个字符数组public void write(char[] cbuff, int off, int len)
       写出字符数组的一部分
       public void write(String str)
       写出字符串
       public void write(String str, int off, int len)
       写出字符串的一部分

         注意事项：
         flush():刷新缓冲区，流对象在写出数据时，会先将数据写入到内存缓冲区中，当缓冲区满了之后，再一次性写入到文件中，提高写入效率
         close():关闭流对象，在关闭之前，会先调用flush()方法刷新缓冲区

     */
    public static void main(String[] args) {
        //1.创建FileWriter对象
        try (java.io.FileWriter fw = new java.io.FileWriter("E:\\IJ25.02\\Java-AI-Project\\Javase07-IO\\src\\main\\java\\filewriter.txt", true)) {

            //2.写出数据
            fw.write(97);
            fw.write('\n');
            char[] chars = {'a', 'b', 'c', 'd', 'e'};
            fw.write(chars);
            fw.write('\n');
            fw.write(chars, 1, 3);
            fw.write('\n');
            String s = "hello world";
            fw.write(s);
            fw.write('\n');
            fw.write(s, 6, 5);

            //3.释放资源
            //fw.close(); //自动关闭，省略
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
