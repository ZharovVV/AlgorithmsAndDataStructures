package data.structure;

import org.junit.jupiter.api.Test;

import static data.structure.TreeExample.Tree.buildIntegerTree;
import static org.junit.jupiter.api.Assertions.*;

class TreeExampleTest {

    @Test
    public void check_1() {
        assertEquals(3, buildIntegerTree(new int[]{4, -1, 4, 1, 1}).getHeight());
    }

    @Test
    public void check_2() {
        assertEquals(4, buildIntegerTree(new int[]{-1, 0, 4, 0, 3}).getHeight());
    }

    @Test
    public void check_3() {
        assertEquals(1, buildIntegerTree(new int[]{-1}).getHeight());
    }

    @Test
    public void check_4() {
        assertEquals(4, buildIntegerTree(new int[]{9, 7, 5, 5, 2, 9, 9, 9, 2, -1}).getHeight());
    }

    @Test
    public void check_5() {
        assertEquals(6, buildIntegerTree(new int[]{2, 2, 3, 5, 5, 7, 7, 9, 9, -1}).getHeight());
    }

    @Test
    public void check_6() {
        assertEquals(3, buildIntegerTree(new int[]{4, -1, 4, 1, 1}).getHeight());
    }

    @Test
    public void check_7() {
        assertEquals(17, buildIntegerTree(new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}).getHeight());
    }

    @Test
    public void check_8() {
        assertEquals(2, buildIntegerTree(new int[]{-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}).getHeight());
    }

    @Test
    public void check_9() {
        assertEquals(0, buildIntegerTree(new int[]{}).getHeight());
    }

    @Test
    public void check_10() {
        int vertexCount = (int) Math.pow(10, 5);
        int[] source = new int[vertexCount];
        source[0] = -1;
        for (int i = 1; i < vertexCount; ++i) {
            source[i] = 0;
        }
        source[vertexCount - 1] = 1;
        assertEquals(3, buildIntegerTree(source).getHeight());
    }
}