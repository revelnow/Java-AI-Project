package anno;

import java.lang.reflect.Method;

public class ScanTest {
    public static void main(String[] args) throws Exception {
        Class<MethodDemo> methodDemoClass = MethodDemo.class;

        MethodDemo instance = methodDemoClass.getConstructor().newInstance();

        // 获取该类中所有成员方法的对象
        Method[] methods = methodDemoClass.getMethods();

        // 遍历数组获取每一个成员方法对象
        for (Method method : methods) {
            // 判断该方法的头顶上, 是否存在@Test注解
            if (method.isAnnotationPresent(Test.class)) {
                // 方法执行
                method.invoke(instance);
            }
        }
    }
}
