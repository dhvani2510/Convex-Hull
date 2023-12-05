package main;

import main.algorithms.ChansAlgorithm;
import main.algorithms.GrahamsScan;
import main.algorithms.JarvisMarch;
import main.algorithms.QuickHull;
import main.data_structure.Point;
import main.data_structure.Stack;
import main.helper.CSVHelper;

import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void mainFunction() {
        int size=1;
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        CSVHelper.generateRandomPoints(size, "points.csv"); // Adjust the number of points as needed
        Point[] points = CSVHelper.readPointsFromCSV("points.csv");
        CSVHelper.createElapsedTimeFile();
        runGrahamScan(points);
        runJarvisMarch(points);
        runQuickHull(points);
//        runChansAlgorithm(points);
    }

    private static void runChansAlgorithm(Point[] points) {
        long startTime = System.nanoTime();
        List<Point> convex_hull = ChansAlgorithm.convexHull(points);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        CSVHelper.exportElapsedTimeToCSV("elapsed_times.csv", "Graham Scan", points.length, elapsedTime);
        CSVHelper.exportToCSV(convex_hull, "convex_hull-points_chans.csv");
    }

    private static void runJarvisMarch(Point[] points) {
        long startTime = System.nanoTime();
        Stack<Point> convex_hull = JarvisMarch.convexHull(points);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        CSVHelper.exportElapsedTimeToCSV("elapsed_times.csv", "Jarvis March", points.length, elapsedTime);
        CSVHelper.exportToCSV(convex_hull.toArray(), "convex_hull-points_jarvis_march.csv");
    }


    private static void runGrahamScan(Point[] points) {
        long startTime = System.nanoTime();
        Stack<Point> convex_hull = GrahamsScan.convexHull(points);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        CSVHelper.exportElapsedTimeToCSV("elapsed_times.csv", "Graham Scan", points.length, elapsedTime);
        CSVHelper.exportToCSV(convex_hull.toArray(), "convex_hull-points_graham_scan.csv");
    }

    private static void runQuickHull(Point[] points) {
        long startTime = System.nanoTime();
        List<Point> convex_hull = QuickHull.convexHull(points);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        CSVHelper.exportElapsedTimeToCSV("elapsed_times.csv", "QuickHull", points.length, elapsedTime);
        CSVHelper.exportToCSV(convex_hull, "convex_hull-points_quick_hull.csv");
    }
}
