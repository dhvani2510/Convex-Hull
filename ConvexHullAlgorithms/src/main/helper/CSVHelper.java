package main.helper;

import main.Point;
import main.data_structure.Stack;

import java.io.*;
import java.util.Random;

public class CSVHelper {
    public static void generateRandomPoints(int numPoints, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            Random random = new Random();
            writer.println("x,y"); // CSV header

            for (int i = 0; i < numPoints; i++) {
                int x = random.nextInt(50); // Adjust the range as needed
                int y = random.nextInt(50);
                writer.println(x + "," + y);
            }
            writer.close();
            System.out.println("Points generated and exported to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportToCSV(Stack<Point> points, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.write("x,y\n");

            // Write points
            while (!points.isEmpty()) {
                Point p = points.pop();
                writer.write(p.x + "," + p.y + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Point[] readPointsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            // Read points from CSV
            return br.lines()
                    .map(line -> {
                        String[] values = line.split(",");
                        int x = Integer.parseInt(values[0]);
                        int y = Integer.parseInt(values[1]);
                        return new Point(x, y);
                    })
                    .toArray(Point[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new Point[0];
        }
    }
}
