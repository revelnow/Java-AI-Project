package utils;

/**
 * 文件处理工具类
 */
public class FileUtils {
    /**
     * 安全审计：验证文件名是否合法
     */
    public static void validateFileName(String fileName) {
        if (fileName == null || fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
            throw new IllegalArgumentException("检测到非法文件名或路径穿越风险: " + fileName);
        }
    }
}