import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUnionFind {

    @Test
    public void test() {
        UnionFind u = new UnionFind(10);

        assertEquals(1, u.sizeOf(1));

        u.union(2, 3);
        u.union(1, 2);
        u.union(5, 7);
        u.union(8, 4);
        u.union(7, 2);
        assertEquals(2, u.find(3));
        assertEquals(5, u.sizeOf(2));
        assertEquals(5, u.sizeOf(7));
        assertEquals(5, u.parent(7));
        assertEquals(8, u.parent(4));

        u.union(0, 6);
        u.union(6, 4);
        u.union(6, 3);
        assertEquals(2, u.find(4));
        assertEquals(2, u.find(6));

    }
}
