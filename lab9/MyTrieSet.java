import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private final Node root = new Node(false);

    @Override
    public void clear() {
        root.map.clear();
    }

    @Override
    public boolean contains(String key) {
        Node ptr = root;
        for (int i = 0; i < key.length(); i++) {
            if (!ptr.map.containsKey(key.charAt(i))) {
                return false;
            }
            ptr = ptr.map.get(key.charAt(i));
        }
        return true;
    }

    @Override
    public void add(String key) {
        if (key == null && key.length() < 1) {
            return;
        }
        Node ptr = root;
        for (int i = 0; i < key.length(); i++) {
            if (!ptr.map.containsKey(key.charAt(i))) {
                ptr.map.put(key.charAt(i), new Node(false));
            }
            ptr = ptr.map.get(key.charAt(i));
        }
        ptr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null && prefix.length() < 1) {
            return null;
        }
        List<String> collect = new ArrayList<>();
        Node ptr = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (!ptr.map.containsKey(prefix.charAt(i))) {
                return null;
            }
            ptr = ptr.map.get(prefix.charAt(i));
        }
        return keysWithPrefixHelp(prefix, ptr, collect);
    }

    private List<String> keysWithPrefixHelp(String s, Node x, List<String> list) {
        if (x.isKey) {
            list.add(s);
        }
        for (Map.Entry<Character, Node> entry : x.map.entrySet()) {
            keysWithPrefixHelp(s + entry.getKey(), entry.getValue(), list);
        }
        return list;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    private class Node {
        private final HashMap<Character, Node> map;
        private boolean isKey;

        Node(boolean b) {
            map = new HashMap<>();
            isKey = b;
        }
    }

}
