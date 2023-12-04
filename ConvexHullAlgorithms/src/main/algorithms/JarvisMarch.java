package main.algorithms;

import main.data_structure.Point;
import main.data_structure.Stack;
import java.util.Arrays;

public class JarvisMarch {
    public static Stack<Point> convexHull(Point[] points) {
        int n = points.length;
        if(n<3) {
            return new Stack<>();  // convex hull not possible
        }

        Point pivot = findPivot(points);  // find the lowest point

        // Build the convex hull using Stack
        return buildConvexHull(points, pivot);
    }

    private static Point findPivot(Point[] points) {
        // Find the point with the lowest y-coordinate (and leftmost if ties)
        Point pivot = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < pivot.x || (points[i].x == pivot.x && points[i].y < pivot.y)) {
                pivot = points[i];
            }
        }
        return pivot;
    }

    private static Stack<Point> buildConvexHull(Point[] points, Point pivot) {
        Stack<Point> hull = new Stack<>();
        Arrays.sort(points, pivot.polarOrder());
        Point current = pivot;
        do {
            hull.push(current);
            Point next = points[0];
            for (int i = 1; i < points.length; i++) {
                if (next.equals(current) || Point.orientation(current, next, points[i]) == 1) {
                    next = points[i];
                }
            }
            current = next;
        } while (!current.equals(pivot)); // Terminate when the next selected point is the starting point
        return hull;
    }
}
