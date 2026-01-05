import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyTest {
    /*
    请使用字节流复制一个图片文件到指定目录
     */
    public static void main(String[] args) {

        String s1="d:\\1.png";
        String s2="e:\\1.png";

        try (FileInputStream fis = new FileInputStream(s1);FileOutputStream fos = new FileOutputStream(s2)) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            System.out.println("复制完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
