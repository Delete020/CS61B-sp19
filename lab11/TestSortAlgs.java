import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Double> tas = new Queue<>();
        tas.enqueue(2.2);
        tas.enqueue(4.3);
        tas.enqueue(2.1);
        tas.enqueue(7.2);

        tas = QuickSort.quickSort(tas);
        assertTrue(isSorted(tas));

        Queue<Integer> tas1 = new Queue<>();
        for (int i = 9999; i > 0; i--) {
            tas1.enqueue(i);
        }

        tas1 = QuickSort.quickSort(tas1);
        assertTrue(isSorted(tas1));
    }

    @Test
    public void testMergeSort() {
        Queue<Double> tas = new Queue<>();
        tas.enqueue(2.2);
        tas.enqueue(4.3);
        tas.enqueue(2.1);
        tas.enqueue(7.2);

        tas = MergeSort.mergeSort(tas);
        assertTrue(isSorted(tas));

        Queue<Integer> tas1 = new Queue<>();
        for (int i = 9999; i > 0; i--) {
            tas1.enqueue(i);
        }

        tas1 = MergeSort.mergeSort(tas1);
        assertTrue(isSorted(tas1));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
