package main;

import main.algorithms.GrahamsScan;
import main.algorithms.JarvisMarch;
import main.data_structure.Stack;
import main.helper.CSVHelper;

import java.util.Scanner;

public class Runner {
    public static void mainFunction() {
        int size=1;
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        CSVHelper.generateRandomPoints(size, "points.csv"); // Adjust the number of points as needed
        Point[] points = CSVHelper.readPointsFromCSV("points.csv");
        runGrahamScan(points);
        runJarvisMarch(points);
    }

    private static void runJarvisMarch(Point[] points) {
        long startTime = System.currentTimeMillis();
        Stack<Point> convex_hull = JarvisMarch.convexHull(points);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Input size: " + points.length + ", Elapsed time: " + elapsedTime + " milliseconds");
        CSVHelper.exportToCSV(convex_hull, "convex_hull-points_jarvis_march.csv");
    }


    private static void runGrahamScan(Point[] points) {
        long startTime = System.currentTimeMillis();
        Stack<Point> convex_hull = GrahamsScan.convexHull(points);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Input size: " + points.length + ", Elapsed time: " + elapsedTime + " milliseconds");
        CSVHelper.exportToCSV(convex_hull, "convex_hull-points_graham_scan.csv");
    }
}
