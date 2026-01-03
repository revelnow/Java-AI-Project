package com.theo;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamDemo2 {
    /*
    Stream<T> filter(Predicate<? super T> predicate)	用于对流中的数据进行过滤
    Stream<T> limit(long maxSize)                       获取前几个元素
    Stream<T> skip(long n)	                            跳过前几个元素
    Stream<T> distinct()	                 去除流中重复的元素依赖(hashCode 和 equals方法)
    static <T> Stream<T> concat(Stream a, Stream b)	    合并a和b两个流为一个流
    Stream<R> map(Function<? super T,? extends R> mapper)	对流中的每一个元素进行转换
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");
        // 需求: 将集合中以 【张】 开头的数据，并且只要长度为3的人名, 过滤出来并打印在控制台
        list.stream().filter(s -> s.startsWith("张") && s.length() == 3);
        // 需求1: 取前3个数据在控制台输出
        list.stream().limit(3).forEach(System.out::println);
        // 需求2: 跳过3个元素, 把剩下的元素在控制台输出
        list.stream().skip(3).forEach(System.out::println);
        // 需求3: 跳过2个元素, 把剩下的元素中前2个在控制台输出
        list.stream().skip(2).limit(2).forEach(System.out::println);
        // 需求4: 取前4个数据组成一个流
        Stream<String> list1 = list.stream().limit(4);
        // 需求5: 跳过2个数据组成一个流
        Stream<String> list2 = list.stream().skip(2);
        // 需求6: 合并需求4和需求5得到的流, 并把结果在控制台输出，要求字符串元素不能重复
        Stream.concat(list1, list2).distinct().forEach(System.out::println);

        //Stream<R> map(Function<? super T,? extends R> mapper)
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5);
        // 需求: 对流中的数据进行转换, 每一个数据都做 + 10操作, 随后遍历打印.
        s1.map(integer -> integer + 10).forEach(System.out::println);
    }

}
