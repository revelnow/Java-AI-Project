package anno;

@SuppressWarnings("all")
public class JDKAnnoDemo {

    public static void main(String[] args) {
        method();

        int a = 10;
        int b = 20;
        int max = a > b ? a : b;

    }

    @Deprecated
    public static void method() {

    }
}
