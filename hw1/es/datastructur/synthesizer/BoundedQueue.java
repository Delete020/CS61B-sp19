package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    int capacity();

    int fillCount();

    void enqueue(T x);

    T dequeue();

    T peek();

    /* is the buffer empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /* is the buffer full. */
    default boolean isFull() {
        return fillCount() == capacity();
    }

    @Override
    Iterator<T> iterator();
}
