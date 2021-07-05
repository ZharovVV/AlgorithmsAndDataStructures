package data.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapExampleTest {

    @Test
    public void main_check_1() {
        HeapExample.MinBinaryHeap minBinaryHeap = HeapExample.MinBinaryHeap.createFromArray(
                new int[]{0, 1, 2, 3, 4, 5}
        );
        assertEquals("0", minBinaryHeap.getExchangePrinter().toString());
    }

    @Test
    public void main_check_2() {
        HeapExample.MinBinaryHeap minBinaryHeap = HeapExample.MinBinaryHeap.createFromArray(
                new int[]{7, 6, 5, 4, 3, 2}
        );
        assertEquals("4\n2 5\n1 4\n0 2\n2 5", minBinaryHeap.getExchangePrinter().toString());
    }

    @Test
    public void main_check_3() {
        HeapExample.MinBinaryHeap minBinaryHeap = HeapExample.MinBinaryHeap.createFromArray(
                new int[]{5, 4, 3, 2, 1}
        );
        assertEquals("3\n1 4\n0 1\n1 3", minBinaryHeap.getExchangePrinter().toString());
    }

    @Test
    public void main_check_4() {
        HeapExample.MinBinaryHeap minBinaryHeap = HeapExample.MinBinaryHeap.createFromArray(
                new int[]{1, 2, 3, 4, 5}
        );
        assertEquals("0", minBinaryHeap.getExchangePrinter().toString());
    }

    @Test
    public void check_insert() {
        HeapExample.MinBinaryHeap minBinaryHeap = createByInserting();
        assertEquals(17, minBinaryHeap.size());
        assertEquals(0, minBinaryHeap.getMin());
    }

    @Test
    public void check_extractMin() {
        HeapExample.MinBinaryHeap minBinaryHeap = createByInserting();
        minBinaryHeap.extractMin();
        minBinaryHeap.extractMin();
        minBinaryHeap.extractMin();
        minBinaryHeap.extractMin();
        int lastExtractedMinValue = minBinaryHeap.extractMin();
        assertEquals(9, lastExtractedMinValue);
        assertEquals(12, minBinaryHeap.getMin());
        assertEquals(12, minBinaryHeap.size());
    }

    @Test
    public void check_remove() {
        HeapExample.MinBinaryHeap minBinaryHeap = createByInserting();
        minBinaryHeap.remove(0);
        System.out.println("after removing: " + minBinaryHeap);
        assertEquals(16, minBinaryHeap.size());
        assertEquals(3, minBinaryHeap.getMin());
        minBinaryHeap.insert(0);
        System.out.println("after inserting back: " + minBinaryHeap);
    }

    @Test
    public void check_changePriority() {
        HeapExample.MinBinaryHeap minBinaryHeap = createByInserting();
        minBinaryHeap.changePriority(minBinaryHeap.size() - 2, 2);
        minBinaryHeap.extractMin();
        System.out.println("Heap after change priority element with 100 to 2 and after extractMin call:");
        System.out.println(minBinaryHeap);
        assertEquals(16, minBinaryHeap.size());
        assertEquals(2, minBinaryHeap.getMin());
    }

    private HeapExample.MinBinaryHeap createByInserting() {
        int[] array = new int[]{100, 85, 54, 60, 40, 31, 27, 58, 72, 17, 14, 12, 9, 8, 6, 3, 0};
        HeapExample.MinBinaryHeap minBinaryHeap = new HeapExample.MinBinaryHeap();
        for (int j : array) {
            minBinaryHeap.insert(j);
        }
        System.out.println(minBinaryHeap);
        return minBinaryHeap;
    }

}