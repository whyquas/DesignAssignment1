package algorithms.closest;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Result {
        public final Point p1, p2;
        public final double dist;
        public Result(Point p1, Point p2, double dist) {
            this.p1 = p1;
            this.p2 = p2;
            this.dist = dist;
        }
        @Override
        public String toString() {
            return String.format("Closest pair: (%.2f, %.2f) – (%.2f, %.2f), dist = %.4f",
                    p1.x, p1.y, p2.x, p2.y, dist);
        }
    }

    public static Result find(Point[] points) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] sortedByY = points.clone();
        Arrays.sort(sortedByY, Comparator.comparingDouble(p -> p.y));
        return closest(sortedByX, sortedByY);
    }

    private static Result closest(Point[] ptsX, Point[] ptsY) {
        int n = ptsX.length;
        if (n <= 3) {
            return bruteForce(ptsX);
        }

        int mid = n / 2;
        Point midPoint = ptsX[mid];

        Point[] leftX = Arrays.copyOfRange(ptsX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(ptsX, mid, n);

        Point[] leftY = Arrays.stream(ptsY).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] rightY = Arrays.stream(ptsY).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        Result leftRes = closest(leftX, leftY);
        Result rightRes = closest(rightX, rightY);

        Result best = leftRes.dist < rightRes.dist ? leftRes : rightRes;
        double d = best.dist;

        double finalD = d;
        Point[] strip = Arrays.stream(ptsY).filter(p -> Math.abs(p.x - midPoint.x) < finalD).toArray(Point[]::new);

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < d; j++) {
                double dist = dist(strip[i], strip[j]);
                if (dist < best.dist) {
                    best = new Result(strip[i], strip[j], dist);
                    d = dist;
                }
            }
        }
        return best;
    }

    private static Result bruteForce(Point[] pts) {
        double minDist = Double.POSITIVE_INFINITY;
        Point p1 = null, p2 = null;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double dist = dist(pts[i], pts[j]);
                if (dist < minDist) {
                    minDist = dist;
                    p1 = pts[i];
                    p2 = pts[j];
                }
            }
        }
        return new Result(p1, p2, minDist);
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
