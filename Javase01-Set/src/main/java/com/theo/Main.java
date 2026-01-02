package com.theo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {

        TreeSet<String> set1=new TreeSet<>();
        set1.add("a");
        set1.add("c");
        set1.add("d");
        set1.add("c");
        System.out.println(set1);
        Iterator<String> iterator = set1.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }
        System.out.println("------------------");

        HashSet<String> set2=new HashSet<>();
        set2.add("a");
        set2.add("c");
        set2.add("d");
        set2.add("c");
        set2.add("q");
        System.out.println(set2);
        for (String s : set2) {
            System.out.println(s);
        }
        System.out.println("------------------");

        LinkedHashSet<String> set3=new LinkedHashSet<>();
        set3.add("a");
        set3.add("c");
        set3.add("d");
        set3.add("c");
        System.out.println(set3);
        set3.forEach(System.out::println);



    }
}