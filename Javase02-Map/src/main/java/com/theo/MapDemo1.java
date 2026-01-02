package com.theo;

import java.util.HashMap;
import java.util.Map;

public class MapDemo1 {
    /*
     * Map 的常用 API
     *
     * 1) 添加/更新元素
     * put(K key, V value): V
     * - key 不存在：返回 null
     * - key 已存在：覆盖旧值，并返回旧值
     *
     * 2) 删除元素
     * remove(Object key): V
     * - key 存在：删除并返回旧 value
     * - key 不存在：返回 null
     *
     * 3) 移除所有元素
     * clear(): void
     *
     * 4) 判断是否包含指定 key/value
     * containsKey(Object key): boolean
     * containsValue(Object value): boolean
     *
     * 5) 判空、长度
     * isEmpty(): boolean
     * size(): int
     */

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();

        System.out.println("=== put ===");
        System.out.println("put 张三 -> 北京, return=" + map.put("张三", "北京"));
        System.out.println("put 李四 -> 上海, return=" + map.put("李四", "上海"));
        System.out.println("put 王五 -> 广州, return=" + map.put("王五", "广州"));

        // key 重复：覆盖旧值，并返回旧值
        System.out.println("put 李四 -> 深圳(覆盖), oldValue=" + map.put("李四", "深圳"));

        System.out.println("map=" + map);
        System.out.println("containsKey(李四)=" + map.containsKey("李四"));
        System.out.println("containsValue(上海)=" + map.containsValue("上海"));
        System.out.println("size=" + map.size());

        System.out.println("---------------------------------------");
        System.out.println("=== remove ===");
        System.out.println("remove(李四), removedValue=" + map.remove("李四"));
        System.out.println("remove(不存在), removedValue=" + map.remove("不存在"));

        System.out.println("map=" + map);
        System.out.println("containsKey(李四)=" + map.containsKey("李四"));
        System.out.println("containsValue(深圳)=" + map.containsValue("深圳"));
        System.out.println("size=" + map.size());

        System.out.println("---------------------------------------");
        System.out.println("=== iterate ===");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("---------------------------------------");
        System.out.println("=== clear/isEmpty ===");
        System.out.println("isEmpty=" + map.isEmpty());
        map.clear();
        System.out.println("after clear, isEmpty=" + map.isEmpty());
    }
}
