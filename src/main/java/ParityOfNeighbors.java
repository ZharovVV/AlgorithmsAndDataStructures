import java.util.Scanner;

/**
 * В последовательности целых чисел A требуется переставить минимальное число элементов так,
 * чтобы никакие два соседних числа не были одной четности.
 * <p>
 * Формат ввода
 * В первой строке входных данных записано целое число n (1 ≤ n ≤ 100 000) — количество элементов в последовательности.
 * Во второй строке записаны n целых чисел ai (0 ≤ ai ≤ 1 000 000 000) — элементы последовательности.
 * <p>
 * Формат вывода
 * В первой строке выведите число k (0 ≤ k ≤ n) — количество элементов последовательности, которые были переставлены.
 * Во второй строке выведите n чисел bi. Все числа последовательности A должны быть выведены.
 * Любые два соседних элемента последовательности должны быть различной четности.
 * Ровно k индексов последовательности B должны отличаться от последовательности A.
 * Если подходящих последовательностей B несколько, то выведите любую из них.
 * Если переставить элементы последовательности с требуемым условием невозможно, то выведите одно число -1.
 */
public class ParityOfNeighbors {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int arraysLength = scanner.nextInt();
            int numberOfEven = 0;   // Число четных чисел в массиве
            int numberOfOdd = 0;    // Число нечетных чисел в массиве
            int[] array = new int[arraysLength];

            for (int i = 0; i < arraysLength; ++i) {
                int number = scanner.nextInt();
                array[i] = number;
                if (number % 2 == 0) {
                    numberOfEven++;
                } else {
                    numberOfOdd++;
                }
            }
            arrayPermutation(array, numberOfEven, numberOfOdd);
        }
    }

    private static void arrayPermutation(int[] array, int numberOfEven, int numberOfOdd) {
        int n = array.length;
        int countPermutations = 0;  // Число перестановок
        if (
                (n % 2 == 0 && numberOfEven == numberOfOdd) || // Если число элементов четное и количество четных и нечетных элементов совпадают
                        (n % 2 != 0 && (numberOfEven == n / 2 + 1 || numberOfOdd == n / 2 + 1)) // Или если число элементов нечетное
            // и число нечетных или четных элементов равно половине числа элементов массива + 1
        ) {

            if (n % 2 != 0) {   //Для нечетного числа элементов

                if (numberOfEven > numberOfOdd) {    // Если четных больше
                    if (array[0] % 2 != 0) {    // Если в начале стоит нечетный элемент
                        int temp = array[0];    // Меняем местами первый и предпоследний элемент
                        array[0] = array[n - 2];
                        array[n - 2] = temp;
                        countPermutations++;
                    }
                } else {    // Если нечетных больше
                    if (array[0] % 2 == 0) {    // Если в начале стоит четный элемент
                        int temp = array[0];    // Меняем местами первый и предпоследний элемент
                        array[0] = array[n - 2];
                        array[n - 2] = temp;
                        countPermutations++;
                    }
                }

            }

            for (int i = 0; i < n - 1; ++i) {  // Для каждого элемента массива с 0 до предпоследнего
                boolean parity = array[i] % 2 == 0; // Четность i-го элемента, true - если число четное...
                if ((array[i + 1] % 2 == 0) == parity) {    // Если следующий элемент той же четности

                    for (int j = i + 1; j < n; ++j) {       // Для элементов массива, начиная с i+1 и до конца
                        if ((array[j] % 2 == 0) != parity) {    // Если нашёлся элемент другой четности
                            int temp = array[j];    // меняем этот элемент с элементом i+1
                            array[j] = array[i + 1];
                            array[i + 1] = temp;
                            countPermutations++;    // Увеличиваем счётчик перестановок
                            break;                 // Выходим из цикла
                        }
                    }
                }
            }
            // Вывод результатов
            System.out.println(countPermutations);
            for (int i = 0; i < n; ++i) {
                if (i == 0) {
                    System.out.print(array[i]);
                } else {
                    System.out.print(" " + array[i]);
                }
            }

        } else {
            System.out.println(-1);
        }
    }

}
