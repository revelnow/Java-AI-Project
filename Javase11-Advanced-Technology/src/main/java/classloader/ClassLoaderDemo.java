package classloader;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        // 1. 获取BootStrapClassLoader (启动类\引导类加载器)
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);

        // 2. 获取PlatformClassLoader (平台类加载器 jdk\lib\modules)

        // 3. 获取ApplicationClassLoader (应用程序类加载器)
        ClassLoader classLoader2 = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader2);

        // 测试类加载器的上下级关系
        System.out.println(classLoader2.getParent());
        System.out.println(classLoader2.getParent().getParent());
    }
}
