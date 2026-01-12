package anno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ScanTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.获取字节码文件对象
        Class<MethodDemo> methodDemoClass = MethodDemo.class;

        //5.反射执行被Test注解修饰的方法对象
        MethodDemo methodDemo = methodDemoClass.getConstructor().newInstance();


        //2.获取所有方法
        Method[] methods = methodDemoClass.getDeclaredMethods();

        //3.遍历方法
        for (Method method : methods) {
            //4.判断方法上是否有注解
            if(method.isAnnotationPresent(Test.class)){
                //有注解，执行该方法
                method.invoke(methodDemo);
            }
        }
    }
}
