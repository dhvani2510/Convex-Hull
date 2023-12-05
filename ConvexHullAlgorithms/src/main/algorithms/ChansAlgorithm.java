package main.algorithms;

import main.data_structure.Point;
import main.data_structure.Stack;

import java.util.*;

public class ChansAlgorithm {

    public static List<Point> convexHull(Point[] points) {
        int n = points.length;
        if (n <= 5) {
            // Use Graham's Scan for small point sets
            return GrahamsScan.convexHull(points).toArray();
        }

       return  buildConvexHull(points);


    }

    private static List<Point> buildConvexHull(Point[] p) {
        List<Point> points = List.of(p);
        // Step 1: Preprocess the points into subsets
        List<List<Point>> subsets = new ArrayList<>();
        for (int i = 0; i < points.size(); i += 5) {
            int end = Math.min(i + 5, points.size());
            List<Point> subset = new ArrayList<>(points.subList(i, end));
            Arrays.sort(new List[]{subset});
            subsets.add(subset);
        }
        // Step 2: Apply Graham's Scan on each subset
        List<Point> subsetHulls = new ArrayList<>();
        for (List<Point> subset : subsets) {
            Point[] pointsArray = subset.toArray(new Point[0]);
            List<Point> hull = GrahamsScan.convexHull(pointsArray).toArray();
            subsetHulls.addAll(hull);
        }

        // Step 3: Apply Graham's Scan on the combined convex hulls
        Point[] pArray = subsetHulls.toArray(new Point[0]);
        List<Point> mergedHull = GrahamsScan.convexHull(pArray).toArray();

        return mergedHull;
    }
}
