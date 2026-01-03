public class RecursionDemo2 {
    /*
    不死神兔问题
     1.有一对兔子，从出生后第3个月起每个月都生一对兔子
     2.小兔子长到第三个月后每个月又生一对兔子
     3.假如兔子都不死，问20个月后的兔子有多少对？
     */
    public static void main(String[] args) {
        int result  = rabbit(20);
        System.out.println("20个月后兔子有"+result+" 对");

    }

    private static int rabbit(int i) {
       if(i==1||i==2){
           return 1;
       }
       return rabbit(i-1)+rabbit(i-2);
    }
}
