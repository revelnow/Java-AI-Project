package com.theo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamDemo4 {
    /*
    单列集合收集流中的数据到集合中
    <R, A> R collect(Collector<? super T, A, R> collector)	将流中的数据收集到集合中
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,10,10);

        //list集合收集流中的数据到集合中
        //需求：收集偶数元素
        List<Integer> list1 = list.stream().filter(integer -> integer % 2 == 0).toList();
        System.out.println(list1);
        System.out.println("-------------------");

        //set集合收集流中的数据到集合中
        //需求：收集偶数元素，要求元素不能重复
        Set<Integer> set = list.stream().filter(integer -> integer % 2 == 0).collect(Collectors.toSet());
        System.out.println(set);


    }
}
