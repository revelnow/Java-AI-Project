package HuTool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import java.io.*;
import java.util.ArrayList;

public class HuToolDemo {
    /*
        IOUtil常用方法
            copy(InputStream in, OutputStream out, int bufferSize)      字节流拷贝
            copy(Reader reader, Writer writer)                          字符流拷贝
            readLines(Reader reader, Collection<String> collection)     按行读取内容到集合
            close(Closeable closeables)                                 安全关闭流

        FileUtil常用方法
            touch(filePath)                                             创建文件（自动创建父目录）
            mkdir(dirPath)                                              创建目录（支持多级目录）
            copy(srcPath, destPath, isOverride)                         复制文件或目录（可选覆盖）
            move(srcFile, destDir, isOverride)                          移动文件或目录
     */
    public static void main(String[] args) throws IOException {
        // 创建文件（自动创建父目录）
        FileUtil.touch("E:\\aaa\\bbb\\ccc\\A.txt");
        // 创建目录（支持多级目录）
        FileUtil.mkdir("E:\\abc\\ddd");
        // 复制文件或目录（可选覆盖）
        FileUtil.copy("E:\\test", "D:\\", true);
        // 移动文件或目录
        FileUtil.move(new File("E:\\test"), new File("D:\\"), true);
    }

    private static void method() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("D:\\1.jpg");
        FileOutputStream fos = new FileOutputStream("E:\\copy.jpg");

        IoUtil.copy(fis, fos, 2048);

        IoUtil.close(fis);
        IoUtil.close(fos);

        // 按行读取内容到集合
        ArrayList<String> list = new ArrayList<>();
        FileReader fr = new FileReader("day04\\出师表.txt");
        IoUtil.readLines(fr, list);
        IoUtil.close(fr);

        list.forEach(System.out::println);
    }
}
