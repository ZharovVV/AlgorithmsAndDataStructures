package data.structure;

import java.util.Arrays;

public class DisjointSetExample {

    interface DisjointSet {

        int makeSet(int i);

        int find(int i);

        void union(int i, int j);
    }

    /**
     * Непересекающееся множество
     */
    public static class TreeDisjointSet implements DisjointSet {

        private static final int INITIAL_CAPACITY = 16;
        private static final float REALLOCATE_COEFFICIENT = 1.5f;

        /**
         * Массив родителей.
         * Например массив [2,1,1,1,3,3] представляет собой следующее дерево:
         * 1--
         * | |
         * 2 3--
         * | | |
         * 0 4 5
         */
        private int[] parent = new int[INITIAL_CAPACITY];

        /**
         * Массив 0 <= i <= n
         * Высоты поддеревьев для каждого элемента со значением i.
         * В случае применения эвристики сжатия путей rank будет не обязательно равен высоте поддерева.
         * <p>
         * Ранг любой некорневой вершины всегда меньше ранга её родителя.
         */
        private int[] rank = new int[INITIAL_CAPACITY];

        /**
         * Создание одноэлементнога множества.
         * O(1).
         *
         * @param i - элемент, на основании которого будет созданно одноэлементное множество.
         * @return - идентификатор множества (в данном случае корневой элемент множества).
         * Т.к метод создает одноэлементное множество, то единственный элемент будет являться корнем.
         */
        @Override
        public int makeSet(int i) {
            if (i >= parent.length) {
                relocate(i);
            }
            parent[i] = i;
            rank[i] = 0;
            return i;
        }

        private void relocate(int i) {
            int oldCapacity = parent.length;
            int newCapacity = Math.max((int) (oldCapacity * REALLOCATE_COEFFICIENT), (int) (i * REALLOCATE_COEFFICIENT));
            parent = Arrays.copyOf(parent, newCapacity);
            rank = Arrays.copyOf(rank, newCapacity);
        }

        /**
         * Нахождение идентификатора множества, в которое входит элемент i.
         * O(высота дерева = logN).
         *
         * @param i - элемент.
         * @return - идентификатор множества, в которое входит элемент i.
         */
        @Override
        public int find(int i) {
            //Обычный алгоритм
            //Проходим по всем родительским элементам в поиске корневого элемента
//            if (parent[i] == i) {
//                return i;
//            }
//            return find(parent[i]);

            //Алгоритм со сжатием путей
            //В ходе прохода по родительским элементам для каждого элемента будет установлен parent, равный корню.
            //Таким образом высота дерева будет уменьшаться и при последующем вызове будет затрачено меньшее время на операцию поиска.
            //Время работы становится практически константным.
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /**
         * Объединяет множества, в которые входит элемент i и j.
         * O(высота дерева = logN).
         *
         * @param i - элемент
         * @param j - элемент
         */
        @Override
        public void union(int i, int j) {
            int iId = find(i);
            int jId = find(j);
            if (iId == jId) {
                return;
            }
            //NB: Под rank дерева понимаем высоту дерева.
            //Однако если применяется эвристика сжатия путей, то rank может быть не равен высоте дерева.

            //Если rank дерева, в которое входит i больше чем rank дерева, в которое входит j,
            //то добавляем корневой элемент дерева j в качестве дочернего элемента корня дерева i.
            if (rank[iId] > rank[jId]) {
                parent[jId] = iId;
            } else {
                //Иначе - наоборот
                parent[iId] = jId;
                //Если rank деревьев совпадают, то увеличиваем rank дерева, в которое добавили другое на 1.
                if (rank[iId] == rank[jId]) {
                    rank[jId]++;
                }
            }
        }
    }

}
