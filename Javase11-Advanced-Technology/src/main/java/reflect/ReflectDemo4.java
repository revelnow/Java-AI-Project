package reflect;

import pojo.Student;

import java.lang.reflect.Method;

public class ReflectDemo4 {
    /*
        反射类中的成员方法

        1. Method[] getMethods()                返回所有公共成员方法对象的数组，包括继承的
        2. Method[] getDeclaredMethods()        返回所有成员方法对象的数组, 不包括继承的
        3. Method getMethod(String name, Class<?>... parameterTypes)                返回单个公共成员方法对象
        4. Method getDeclaredMethod(String name, Class<?>... parameterTypes)        返回单个成员方法对象

        ---------------------------------------------------------------------------------------------------

        Method类的执行方法
        Object invoke(Object obj, Object... args)       运行方法

        ---------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws Exception {
        Class<Student> studentClass = Student.class;

        Student stu = studentClass.getConstructor().newInstance();

        // 反射指定的成员方法对象
        Method eat1 = studentClass.getMethod("eat");
        Method eat2 = studentClass.getMethod("eat", int.class);

        // 让成员方法执行
        eat1.invoke(stu);
        eat2.invoke(stu, 3);
    }
}
