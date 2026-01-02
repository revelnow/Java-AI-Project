package com.theo.HashSet;

import java.util.HashSet;

public class PersonTest {

    public static void main(String[] args) {
        HashSet<Person> h = new HashSet<Person>();
        h.add(new Person("Jerry", 18));
        h.add(new Person("Tom", 20));
        h.add(new Person("Jack", 22));
        h.add(new Person("Jerry", 18));
        h.add(new Person("张三", 20));
        System.out.println(h);
    }

}
