package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

public class PercolationStats {
    private int T; /* # of independent computational experiments */
    private double[] numOpenedSites;
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0.");
        }

        this.T = T;
        numOpenedSites = new double[T];

        for (int i = 0; i < T; i++){
            Percolation p = pf.make(N);
            while (!p.percolates()){
                int col = StdRandom.uniform(N); /* Get the random col */
                int row = StdRandom.uniform(N); /* Get the random row */
                p.isOpen(col, row);
            }
            numOpenedSites[i] = (double) p.numberOfOpenSites()/ (N * N);
        }
    }

    public double mean(){
        return StdStats.mean(numOpenedSites);
    }

    public double stddev(){
        return StdStats.stddev(numOpenedSites);

        }

    public double confidenceLow(){
        double SqrtT = Math.sqrt(T);
        return mean() - (1.96 * stddev() / SqrtT );
    }

    public double confidenceHigh(){
        double SqrtT = Math.sqrt(T);
        return mean() + (1.96 * stddev() / SqrtT );
    }

}
