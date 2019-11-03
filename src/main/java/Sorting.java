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
}
