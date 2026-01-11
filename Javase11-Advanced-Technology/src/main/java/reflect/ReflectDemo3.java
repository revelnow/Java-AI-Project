package reflect;

import pojo.Student;

import java.lang.reflect.Field;

public class ReflectDemo3 {
    /*
        反射类中的成员变量

        1. Field[] getFields()                      返回所有公共成员变量对象的数组
        2. Field[] getDeclaredFields()              返回所有成员变量对象的数组
        3. Field getField(String name)              返回单个公共成员变量对象
        4. Field getDeclaredField(String name)      返回单个成员变量对象

        ---------------------------------------------------------------------------------------------------

        Field类的设置和获取方法
        1. void set(Object obj, Object value)       赋值
        2. Object get(Object obj)                   获取值

        ---------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) throws Exception {
        Class<Student> studentClass = Student.class;

        // 反射类的空参构造方法并创建对象
        Student stu1 = studentClass.getConstructor().newInstance();
        Student stu2 = studentClass.getConstructor().newInstance();

        // 获取成员变量的对象
        Field nameField = studentClass.getDeclaredField("name");
        nameField.setAccessible(true);
        Field ageField = studentClass.getDeclaredField("age");
        ageField.setAccessible(true);

        // 通过成员变量对象, 完成赋值和获取操作
        nameField.set(stu1, "张三");
        nameField.set(stu2, "李四");

        ageField.set(stu1, 23);
        ageField.set(stu2, 24);

        System.out.println(nameField.get(stu1));
        System.out.println(nameField.get(stu2));

        System.out.println(ageField.get(stu1));
        System.out.println(ageField.get(stu2));
    }
}
