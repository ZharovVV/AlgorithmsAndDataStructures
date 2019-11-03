import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int a = 7;
        int power = 1000;

        long startTime = System.currentTimeMillis();

        System.out.println(Recursion.exponentiation(a, power));

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " ms");

        long startTime1 = System.currentTimeMillis();

        System.out.println(Recursion.fastExponentiation(a, power));

        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1 + " ms");
    }

}
