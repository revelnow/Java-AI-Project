package my_enum;

public class EnumTest {
    /*
        枚举介绍: Java中的一种特殊的类型, 常用于信息的标记和分类

                    1. 常量
                    2. 枚举 (入参严谨, 提示性更好代码优雅)
     */
    public static void main(String[] args) {
        useSeason(Season.WINTER);

        System.out.println(Season.SPRING.ordinal());
        System.out.println(Season.SUMMER.ordinal());
        System.out.println(Season.AUTUMN.ordinal());
        System.out.println(Season.WINTER.ordinal());
    }

    public static void useSeason(Season season) {
        switch (season) {
            case SPRING:
                System.out.println("春季");
                break;
            case SUMMER:
                System.out.println("夏季");
                break;
            case AUTUMN:
                System.out.println("秋季");
                break;
            case WINTER:
                System.out.println("冬季");
                break;
        }
    }
}

enum Season {
    SPRING, SUMMER, AUTUMN, WINTER;
}