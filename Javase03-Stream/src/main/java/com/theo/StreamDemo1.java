package com.theo;

import java.util.*;
import java.util.stream.Stream;

public class StreamDemo1 {
    /*获取Stream的方法有三种：
    1.通过集合获取：单列集合：List、Set使用collection.stream()方法获取
               双列集合：Map间接获取，先获取键集、值集、键值对集合，再通过上述单列集合的方法获取
    2.通过数组获取：使用Arrays类中的静态方法stream获取
    3.通过Stream类中的静态方法of获取
     */
    public static void main(String[] args) {
        //1.通过集合获取Stream
        //List集合
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.stream().forEach(System.out::println);
        System.out.println("-------------------");

        //set集合
        Set<String> set = new HashSet<>();
        set.add("赵六");
        set.add("钱七");
        set.add("孙八");
        set.stream().forEach(System.out::println);
        System.out.println("-------------------");

        //map集合
        //间接获取Stream
        Map<String, Integer> map = new HashMap<>();
        map.put("语文", 90);
        map.put("数学", 95);
        map.put("英语", 85);
        //获取键的Stream
        map.keySet().stream().forEach(System.out::println);
        //获取值的Stream
        map.values().stream().forEach(System.out::println);
        System.out.println("-------------------");
        //获取键值对的Stream
        map.entrySet().stream().forEach(System.out::println);
        System.out.println("-------------------");


        //2.通过数组获取Stream
        int[] arr1= {1,2,3,4,5};
        String[] arr2 = {"a","b","c","d","e"};
        Arrays.stream(arr1).forEach(System.out::println);
        System.out.println("-------------------");
        Arrays.stream(arr2).forEach(System.out::println);
        System.out.println("-------------------");

        //3.通过Stream类中的静态方法of获取
        Stream<Integer> nub = Stream.of(1, 2, 3, 4, 5);
        Stream<String> str = Stream.of("A", "B", "C", "D", "E");

    }
}
