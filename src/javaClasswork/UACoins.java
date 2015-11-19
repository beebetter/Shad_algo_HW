package javaClasswork;

/**
 * Created by Artem Ogarkov at 20:24 on 16.11.2015.
 * artem.ogarkov@gmail.com
 */
//We have to write this before java 1.5
import java.util.Arrays;
import java.util.Optional;

public class UACoins {

    private final int value;
    private final String name;
    public UACoins(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public static final UACoins ONE = new UACoins(1, "ONE");
    public static final UACoins TWO = new UACoins(2, "TWO");
    public static final UACoins FIVE = new UACoins(5, "FIVE");

    public static final UACoins[] values = {ONE, TWO, FIVE};
    public static int calcTotal(UACoins ... coins) {
        int sum = 0;
        for (UACoins coin : coins) {
            sum += coin.getValue();
        }
        return sum;
    }

    public static Optional<UACoins> valueOf(String name) {
        for (UACoins coin : values) {
            if (coin.name.equals(name)) {
                return Optional.of(coin);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return name;
    }


    public static void main(String[] args) {
        System.out.println(UACoins.calcTotal(UACoins.ONE, UACoins.FIVE));
        System.out.println(Arrays.toString(UACoins.values));
    }
}
