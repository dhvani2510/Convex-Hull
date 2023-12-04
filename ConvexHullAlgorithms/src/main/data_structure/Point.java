package main.data_structure;

import java.util.Comparator;

public class Point {
    public double x, y;

    // Constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Comparator to compare points based on polar order
    public Comparator<Point> polarOrder() {
        return new PolarOrderComparator(this);
    }

    public static int orientation(Point p, Point q, Point r) {
        var val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0; // Collinear
        return (val > 0) ? -1 : 1; // Clockwise or counterclockwise
    }

    // Static inner class for polar order
    private static class PolarOrderComparator implements Comparator<Point> {
        // Singleton instance
        private final Point INSTANCE;

        private PolarOrderComparator(Point instance) {
            this.INSTANCE = instance;
        }

        @Override
        public int compare(Point p1, Point p2) {
            double angle1 = Math.atan2(p1.y - INSTANCE.y, p1.x - INSTANCE.x);
            double angle2 = Math.atan2(p2.y - INSTANCE.y, p2.x - INSTANCE.x);
            int angleComparison = Double.compare(angle1, angle2);
            if (angleComparison != 0) {
                return angleComparison;
            }
            // If angles are equal, compare distances to the pivot
            double dist1Squared = Math.pow(p1.x - INSTANCE.x, 2) + Math.pow(p1.y - INSTANCE.y, 2);
            double dist2Squared = Math.pow(p2.x - INSTANCE.x, 2) + Math.pow(p2.y - INSTANCE.y, 2);
            return Double.compare(dist2Squared, dist1Squared); // Corrected comparison for distances
        }
    }
}
