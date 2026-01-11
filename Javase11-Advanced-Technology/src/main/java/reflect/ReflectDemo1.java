package reflect;

import pojo.Student;

public class ReflectDemo1 {
    /*
        反射获取类的字节码对象

            1. Class.forName("全类名");
            2. 类名.class
            3. 对象.getClass();
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // 1. Class.forName("全类名");
        Class<?> class1 = Class.forName("com.itheima.pojo.Student");

        // 2. 类名.class
        Class<Student> class2 = Student.class;

        // 3. 对象.getClass();
        Student stu = new Student();
        Class<? extends Student> class3 = stu.getClass();

        System.out.println(class1 == class2);
        System.out.println(class2 == class3);
        System.out.println(class3 == class1);
    }
}
