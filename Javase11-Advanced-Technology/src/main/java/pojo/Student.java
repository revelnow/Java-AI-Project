package pojo;

public class Student {
    private String name;
    private int age;

    public void eat() {
        System.out.println("学生吃饭");
    }

    public void eat(int num) {
        System.out.println("学生吃了" + num + "顿饭");
    }

    public Student() {
    }

    public Student(String name) {
    }

    private Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
