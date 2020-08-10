package es.datastructur.synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        assertEquals(10, arb.capacity());

        /* Test enqueue, fillCount. */
        arb.enqueue(1);
        arb.enqueue(4);
        arb.enqueue(6);
        arb.enqueue(7);
        assertEquals(4, arb.fillCount());

        /* Test equals. */
        arb2.enqueue(1);
        arb2.enqueue(4);
        arb2.enqueue(6);
        arb2.enqueue(7);
        assertTrue(arb.equals(arb2));

        arb2.enqueue(2);
        assertFalse(arb.equals(arb2));

        /* Test dequeue. */
        arb.dequeue();
        arb.dequeue();
        assertEquals(2, arb.fillCount());
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(8);

        arb.enqueue(1);
        arb.enqueue(4);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(1);
        arb.enqueue(4);
        arb.enqueue(6);
        arb.enqueue(7);

        for (Integer i : arb) {
            System.out.println(i);
        }
    }
}
