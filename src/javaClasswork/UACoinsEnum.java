package javaClasswork;

/**
 * Created by Artem Ogarkov at 20:29 on 16.11.2015.
 * artem.ogarkov@gmail.com
 */
public enum UACoinsEnum {
    ONE(1), TWO(2), FIVE(5);

    private final int value;
    UACoinsEnum(int value) {
        this.value = value;
    }
    public int getValue (){
        return value;
    }
}
class SomeClass {

    public static int calcTotal(UACoinsEnum ... coins) {
        return 0;
    }
    public static void main (String ... args) {
        calcTotal(UACoinsEnum.FIVE, UACoinsEnum.ONE);
    }
}
