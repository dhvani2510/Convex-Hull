package main.algorithms;

import main.data_structure.Point;
import main.data_structure.Stack;

import java.util.ArrayList;
import java.util.List;

public class QuickHull {
    public static List<Point> convexHull(Point[] points) {
        int n = points.length;
        if(n<3) {
            return new ArrayList<>();  //convex hull not possible
        }
        Point leftmost = points[0];
        Point rightmost = points[0];

        // Find the leftmost and rightmost points
        for (Point point : points) {
            if (point.x < leftmost.x) {
                leftmost = point;
            }
            if (point.x > rightmost.x) {
                rightmost = point;
            }
        }

        // Build the convex hull using Stack
        return buildConvexHull(points, leftmost, rightmost);
    }

    private static List<Point> buildConvexHull(Point[] points, Point leftmost, Point rightmost) {
        List<Point> convexHull = new ArrayList<>();
        convexHull.add(leftmost);
        convexHull.add(rightmost);

        // Find points on the left and right of the line formed by leftmost and rightmost points
        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();

        for (Point point : points) {
            if (point != leftmost && point != rightmost) {
                double position = isLeft(leftmost, rightmost, point);
                if (position > 0) {
                    leftSet.add(point);
                } else if (position < 0) {
                    rightSet.add(point);
                }
            }
        }
        hullSet(leftmost, rightmost, leftSet, convexHull);
        hullSet(rightmost, leftmost, rightSet, convexHull);
        return convexHull;
    }

    private static void hullSet(Point p1, Point p2, List<Point> set, List<Point> hull) {
        int insertPosition = hull.indexOf(p2);
        if (set.isEmpty()) {
            return;
        }
        if (set.size() == 1) {
            Point point = set.get(0);
            set.remove(point);
            hull.add(insertPosition, point);
            return;
        }

        double dist = Double.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point point = set.get(i);
            double currentDist = distance(p1, p2, point);
            if (currentDist > dist) {
                dist = currentDist;
                furthestPoint = i;
            }
        }

        Point furthest = set.remove(furthestPoint);
        hull.add(insertPosition, furthest);
        // Recur for the two sets of points
        List<Point> leftSet1 = new ArrayList<>();
        List<Point> leftSet2 = new ArrayList<>();
        for (Point point : set) {
            double position1 = isLeft(p1, furthest, point);
            double position2 = isLeft(furthest, p2, point);

            if (position1 > 0) {
                leftSet1.add(point);
            } else if (position2 > 0) {
                leftSet2.add(point);
            }
        }

        hullSet(p1, furthest, leftSet1, hull);
        hullSet(furthest, p2, leftSet2, hull);
    }

    private static double distance(Point p1, Point p2, Point p) {
        return (p.y - p1.y) * (p2.x - p1.x) - (p.x - p1.x) * (p2.y - p1.y);
    }

    private static double isLeft(Point p1, Point p2, Point p) {
        return (p2.x - p1.x) * (p.y - p1.y) - (p.x - p1.x) * (p2.y - p1.y);
    }
}
