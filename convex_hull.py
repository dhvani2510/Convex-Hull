import subprocess
import csv
import matplotlib.pyplot as plt
import pandas as pd


# Define project paths
source_dir = "C:\\Users\\dhvan\\OneDrive\\Desktop\\CG\\ConvexHullAlgorithms\\src"
output_dir = "C:\\Users\\dhvan\\OneDrive\\Desktop\\CG\\ConvexHullAlgorithms\\out"
package_name = "main"
main_class = "Main"

# Compile the Java project
compile_command = ["javac", "-d", output_dir, "-cp", f"{source_dir};{output_dir}", f"{source_dir}/{package_name}/*.java"]
compile_result = subprocess.run(compile_command, capture_output=True, text=True)

if compile_result.returncode == 0:
    print("Compilation Output:", compile_result.stdout)
    print("Compilation Error:", compile_result.stderr)

    # Run the compiled Java project
    run_command = ["java", "-cp", f"{output_dir};{source_dir}", f"{package_name}.{main_class}"]

    run_result = subprocess.run(run_command, capture_output=True, text=True)

    print("Return Code:", run_result.returncode)
    print("Standard Output:", run_result.stdout)
    print("Standard Error:", run_result.stderr)
    
    original_points = pd.read_csv('points.csv')

    # Read convex hull points from CSV
    convex_hull_points_GS = pd.read_csv('convex_hull-points_graham_scan.csv')

    convex_hull_points_JM = pd.read_csv('convex_hull-points_jarvis_march.csv')

    # Plot original points
    plt.scatter(original_points['x'], original_points['y'], label='Original Points')

    # Plot convex hull points
    plt.scatter(convex_hull_points_GS['x'], convex_hull_points_GS['y'])

    plt.scatter(convex_hull_points_JM['x'], convex_hull_points_JM['y'])


    # Connect convex hull points to visualize the convex hull
    plt.plot(convex_hull_points_GS['x'].tolist() + [convex_hull_points_GS['x'].iloc[0]],
             convex_hull_points_GS['y'].tolist() + [convex_hull_points_GS['y'].iloc[0]],
             linestyle='-', color='red')

    plt.plot(convex_hull_points_JM['x'].tolist() + [convex_hull_points_JM['x'].iloc[0]],
             convex_hull_points_JM['y'].tolist() + [convex_hull_points_JM['y'].iloc[0]],
             linestyle=':', color='green',)

    plt.legend()
    plt.xlabel('X')
    plt.ylabel('Y')
    plt.title('Convex Hull Visualization')
    plt.show()

else:
    print("Compilation Error:", compile_result.stderr)
