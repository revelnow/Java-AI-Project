package com.theo.Arrays;

public class ArraysDemo1 {
    /*

      Arrays 数组操作工具类, 专门用于操作数组元素
      public static String toString(类型[] a)
      将数组元素拼接为带有格式的字符串


      public static boolean equals(类型[] a, 类型[] b)
      比较两个数组内容是否相同（元素、个数、顺序）

      public static int binarySearch(int[] a, int key)
      查找元素在数组中的索引 (二分查找法)

      public static void sort(类型[] a)
      对数组进行默认升序排序

     */
    public static void main(String[] args) {
        int[] arr = { 23, 12, 45, 67, 34 };
        System.out.println("Array elements: " + java.util.Arrays.toString(arr));

        int[] arr2 = { 23, 12, 45, 67, 34 };
        System.out.println("Arrays equal: " + java.util.Arrays.equals(arr, arr2));

        java.util.Arrays.sort(arr);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr));

        int index = java.util.Arrays.binarySearch(arr, 45);
        System.out.println("Index of 45: " + index);
    }
}
