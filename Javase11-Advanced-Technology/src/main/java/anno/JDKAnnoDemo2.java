package anno;

@MyAnno1(show1 = "A", show2 = {"B", "C", "D"})
@MyAnno2("A")
public class JDKAnnoDemo2 {

    @MyAnno1(show1 = "B", show2 = "Q")
    public void method() {

    }
}
