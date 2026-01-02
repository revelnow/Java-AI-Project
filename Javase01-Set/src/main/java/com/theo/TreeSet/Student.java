package com.theo.TreeSet;


public class Student implements Comparable<Student> {
    private int age;
    private String name;

    @Override
    public int compareTo(Student o) {
        int ageRusult = this.age - o.age;
        int nameRusult = ageRusult == 0 ? o.name.compareTo(this.name) : ageRusult;
        return nameRusult == 0 ? 1 : nameRusult;

    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }


    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
