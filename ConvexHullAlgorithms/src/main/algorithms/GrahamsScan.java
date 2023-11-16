package main.algorithms;

import main.Point;
import main.data_structure.Stack;
import java.util.Arrays;
import java.util.List;

public class GrahamsScan {
    // Point class remains the same

    // Function to find the convex hull using Graham's Scan
    public static Stack<Point> convexHull(Point[] points) {
        int n = points.length;

        if(n<3) {
            //convex hull not possible
            return new Stack<>();
        }

        // Find the point with the lowest y-coordinate (and leftmost if ties)
        Point pivot = findPivot(points);

        System.out.println("The lowest point of all is "+ pivot.x + " " + pivot.y);

        // Sort the points based on polar angle with the pivot
        Arrays.sort(points, pivot.polarOrder());
        System.out.println("Points after sorting order\n");
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i].x + " " + points[i].y + "\n");
        }


            // Build the convex hull using Stack
        Stack<Point> hull = buildConvexHull(points, pivot);
        return hull;
    }

    private static Stack<Point> buildConvexHull(Point[] points, Point pivot) {
        Stack<Point> hull = new Stack<>();
        hull.push(points[0]);
        hull.push(points[1]);

        for (int i = 2; i < points.length; i++) {
            while (hull.size() > 1 && orientation(hull.peekNextToTop(), hull.peek(), points[i]) <= 0) {
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

    private static int orientation(Point p, Point q, Point r) {
        System.out.println("For the points \n");
        System.out.println(p.x + " " + p.y);
        System.out.println(q.x + " " + q.y);
        System.out.println(r.x + " " + r.y);
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        System.out.println(val + "\n");
        if (val == 0) return 0; // Collinear
        return (val > 0) ? -1 : 1; // Clockwise or counterclockwise
    }
}
