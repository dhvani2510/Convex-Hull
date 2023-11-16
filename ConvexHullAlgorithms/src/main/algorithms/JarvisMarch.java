package main.algorithms;

import main.Point;
import main.data_structure.Stack;

import java.util.Arrays;

public class JarvisMarch {
    public static Stack<Point> convexHull(Point[] points) {
        int n = points.length;

        if(n<3) {
            //convex hull not possible
            return new Stack<>();
        }

        // Find the point with the lowest y-coordinate (and leftmost if ties)
        Point pivot = findPivot(points);

        System.out.println("The lowest point of all is "+ pivot.x + " " + pivot.y);

        // Build the convex hull using Stack
        Stack<Point> hull = buildConvexHull(points, pivot);
        return hull;
    }

    private static Point findPivot(Point[] points) {
        // Find the point with the lowest y-coordinate (and leftmost if ties)
        Point pivot = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i].y < pivot.y || (points[i].y == pivot.y && points[i].x < pivot.x)) {
                pivot = points[i];
            }
        }
        return pivot;
    }

    private static Stack<Point> buildConvexHull(Point[] points, Point pivot) {
        Stack<Point> hull = new Stack<>();
        hull.push(pivot);
        Arrays.sort(points, pivot.polarOrder());
        do {
            Arrays.sort(points, pivot.polarOrder());
            Point next = points[0];
            int i = 1;
            while (next.equals(pivot) && i < points.length) {
                next = points[i++];
            }

            if (i == points.length && next.equals(pivot)) {
                System.out.println("breaking");
                // All points are the same, or there are fewer than two distinct points
                break;
            }
            System.out.println(next.x + " " + next.y + "\n\n");
            hull.push(next);
            pivot = next;
        } while (!pivot.equals(hull.get(0)));  // Terminate when the next selected point is the starting point
        return hull;
    }

}
