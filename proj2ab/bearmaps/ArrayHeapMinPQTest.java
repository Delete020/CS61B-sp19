package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    ArrayHeapMinPQ<Integer> pq = new ArrayHeapMinPQ<>();

    @Test
    public void addTest() {
        pq.add(2, 4);
        pq.add(1, 2);
        assertTrue(pq.contains(2));
        assertTrue(pq.contains(1));
        assertFalse(pq.contains(3));
        assertEquals(2, pq.size());
    }

    @Test
    public void getSmallestTest() {
        pq.add(2, 4);
        pq.add(4, 2);
        pq.add(3, 1);
        assertEquals(3, (int) pq.getSmallest());
    }

    @Test
    public void removeSmallestTest() {
        pq.add(2, 4);
        pq.add(3, 1);
        pq.add(9, 6);
        pq.add(5, 7);
        pq.add(6, 8);
        pq.add(7, 9);
        pq.add(8, 10);
        pq.add(4, 2);
        pq.removeSmallest();
        assertEquals(4, (int) pq.getSmallest());
        assertEquals(7, pq.size());
    }

    @Test
    public void changePriorityTest() {
        pq.add(2, 4);
        pq.add(4, 2);
        pq.add(3, 1);
        pq.changePriority(4, 0);
        assertEquals(4, (int) pq.removeSmallest());
        assertEquals(3, (int) pq.getSmallest());
    }

}

