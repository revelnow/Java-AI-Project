package anno;

public @interface MyAnno1 {

    String show1();

    String[] show2();

    String show3() default "C";

}
