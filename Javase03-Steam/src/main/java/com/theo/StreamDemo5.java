package com.theo;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamDemo5 {
    /*
    双列集合收集流中的数据到集合中
    <K,U> Map<K,U> collect(Collector<? super T,?,Map<K,U>> collector)	将流中的数据收集到Map集合中
     */
    public static void main(String[] args) {
        List<String> list =new ArrayList<>();
        Collections.addAll(list, "张三-23", "李四-24", "王五-25", "赵六-26", "钱七-27");

        //双列集合收集流中的数据到集合中
        //需求：将集合中的数据收集到Map集合中，姓名作为键，年龄作为值
        list.stream().collect(Collectors.toMap(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.split("-")[0];
            }
        }, new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.split("-")[1];
            }
        }));
        System.out.println(list);

    }
}
