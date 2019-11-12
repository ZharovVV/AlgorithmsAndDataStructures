import java.util.Arrays;

public class Sorting {
    /**
     * Сортировка вставками.
     * O(n^2).
     *
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array) {
        long startTime = System.currentTimeMillis();

        int N = array.length;
        for (int top = 1; top < N; top++) {             // top - индекс вставляемого элемента (от второго до последнего).
            int k = top;                                // k - текущее положение вставляемого элемента (в.э.).
            while (k > 0 && array[k - 1] > array[k]) {  // Пока текущее положение в.э. не является самым левым. и элемент слева больше в.э..
                int temp = array[k - 1];    // меняем местами элементы
                array[k - 1] = array[k];
                array[k] = temp;
                k--;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка вставками: ");
        System.out.println(endTime - startTime + " ms");
        return array;
    }

    /**
     * Сортировка методом выбора.
     * O(n^2).
     *
     * @param array
     * @return
     */
    public static int[] choiceSort(int[] array) {
        long startTime = System.currentTimeMillis();

        int N = array.length;
        for (int pos = 0; pos < N - 1; pos++) {      //  pos - сравниваемая позиция (с первого до предпоследнего).
            for (int k = pos + 1; k < N; k++) {      // сравниваем с элементами (от pos + 1 до последнего).
                if (array[k] < array[pos]) {         // если находится элемент меньше сравниваемой позиции
                    int temp = array[pos];           // меняем их местами
                    array[pos] = array[k];
                    array[k] = temp;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка методом выбора: ");
        System.out.println(endTime - startTime + " ms");
        return array;
    }


    /**
     * Сортировка "пузырьком".
     * O(n^2).
     *
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        long startTime = System.currentTimeMillis();

        int N = array.length;
        for (int bypass = 1; bypass < N; bypass++) {    // bypass - число проходов, их N - 1.
            for (int k = 0; k < N - bypass; k++) {      // N - bypass - число сравнений за проход. Оно уменьшается, т.к. число проходов изменяется от 1 до N.
                // k и k + 1 - сравниваемые элементы, k с первого элемента до N - bypass.
                if (array[k] > array[k + 1]) {          // если элемент слева больше элемента справа, то
                    int temp = array[k + 1];            // меняем их местами
                    array[k + 1] = array[k];
                    array[k] = temp;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка методом выбора: ");
        System.out.println(endTime - startTime + " ms");
        return array;
    }


    /**
     * Слияние отсортированных массивов.
     *
     * @param a - отсортированный по возрастанию массив.
     * @param b - другой отсортированный по возрастанию массив.
     * @return c - массив, полученный слиянием a и b.
     */
    public static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;  // индекс в массиве a
        int k = 0;  // индекс в массиве b
        int n = 0;  // индекс в массиве c
        while (i < a.length && k < b.length) {  // до тех пор пока полностью не пройден один из входных массивов
            if (a[i] <= b[k]) { // если первый непройденный элемент из a >= b
                c[n] = a[i];    // кладем этот элемент в массив c
                i++;    // увеличиваем индекс в a
                n++;    // увеличиваем индекс в c
            } else {
                c[n] = b[k];
                k++;
                n++;
            }
        }
        while (i < a.length) {
            c[n] = a[i];    // кладем этот элемент в массив c
            i++;    // увеличиваем индекс в a
            n++;    // увеличиваем индекс в c
        }
        while (k < b.length) {
            c[n] = b[k];
            k++;
            n++;
        }
        return c;
    }


    /**
     * Сортировка слиянием.
     * O(nlog(n)).
     * @param a - сортируемый массив.
     * @return a - отсортированный массив.
     */
    public static int[] mergeSort(int[] a) {
        if (a.length <= 1) {
            return a;
        }
        int middle = a.length / 2;
        int[] l = Arrays.copyOfRange(a, 0, middle);
        int[] r = Arrays.copyOfRange(a, middle, a.length);
        mergeSort(l);
        mergeSort(r);
        int[] c = merge(l, r);
        for (int i = 0; i < a.length; ++i) {
            a[i] = c[i];
        }
        return a;
    }
}
