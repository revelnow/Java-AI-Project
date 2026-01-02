package com.theo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapTest2 {
    /*
    定义一个Map集合，键用表示省份名称的字符串表示，值用表示省会名称的字符串表示，但市会有多个
    添加完毕后，遍历结果：
    格式如下：
    江苏=南京市，扬州市，苏州市，无锡市，常州市
    湖北省=武汉市，孝感市，黄冈市，荆州市
    四川省=成都市，绵阳市，德阳市，广元市
     */
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "南京市", "扬州市", "苏州市", "无锡市", "常州市");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "武汉市", "孝感市", "黄冈市", "荆州市");

        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3, "成都市", "绵阳市", "德阳市", "广元市");

        Map<String,ArrayList<String>> map = new HashMap<>();
        map.put("江苏省",list1);
        map.put("湖北省",list2);
        map.put("四川省",list3);
        map.forEach((k,v)->{
            System.out.println(k + "=" + String.join("，", v));
        });


    }
}
