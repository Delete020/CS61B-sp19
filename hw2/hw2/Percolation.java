package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[][] grid;
    private int openSites;
    private final WeightedQuickUnionUF uf;
    private final int top;
    private final int bottom;

    /**
     * create N-by-N grid, with all sites initially blocked.
     */
    public Percolation(int N) {
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        top = 0;
        bottom = N * N + 1;
        openSites = 0;
    }

    /*  open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        unionSites(row, col);
        openSites++;
    }

    /* is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    /* is the site (row, col) full?*/
    public boolean isFull(int row, int col) {
        return uf.connected(top, xyTo1D(row, col));
    }

    /* number of open sites*/
    public int numberOfOpenSites() {
        return openSites;
    }

    /* does the system percolate?*/
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    private int xyTo1D(int r, int c) {
        return r * grid.length + c;
    }

    private void unionSites(int r, int c) {
        int curr = xyTo1D(r, c);
        if (r == 0) {
            uf.union(top, curr);
        }
        if (r == grid.length - 1) {
            uf.union(curr, bottom);
        }

        for (int i = -1; i < 2; i += 2) {
            if (r + i >= 0 && r + i < grid.length && isOpen(r + i, c)) {
                uf.union(curr, xyTo1D(r + i, c));
            }
            if (c + i >= 0 && c + i < grid.length && isOpen(r, c + i)) {
                uf.union(curr, xyTo1D(r, c + i));
            }
        }
    }

    private void validate(int r, int c) {
        if (r >= grid.length || c >= grid.length) {
            throw new IllegalArgumentException("Out of scope");
        }
        if (r < 0 || c < 0) {
            throw new IndexOutOfBoundsException("It shouldn't be negative.");
        }
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(0, 1);
    }
}
