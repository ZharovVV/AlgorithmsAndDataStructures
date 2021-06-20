package data.structure;

import java.util.*;


/**
 * Формат входа. Первая строка содержит натуральное число n. Вторая
 * строка содержит n целых чисел parent0
 * , . . . , parentn−1
 * . Для каждого 0 ≤ i ≤ n−1, parent_i — родитель вершины i; если parent_i = −1,
 * то i является корнем. Гарантируется, что корень ровно один. Гарантируется, что данная последовательность задаёт дерево.
 * Формат выхода. Высота дерева.
 * Ограничения. 1 ≤ n ≤ 10^5
 * .
 */
public class TreeExample {

    public static class Tree<T> {

        private Vertex<T> root;

        private final Map<T, Vertex<T>> vertexMap = new HashMap<>();

        public int getHeight() {
            if (root == null) {
                return 0;
            }
            return getHeight(root);
        }

        public void addVertex(Vertex<T> vertex) {
            Vertex<T> checkedVertex = getAndAddIfNotContains(vertex);
            if (checkedVertex.parent == null) {
                root = checkedVertex;
            }
        }

        public void addVertexWithParent(Vertex<T> vertex, Vertex<T> parent) {
            Vertex<T> checkedVertex = getAndAddIfNotContains(vertex);
            Vertex<T> checkedParentVertex = getAndAddIfNotContains(parent);
            checkedVertex.setParent(checkedParentVertex);
            checkedParentVertex.addChild(checkedVertex);
//            if (checkedParentVertex.parent == null) { из-за данного условия не проходит тесты
//                root = checkedParentVertex;
//            }
        }

        private Vertex<T> getAndAddIfNotContains(Vertex<T> vertex) {
            Vertex<T> checkedVertex;
            if (!vertexMap.containsKey(vertex.value)) {
                checkedVertex = vertex;
                vertexMap.put(vertex.value, vertex);
            } else {
                checkedVertex = vertexMap.get(vertex.value);
            }
            return checkedVertex;
        }

        private int getHeight(Vertex<T> root) {
            int height = 1;
            for (Vertex<T> child : root.children) {
                height = Math.max(height, getHeight(child) + 1);
            }
            return height;
        }

        public static Tree<Integer> buildIntegerTree(int[] source) {
            Tree<Integer> integerTree = new Tree<>();
            for (int i = 0; i < source.length; ++i) {
                int parentValue = source[i];
                Vertex<Integer> vertex = new Vertex<>(i);
                if (parentValue == -1) {
                    integerTree.addVertex(vertex);
                } else {
                    Vertex<Integer> parentVertex = new Vertex<>(parentValue);
                    integerTree.addVertexWithParent(vertex, parentVertex);
                }
            }
            return integerTree;
        }


        public static class Vertex<T> {

            private final T value;
            private Vertex<T> parent;
            private final List<Vertex<T>> children = new ArrayList<>();

            public Vertex(T value) {
                this.value = value;
            }

            public T getValue() {
                return value;
            }

            public List<Vertex<T>> getChildren() {
                return new ArrayList<>(children);
            }

            protected void addChild(Vertex<T> child) {
                children.add(child);
            }

            public Vertex<T> getParent() {
                return parent;
            }

            protected void setParent(Vertex<T> parent) {
                this.parent = parent;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int vertexCount = scanner.nextInt();
            int[] source = new int[vertexCount];
            for (int i = 0; i < vertexCount; ++i) {
                source[i] = scanner.nextInt();
            }
            System.out.println(Tree.buildIntegerTree(source).getHeight());
        }
    }
}
