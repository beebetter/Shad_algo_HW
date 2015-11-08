package homeWork1;

/**
 * Created by Artem Ogarkov at 11:51 on 08.11.2015.
 * artem.ogarkov@gmail.com
 */
public class A_ClosedPolyline {

    public A_ClosedPolyline() {
        System.out.println("ini");
    }

    public static void main (String ... args) {
        try {
            System.out.println("main");
            A_ClosedPolyline sol = new A_ClosedPolyline();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
