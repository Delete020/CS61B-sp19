public class ArrayDeque<T> {
    private T[] items;
    private int front;
    private int last;
    private int size;

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 0;
        last = front + 1;
        size = 0;
    }

    /**
     * Creates a deep copy of other.
     */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        front = other.front;
        last = other.last;
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (isFull()) {
            increaseSize();
        }
        items[front] = item;
        size += 1;
        front = moveLeft(front);
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (isFull()) {
            increaseSize();
        }
        items[last] = item;
        size += 1;
        last = moveRight(last);
    }

    /**
     * Removes and returns the item at the front of the deque.
     */
    public T removeFirst() {
        if (spare()) {
            decreaseSize();
        }
        front = moveRight(front);
        T ptr = items[front];
        items[front] = null;
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        return ptr;

    }

    /**
     * Removes and returns the item at the back of the deque.
     */
    public T removeLast() {
        if (spare()) {
            decreaseSize();
        }
        last = moveLeft(last);
        T ptr = items[last];
        items[last] = null;
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        return ptr;
    }


    /**
     * Move one step to the left.
     */
    private int moveRight(int move) {
        return (move + 1) % items.length;
    }

    /**
     * Move one step to the right.
     */
    private int moveLeft(int move) {
        return (items.length + move - 1) % items.length;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns true if deque is full, false otherwise.
     */
    private boolean isFull() {
        return moveRight(last) == front;
    }

    /**
     * Returns true if deque has too much spare, false otherwise.
     */
    private boolean spare() {
        return size < items.length / 4 && items.length > 8;
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
        for (int i = front + 1; i % items.length != last; i++) {
            System.out.println(items[i % items.length] + " ");
        }
    }

    /**
     * Gets the item at the given index.
     */
    public T get(int index) {
        return items[(front + index) % items.length];
    }

    /**
     * When the array is full, expand the size.
     */
    private void increaseSize() {
        T[] newItems = (T[]) new Object[items.length * 2];
        if (front < last) {
            System.arraycopy(items, 1, newItems, 1, last);
        } else {
            int newFront = items.length + front;
            System.arraycopy(items, 0, newItems, 0, last);
            System.arraycopy(items, front + 1, newItems, newFront + 1, items.length - front - 1);
            front = newFront;
        }
        items = newItems;
    }

    /**
     * When the array has too much spare, decrease the size.
     */
    private void decreaseSize() {
        T[] newItems = (T[]) new Object[items.length / 2];
        int oldFront = moveRight(front);
        for (int i = 1; i < size; i++) {
            newItems[i] = items[oldFront];
            oldFront = moveRight(oldFront);
        }
        items = newItems;
        front = 0;
        last = front + size + 1;
    }

}
