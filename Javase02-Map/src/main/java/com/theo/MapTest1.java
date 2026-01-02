package com.theo;

import java.util.HashMap;
import java.util.Map;

public class MapTest1 {
    /*需求：字符串 aababcabcdabcde
    请统计出每个字符出现的次数，并按照以下格式输出
    a(5)b(4)c(3)d(2)e(1)
     */
    public static void main(String[] args) {
        String str="aababcabcdabcde";
        //遍历字符串，获取每一个字符
        char[] chars = str.toCharArray();

        //创建Map集合，key：字符  value：次数
        Map<Character,Integer> map=new HashMap<>();

        for (char key : chars) {
            if(!map.containsKey(key)){
                map.put(key,1);
            }else {
                map.put(key,map.get(key)+1);
            }
        }

        //创建StringBuilder对象，用于拼接结果字符串
        StringBuilder sb=new StringBuilder();

        //遍历Map集合，拼接结果字符串
        map.forEach((k,v)->{
            sb.append(k).append("(").append(v).append(")");
        });
        System.out.println(sb);
    }
}
