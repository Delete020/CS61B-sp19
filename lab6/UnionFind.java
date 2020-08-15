public class UnionFind {

    private int[] array;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > array.length) {
            throw new IllegalArgumentException("is not a valid index. ");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        while (array[v1] > 0) {
            v1 = array[v1];
        }
        return -array[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return array[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (connected(v1, v2)) {
            throw new IllegalArgumentException("Is connected.");
        }
        int rootV1 = find(v1);
        int rootV2 = find((v2));
        if (sizeOf(v1) >= sizeOf(v2)) {
            array[rootV1] += array[rootV2];
            array[rootV2] = rootV1;
        } else {
            array[rootV2] += array[rootV1];
            array[rootV1] = rootV2;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (array[root] >= 0) {
            root = array[root];
        }
        while (array[vertex] >= 0) {
            int temp = array[vertex];
            array[vertex] = root;
            vertex = temp;
        }
        return root;
    }
}
