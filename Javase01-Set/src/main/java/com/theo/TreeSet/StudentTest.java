package com.theo.TreeSet;

import java.util.TreeSet;

public class StudentTest {
    public static void main(String[] args) {
        /*自然排序*/
        TreeSet<Student> set1 = new TreeSet<>();
        set1.add(new Student(20, "张三"));
        set1.add(new Student(22, "李四"));
        set1.add(new Student(19, "王五"));
        set1.add(new Student(20, "张三"));
        System.out.println(set1);
        System.out.println("-------------------");


        /*比较器排序*/
        TreeSet<Integer> set2 = new TreeSet<>((o1, o2) -> o2-o1);
        set2.add(30);
        set2.add(10);
        set2.add(20);
        System.out.println(set2);

        TreeSet<String> set3 = new TreeSet<>((o1, o2) -> o1.length() - o2.length());
        set3.add("apple");
        set3.add("banana");
        set3.add("oranges");
        System.out.println(set3);


    }
}
