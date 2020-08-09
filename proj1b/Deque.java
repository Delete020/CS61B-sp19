/**
 * contains all of the methods that appear in both ArrayDeque and LinkedListDeque.
 */
public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    T removeFirst();

    T removeLast();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void printDeque();

    T get(int index);
}
