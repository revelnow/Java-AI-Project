package com.theo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo2 {
    /*
    map的第一种遍历方式：键找值
    public V get(Object key):通过key获取对应的value值
    public Set<K> keySet():获取map中所有key的集合
     */
    public static void main(String[] args) {
        Map<String ,String> map = new HashMap<>();
        map.put("张三","北京");
        map.put("李四","上海");
        map.put("王五","广州");

        //1.获取所有key的集合
        Set<String> keySet = map.keySet();

        //2.遍历key的集合
        for (String key : keySet) {
            //3.通过key获取value
            String value = map.get(key);
            System.out.println(key + "----" + value);
        }
    }
}
