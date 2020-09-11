package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    private static Random rd = new Random(500);

    public static void buildKDTreeTest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p7 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);

        KDTree kd = new KDTree(List.of(p1, p2, p7, p3, p4, p5, p6));
    }

    private List<Point> randomPoints(int n) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point (rd.nextDouble() * 1000, rd.nextDouble() * 1000));
        }
        return points;
    }

    private void nearestTestWithPointCountAndQueries(int pointCount, int queryCount) {
        List<Point> points = randomPoints(pointCount);
        KDTree kd = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        List<Point> queries = randomPoints(queryCount);
        for (Point p : queries) {
            Point actual = kd.nearest(p.getX(), p.getY());
            Point expected = nn.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }

    @Test
    public void nearestCorrectTest() {
        int pointCount = 100000;
        int queryCount = 10000;
        nearestTestWithPointCountAndQueries(pointCount, queryCount);
    }

    @Test
    public void nearest100000Test() {
        List<Point> points = randomPoints(1000000);
        KDTree kd = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        List<Point> queries = randomPoints(100000);

        Stopwatch sw = new Stopwatch();
        for (Point p : queries) {
            kd.nearest(p.getX(), p.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");

        sw = new Stopwatch();
        for (Point p : queries) {
            nn.nearest(p.getX(), p.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
    }

}
