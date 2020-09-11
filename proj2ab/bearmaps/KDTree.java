package bearmaps;

import java.util.List;

import static bearmaps.Point.distance;

public class KDTree implements PointSet {

    private static final boolean isHorizontal = false;
    private Node root;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(root, p, isHorizontal);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearest(root, goal, root.point);
    }

    private Point nearest(Node n, Point goal, Point best) {
        if (n == null) {
            return best;
        }
        if (distance(n.point, goal) < distance(best, goal)) {
            best = n.point;
        }

        Node goodSide, badSide;
        if (n.compareTo(goal) > 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }

        Point bestBadSidePoint;
        if (n.orientation) {
            bestBadSidePoint = new Point(goal.getX(), n.point.getY());
        } else {
            bestBadSidePoint = new Point(n.point.getX(), goal.getY());
        }

        best = nearest(goodSide, goal, best);
        if (distance(bestBadSidePoint, goal) < distance(best, goal)) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }

    private Node add(Node n, Point point, boolean orientation) {
        if (n == null) {
            return new Node(point, orientation);
        }
        if (point.equals(n.point)) {
            return n;
        }
        orientation = !orientation;
        if (n.compareTo(point) > 0) {
            n.left = add(n.left, point, orientation);
        } else {
            n.right = add(n.right, point, orientation);
        }
        return n;
    }

    private class Node implements Comparable<Point> {
        private final Point point;
        private Node right;
        private Node left;
        private final boolean orientation;

        Node(Point p, boolean horizontal) {
            point = p;
            this.orientation = horizontal;
        }

        @Override
        public int compareTo(Point p) {
            if (orientation) {
                return Double.compare(point.getY(), p.getY());
            } else {
                return Double.compare(point.getX(), p.getX());
            }
        }
    }

}