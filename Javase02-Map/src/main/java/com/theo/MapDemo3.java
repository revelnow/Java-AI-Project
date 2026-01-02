package com.theo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo3 {
    /*
    map的第二种遍历方式：键值对对象找值
     */
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("张三","北京");
        map.put("李四","上海");
        map.put("王五","广州");

        //1.获取所有键值对对象的集合
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        //2.遍历键值对对象的集合
        for (Map.Entry<String, String> entry : entrySet) {
            //3.通过键值对对象获取key和value
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "----" + value);
        }
    }
}
