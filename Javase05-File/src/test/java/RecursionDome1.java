public class RecursionDome1 {
    /*
    递归思想：方法自己调用自己
     */
    public static void main(String[] args) {

        int result  = factorial(5);
        System.out.println(result);
    }

    private static int factorial(int i) {
        if(i==1){
            return 1;
        }
        return i*factorial(i-1);
    }
}
