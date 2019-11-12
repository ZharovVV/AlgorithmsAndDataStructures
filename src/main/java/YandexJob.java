import java.util.Arrays;

public class YandexJob {
    /**
     * @param a - произвольный массив, например [4,8,11,2,3,1,9]
     * @return string - строка вида 1-4,8-9,11
     */
    public static String function(int[] a) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(a);
        sb.append(a[0]);    // присоединяем первый элемент массива
        for (int i = 0; i < a.length - 1; ++i) {
            if (a[i] + 1 != a[i + 1]) { // Если следующий элемент больше текущего не на 1
                sb.append("-").append(a[i]);    // присоединяем "-(текущий элемент)"
                sb.append(", ").append(a[i + 1]);   // присоединяем ", (следующий элемент)"
            } else if (i + 1 == a.length - 1) {     // ... и если он последний
                sb.append("-").append(a[i + 1]);    // присоединяем следующий элемент
            }
        }
        return sb.toString();
    }
}
