package test;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest2 {
    /*
        编写三个任意的Javabean类，Student类，Teacher类，Worker类。
        属性均为：name，age
        方法要求：
            Student里面写学习study和吃饭eat的方法（方法无参无返回）
            Teacher里面写上课teach和吃饭eat的方法（方法无参无返回）
            Worker里面写工作work和睡觉sleep的方法（方法无参无返回）
        本地新建配置文件properties
            属性： className=Student类的全类名    methodName=study

        在测试类中读取properties文件中的两个属性值
        利用反射创建对象学生类的对象，并调用方法
        修改配置文件，不修改代码，运行老师类中的上课方法
        修改配置文件，不修改代码，运行工人类中的工作方法
     */
    public static void main(String[] args) throws Exception {
        // 1. 加载配置文件
        FileInputStream fis = new FileInputStream("day06\\config.properties");
        // 2. 创建Properties集合, 加载数据
        Properties prop = new Properties();
        prop.load(fis);
        fis.close();
        // 3. 取出数据
        String className = prop.getProperty("className");
        String methodName = prop.getProperty("methodName");
        // 4. 获取类的字节码对象
        Class<?> aClass = Class.forName(className);
        // 5. 反射内部的构造方法对象, 并实例化
        Object obj = aClass.getConstructor().newInstance();
        // 6. 反射内部的成员方法对象
        Method method = aClass.getMethod(methodName);
        // 7. 让方法执行
        method.invoke(obj);
    }
}
