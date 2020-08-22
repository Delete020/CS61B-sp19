import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int INITIALSIZE = 16;
    private static final double LOADFACTOR = 0.75;
    private double loadFactor;
    private int capability;
    private int size;
    private ArrayList<Entry> bucket;

    public MyHashMap() {
        capability = INITIALSIZE;
        loadFactor = LOADFACTOR;
        bucket = new ArrayList<>(capability);
        bucket.addAll(Collections.nCopies(capability, null));
        size = 0;
    }

    public MyHashMap(int initialSize) {
        capability = initialSize;
        loadFactor = LOADFACTOR;
        bucket = new ArrayList<>(initialSize);
        bucket.addAll(Collections.nCopies(capability, null));
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        capability = initialSize;
        this.loadFactor = loadFactor;
        bucket = new ArrayList<>(initialSize);
        bucket.addAll(Collections.nCopies(capability, null));
        size = 0;
    }

    @Override
    public void clear() {
        bucket.clear();
        bucket.addAll(Collections.nCopies(capability, null));
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        Entry temp = bucket.get(hash(key));
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (size > capability * loadFactor) {
            resize();
        }
        if (bucket.get(hash(key)) == null) {
            bucket.set(hash(key), new Entry(key, value));
            size += 1;
            return;
        }
        Entry temp = bucket.get(hash(key));
        while (temp != null) {
            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }
            if (temp.next == null) {
                temp.next = new Entry(key, value);
                size += 1;
            }
            temp = temp.next;
        }
    }

    private void resize() {
        MyHashMap<K, V> temp = new MyHashMap<>(capability * 2);
        for (int i = 0; i < capability; i++) {
            Entry p = bucket.get(i);
            while (p != null) {
                temp.put(p.key, p.value);
                p = p.next;
            }
        }
        capability = temp.capability;
        bucket = temp.bucket;
    }

    private int hash(K key) {
        return (0x7fffffff & key.hashCode()) % capability;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < capability; i++) {
            Entry temp = bucket.get(i);
            while (temp != null) {
                set.add(temp.key);
                temp = temp.next;
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private class Entry {
        K key;
        V value;
        Entry next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
