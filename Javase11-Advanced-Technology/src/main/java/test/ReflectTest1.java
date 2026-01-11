package test;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectTest1 {
    /*
        需求: 请向一个泛型为 Integer 的集合，添加一个 String 字符串
        思路: Java 中的泛型是假的，只在编译的时候有效
     */
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Class<? extends ArrayList> listClass = list.getClass();

        Method addMethod = listClass.getMethod("add", Object.class);

        addMethod.invoke(list, "张三");

        System.out.println(list);
    }
}
