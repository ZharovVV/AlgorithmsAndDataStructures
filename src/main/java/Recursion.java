import java.math.BigInteger;

public class Recursion {
    public static void matryoshka(int number) {
        if (number == 1) {
            System.out.println("Матрёшечка");
        } else {
            System.out.println("Верх матрёшки " + number);
            matryoshka(number - 1);
            System.out.println("Низ матрёшки " + number);
        }
    }

    public static long factorial(int number) {
        if (number <= 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    /**
     * Рекурентный алгоритм возведения числа а в степень power.
     * O(N)
     *
     * @param a
     * @param power
     * @return
     */
    public static BigInteger exponentiation(BigInteger a, int power) {
        if (power == 0) {
            return BigInteger.ONE;
        } else {
            return a.multiply(exponentiation(a, power - 1));
        }
    }

    public static long exponentiation(int a, int power) {
        if (power == 0) {
            return 1;
        } else {
            return a * exponentiation(a, power - 1);
        }
    }

    /**
     * Быстрое возведение в степень
     * O(log N)
     *
     * @param a
     * @param power
     * @return
     */
    public static BigInteger fastExponentiation(BigInteger a, int power) {

        if (power == 0) {
            return BigInteger.ONE;
        } else if (power % 2 == 1) {
            return a.multiply(fastExponentiation(a, power - 1));
        } else {
            return fastExponentiation(a.multiply(a), power / 2);
        }
    }

    public static long fastExponentiation(int a, int power) {
        if (power == 0) {
            return 1;
        } else if (power % 2 == 1) {
            return a * fastExponentiation(a, power - 1);
        } else {
            return fastExponentiation(a * a, power / 2);
        }
    }
}
