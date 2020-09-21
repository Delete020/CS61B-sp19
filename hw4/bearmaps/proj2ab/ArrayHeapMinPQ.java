package bearmaps.proj2ab;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private final HashMap<T, Integer> map;
    private Node[] items;
    private int size;
    private int capacity;

    public ArrayHeapMinPQ(int capacity) {
        items = new ArrayHeapMinPQ.Node[capacity + 1];
        map = new HashMap<>();
        size = 0;
        this.capacity = capacity;
    }

    public ArrayHeapMinPQ() {
        this(1);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new NoSuchElementException("Item already exists");
        }
        if (size() > capacity * 0.75) {
            resize(capacity * 2 + 1);
        }
        items[++size] = new Node(item, priority);
        map.put(item, size);
        swim(size);
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items[1].getItem();
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        T smallest = getSmallest();
        map.remove(items[1].getItem());
        map.put(items[size()].getItem(), 1);
        items[1] = items[size()];
        items[size--] = null;
        sink(1);
        if (size() < capacity / 4) {
            resize(capacity / 2 + 1);
        }
        return smallest;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        int position = map.get(item);
        double oldPriority = items[position].getPriority();
        items[position].setPriority(priority);
        if (oldPriority < priority) {
            sink(position);
        } else {
            swim(position);
        }
    }

    private void swim(int i) {
        while (i > 1 && grater(i / 2, i)) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (2 * i < size()) {
            int son = i * 2;
            if (son < size() && grater(son, son + 1)) {
                son += 1;
            }
            if (grater(son, i)) {
                break;
            }
            swap(son, i);
            i = son;
        }
    }

    private void swap(int i, int j) {
        map.put(items[i].getItem(), j);
        map.put(items[j].getItem(), i);
        Node temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private boolean grater(int i, int j) {
        return Double.compare(items[i].getPriority(), items[j].getPriority()) > 0;
    }

    private void resize(int i) {
        capacity = i - 1;
        Node[] newItems = new ArrayHeapMinPQ.Node[i];
        if (size() >= 0) System.arraycopy(items, 1, newItems, 1, size());
        items = newItems;
    }

    private class Node {
        private final T item;
        private double priority;

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }
    }
}
