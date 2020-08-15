package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double openSites[];
    private int times;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        openSites = new double[T];
        times = T;
        for (int i = 0; i < T; i++) {
            Percolation grid = pf.make(N);
            while (!grid.percolates()) {
                grid.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            openSites[i] += grid.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(openSites) / times;
    }

    // sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(openSites) / times;
    }

    // low endpoint of 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(times));
    }

    // high endpoint of 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(times));
    }
}
