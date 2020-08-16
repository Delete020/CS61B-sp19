public class BubbleGrid {
    private final int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] pops = new int[darts.length];
        UnionFind uf = new UnionFind(grid.length * grid[0].length);

        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 1; j < grid.length; j++) {
                if (grid[j][i] == 1 && grid[j - 1][i] == 1) {
                    uf.union(xyTo1D(j, i), xyTo1D(j - 1, i));
                }
            }
        }

        for (int i = 0; i < darts.length; i++) {
            int pop = 0;
            int row = darts[i][0];
            int col = darts[i][1];

            if (grid[row][col] != 0) {
                for (int j = 0; j < grid.length; j++) {
                    if (uf.connected(xyTo1D(row, col), xyTo1D(j, col))) {
                        grid[j][col] = 0;
                        pop++;
                    }
                }
            }
            pops[i] = pop;
        }
        return pops;
    }

    private int xyTo1D(int x, int y) {
        return x * grid[0].length + y;
    }
}
