package main.algorithms;

import main.Point;
import main.data_structure.Stack;
import java.util.Arrays;

public class GrahamsScan {
    // Function to find the convex hull using Graham's Scan
    public static Stack<Point> convexHull(Point[] points) {
        int n = points.length;
        if(n<3) {
            return new Stack<>();  //convex hull not possible
        }
        Point pivot = findPivot(points);  // find the lowest point

        // Sort the points based on polar angle with the pivot
        Arrays.sort(points, pivot.polarOrder());
        // Build the convex hull using Stack
        return buildConvexHull(points, pivot);
    }

    private static Stack<Point> buildConvexHull(Point[] points, Point pivot) {
        Stack<Point> hull = new Stack<>();
        hull.push(points[0]);
        hull.push(points[1]);

        for (int i = 2; i < points.length; i++) {
            while (hull.size() > 1 && Point.orientation(hull.peekNextToTop(), hull.peek(), points[i]) <= 0) {
                hull.pop();
            }
            hull.push(points[i]);
        }
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
}
