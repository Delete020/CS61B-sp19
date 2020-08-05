/* The double ended queue. */
public class LinkedListDeque<T> {

    private final DequeNode sentinel;
    private int size;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new DequeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Creates a linked list deque with first.
     */
    public LinkedListDeque(T first) {
        sentinel = new DequeNode(null, null, null);
        sentinel.next = new DequeNode(first, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**
     * Creates a deep copy of other.
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new DequeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addFirst((T) other.get(i));
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        sentinel.next = new DequeNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        sentinel.prev = new DequeNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last.
     */
    public void printDeque() {
        DequeNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     */
    public T removeFirst() {
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
        }
        return sentinel.next.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     */
    public T removeLast() {
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
        }
        return sentinel.next.item;
    }

    /**
     * Gets the item at the given index.
     */
    public T get(int index) {
        DequeNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(DequeNode p, int index) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, --index);
        }
    }

    private class DequeNode {
        private final T item;
        private DequeNode prev;
        private DequeNode next;

        public DequeNode(T item, DequeNode prev, DequeNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
