package data.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HeapExample {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int inputArraySize = scanner.nextInt();
            int[] inputArray = new int[inputArraySize];
            for (int i = 0; i < inputArraySize; ++i) {
                inputArray[i] = scanner.nextInt();
            }
            MinBinaryHeap minBinaryHeap = MinBinaryHeap.createFromArray(inputArray);
            minBinaryHeap.getExchangePrinter().print();
        }
    }

    interface MinHeap {

        int getMin();

        void insert(int value);

        void remove(int vertexIndex);

        int extractMin();

        void changePriority(int heapIndex, int priority);

        int size();
    }

    /**
     * Все основные операци выполняются за O(logN).
     * Операция получения минимума выполняется за константное время.
     * Операция преобразования массива в бинарную мин-кучу выполняется за O(N*logN).
     */
    public static class MinBinaryHeap implements MinHeap {

        //---------------------------------
        private final ExchangePrinter exchangePrinter = new ExchangePrinter();
        //---------------------------------


        private static final int INITIAL_CAPACITY = 16;
        private static final float REALLOCATE_COEFFICIENT = 1.5f;


        /**
         * Zero-based двоичная мин-куча.
         */
        private int[] heap = new int[INITIAL_CAPACITY];
        private int size = 0;

        @Override
        public int getMin() {
            return heap[0];
        }

        @Override
        public void insert(int value) {
            if (size == heap.length) {
                reallocate();
            }
            heap[size++] = value;
            siftUp(size - 1);
        }

        @Override
        public void remove(int vertexIndex) {
            changePriority(vertexIndex, -1);
            extractMin();
        }

        @Override
        public int extractMin() {
            int minValue = heap[0];
            heap[0] = heap[--size];
            siftDown(0);
            return minValue;
        }

        @Override
        public void changePriority(int vertexIndex, int priority) {
            int oldValue = heap[vertexIndex];
            heap[vertexIndex] = priority;
            if (oldValue > priority) {
                siftUp(vertexIndex);
            } else {
                siftDown(vertexIndex);
            }
        }

        @Override
        public int size() {
            return size;
        }

        private void reallocate() {
            int oldCapacity = heap.length;
            int newCapacity = (int) (oldCapacity * REALLOCATE_COEFFICIENT);
            heap = Arrays.copyOf(heap, newCapacity);
        }

        private int getParentIndex(int vertexIndex) {
            return (vertexIndex - 1) / 2;
        }

        private int getLeftChildIndex(int vertexIndex) {
            return 2 * vertexIndex + 1;
        }

        private int getRightChildIndex(int vertexIndex) {
            return 2 * vertexIndex + 2;
        }

        /**
         * Просеивание элемента вверх
         * O(logN), т.к. куча представляет собой полностью заполненое двоичное дерево -
         * число уровней = logN.
         */
        private void siftUp(int vertexIndex) {
            if (vertexIndex > 0 && heap[getParentIndex(vertexIndex)] > heap[vertexIndex]) {
                int parentIndex = getParentIndex(vertexIndex);
                int parentValue = heap[parentIndex];
                heap[parentIndex] = heap[vertexIndex];
                heap[vertexIndex] = parentValue;
                //--------------
                exchangePrinter.registerExchange(parentIndex, vertexIndex);
                //--------------
                siftUp(parentIndex);
            }
        }

        /**
         * Просеивание элемената вниз.
         */
        private void siftDown(int vertexIndex) {
            int minValueIndex = vertexIndex;
            int leftChildIndex = getLeftChildIndex(vertexIndex);
            if (leftChildIndex < size && heap[leftChildIndex] < heap[vertexIndex]) {
                minValueIndex = leftChildIndex;
            }
            int rightChildIndex = getRightChildIndex(vertexIndex);
            if (rightChildIndex < size && heap[rightChildIndex] < heap[minValueIndex]) {
                minValueIndex = rightChildIndex;
            }
            if (minValueIndex != vertexIndex) {
                int minValue = heap[minValueIndex];
                heap[minValueIndex] = heap[vertexIndex];
                heap[vertexIndex] = minValue;
                //--------------
                exchangePrinter.registerExchange(vertexIndex, minValueIndex);
                //--------------
                siftDown(minValueIndex);
            }
        }

        /**
         * Создает двоичную min-кучу на базе массива.
         */
        public static MinBinaryHeap createFromArray(int[] baseArray) {
            MinBinaryHeap minBinaryHeap = new MinBinaryHeap();
            minBinaryHeap.heap = Arrays.copyOf(baseArray, baseArray.length);
            minBinaryHeap.size = minBinaryHeap.heap.length;
            for (int i = minBinaryHeap.size / 2 - 1; i >= 0; --i) {
                minBinaryHeap.siftDown(i);
            }
            return minBinaryHeap;
        }

        public ExchangePrinter getExchangePrinter() {
            return exchangePrinter;
        }

        @Override
        public String toString() {
            return "MinBinaryHeap{" +
                    "heap=" + Arrays.toString(heap) +
                    ", size=" + size +
                    ", heap.length=" + heap.length +
                    '}';
        }
    }

    /**
     * Класс, нужный исключительно для выполенения условий задачи
     * https://stepik.org/lesson/41560/step/1?unit=20013
     */
    public static class ExchangePrinter {

        private static class ExchangePair {
            private final int a;
            private final int b;

            public ExchangePair(int a, int b) {
                this.a = a;
                this.b = b;
            }

            @Override
            public String toString() {
                return a + " " + b;
            }
        }

        private final List<ExchangePair> exchangePairs = new ArrayList<>();

        public void registerExchange(int a, int b) {
            exchangePairs.add(new ExchangePair(a, b));
        }

        public void print() {
            System.out.println(this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(exchangePairs.size());
            if (!exchangePairs.isEmpty()) {
                for (ExchangePair exchangePair : exchangePairs) {
                    sb.append('\n');
                    sb.append(exchangePair);
                }
            }
            return sb.toString();
        }
    }

}
