import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node root, K k) {
        if (root == null) {
            return null;
        } else if (root.key.compareTo(k) > 0) {
            return get(root.left, k);
        } else if (root.key.compareTo(k) < 0) {
            return get(root.right, k);
        }
        return root.val;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size += 1;
    }

    private Node put(Node root, K key, V val) {
        if (root == null) {
            root = new Node(key, val);
        } else if (root.key.compareTo(key) > 0) {
            root.left = put(root.left, key, val);
        } else if (root.key.compareTo(key) < 0) {
            root.right = put(root.right, key, val);
        }
        return root;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> BSTMapSet = new HashSet<>();
        keySet(root, BSTMapSet);
        return BSTMapSet;
    }

    private void keySet(Node root, Set<K> set) {
        if (root != null) {
            keySet(root.left, set);
            set.add(root.key);
            keySet(root.right, set);
        }
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V remove = get(key);
        root = remove(root, key);
        size -= 1;
        return remove;
    }

    private Node remove(Node r, K k) {
        if (r == null) {
            return null;
        } else if (root.key.compareTo(k) > 0) {
            r.left = remove(r.left, k);
        } else if (root.key.compareTo(k) < 0) {
            r.right = remove(r.right, k);
        } else {
            if (r.right == null) {
                return r.left;
            }
            if (r.left == null) {
                return r.right;
            }
            Node p = r;
            r = min(p.right);
            r.right = removeMin(p.right);
            r.left = p.left;
        }
        return r;
    }

    private Node removeMin(Node r) {
        if (r.left == null) {
            return r.right;
        }
        r.left = removeMin(r.left);
        return r;
    }

    private Node min(Node r) {
        if (r.left == null) {
            return r;
        }
        return min(r.left);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        root = remove(root, key);
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.key + " ");
            printInOrder(root.right);
        }
    }

    private class BSTMapIterator implements Iterator<K> {
        Set<K> set = keySet();

        @Override
        public boolean hasNext() {
            return !set.isEmpty();
        }

        @Override
        public K next() {
            K n = set.iterator().next();
            set.remove(set.iterator().next());
            return n;
        }
    }

    private class Node {
        private final K key;
        private final V val;
        private Node left;
        private Node right;

        Node(K k, V v) {
            key = k;
            val = v;
        }
    }
}
