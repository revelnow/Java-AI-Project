package com.theo;

import java.util.HashMap;
import java.util.Map;

public class MapDemo4 {
    /*
    map的第三种遍历方式：ForEach方法
     public void forEach(BiConsumer<? super K,? super V> action):void
     */


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("张三","北京");
        map.put("李四","上海");
        map.put("王五","广州");

        map.forEach((k,v)->{
            System.out.println(k + "----" + v);
        });

    }
}
