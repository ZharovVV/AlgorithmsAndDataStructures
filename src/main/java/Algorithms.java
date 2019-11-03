public class Algorithms {

    /**
     * Алгоритм обращения массива.
     * Без копирования массива.
     *
     * @param array
     * @return
     */
    public static int[] reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

    /**
     * Циклический сдвиг массива влево.
     *
     * @param array
     * @return
     */
    public static int[] leftShift(int[] array) {
        int temp = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = temp;
        return array;
    }

    /**
     * Циклический сдвиг массива вправо.
     * @param array
     * @return
     */
    public static int[] rightShift(int[] array) {
        int temp = array[array.length - 1];
        for (int i = array.length - 1; i > 0; i--) {    //int i = array.length - 2; i > -1; i--
            array[i] = array[i - 1];                    //array[i + 1] = array[i];
        }
        array[0] = temp;
        return array;
    }
}
