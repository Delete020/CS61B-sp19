import java.util.Random;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /**
     * Returns the internal path length for an optimum binary search tree of
     * size N. Examples:
     * N = 1, OIPL: 0
     * N = 2, OIPL: 1
     * N = 3, OIPL: 2
     * N = 4, OIPL: 4
     * N = 5, OIPL: 6
     * N = 6, OIPL: 8
     * N = 7, OIPL: 10
     * N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int deep = (int) (Math.log(N) / Math.log(2));
        int maxNode = (int) (Math.pow(2, deep) - 1);
        int OIPL = 0;
        for (int i = 1; i < deep; i++) {
            OIPL += i * (int) (Math.pow(2, i));
        }
        OIPL += (N - maxNode) * deep;
        return OIPL;
    }

    /**
     * Returns the average depth for nodes in an optimal BST of
     * size N.
     * Examples:
     * N = 1, OAD: 0
     * N = 5, OAD: 1.2
     * N = 8, OAD: 1.625
     *
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void randomDeleteAdd(BST b) {
        Random r = new Random();
        b.deleteTakingSuccessor(b.getRandomKey());
        b.add(r.nextInt());
    }

    public static void randomDeleteAdd2(BST b) {
        Random r = new Random();
        b.deleteTakingRandom(b.getRandomKey());
        b.add(r.nextInt());
    }
}
