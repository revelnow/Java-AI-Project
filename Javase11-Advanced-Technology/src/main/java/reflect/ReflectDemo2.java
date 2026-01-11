package reflect;

import pojo.Student;

import java.lang.reflect.Constructor;

public class ReflectDemo2 {
    /*
        反射类中的构造方法

        1. Constructor<?>[] getConstructors()               返回所有公共构造方法对象的数组
        2. Constructor<?>[] getDeclaredConstructors()       返回所有构造方法对象的数组
        3. Constructor<T> getConstructor(Class<?>... parameterTypes)            返回单个公共构造方法对象
        4. Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)    返回单个构造方法对象

        ---------------------------------------------------------------------------------------------------
        创建对象的方法
        1. T newInstance(Object...initargs)     根据指定的构造方法创建对象
        2. setAccessible(boolean flag)          设置为true,表示取消访问检查
        ---------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws Exception {
        // 1. 获取类的字节码对象
        Class<Student> studentClass = Student.class;
        // 2. 反射构造方法对象
        Constructor<Student> constructor = studentClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        // 3. 通过构造方法对象, 完成实例化
        Student stu = constructor.newInstance("张三", 23);

        System.out.println(stu);
    }

    private static void method(Class<Student> studentClass) {
        Constructor<?>[] constructors = studentClass.getDeclaredConstructors();
        for (Constructor<?> c : constructors) {
            System.out.println(c);
        }
    }

}
