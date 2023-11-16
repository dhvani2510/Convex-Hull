package main;

import java.util.Comparator;

public class Point {
    public int x, y;

    // Constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Comparator to compare points based on polar order
    public Comparator<Point> polarOrder() {
        return new PolarOrderComparator(this);
    }

    // Static inner class for polar order
    private static class PolarOrderComparator implements Comparator<Point> {
        // Singleton instance
        private final Point INSTANCE;
        private int x;
        private int y;

        private PolarOrderComparator(Point instance) {
            this.INSTANCE = instance;
        }

        @Override
        public int compare(Point p1, Point p2) {
            double angle1 = Math.atan2(p1.y - INSTANCE.y, p1.x - INSTANCE.x);
            double angle2 = Math.atan2(p2.y - INSTANCE.y, p2.x - INSTANCE.x);
            if (angle1 < angle2) {
                return -1;
            } else if (angle1 > angle2) {
                return 1;
            } else {
                // If angles are equal, compare distances to the pivot
                double dist1 = Math.sqrt(Math.pow(p1.x - INSTANCE.x, 2) + Math.pow(p1.y - INSTANCE.y, 2));
                double dist2 = Math.sqrt(Math.pow(p2.x - INSTANCE.x, 2) + Math.pow(p2.y - INSTANCE.y, 2));

                return Double.compare(dist2, dist1); // Corrected comparison for distances
            }
        }
    }
}
