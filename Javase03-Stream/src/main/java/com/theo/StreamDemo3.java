package com.theo;

import java.util.stream.Stream;

public class StreamDemo3 {
    /*
    终端操作(也叫做结束操作)：用于结束流的操作，执行终端操作后，流就被使用完毕，不能再被操作了
    void forEach(Consumer<? super T> action)	对流中的每一个数据执行指定的操作
     */
    public static void main(String[] args) {
        // 创建一个字符串流
        Stream<String> str = Stream.of("张三", "李四", "王五", "赵六", "钱七");
        // 使用终端操作forEach遍历流中的每一个数据并打印在控制台
        str.forEach(System.out::println);
    }
}
