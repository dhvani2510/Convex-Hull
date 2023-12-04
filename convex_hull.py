import subprocess
import matplotlib.pyplot as plt
import pandas as pd

# Define project paths
source_dir = "C:\\Users\\dhvan\\OneDrive\\Desktop\\CG\\Convex-Hull\\ConvexHullAlgorithms\\src"
output_dir = "C:\\Users\\dhvan\\OneDrive\\Desktop\\CG\\Convex-Hull\\ConvexHullAlgorithms\\out"
package_name = "main"
main_class = "Main"

# Compile the Java project
compile_command = [
    "javac", "-d", output_dir, "-cp", f"{source_dir};{output_dir}", f"{source_dir}/{package_name}/*.java"
]
compile_result = subprocess.run(compile_command, capture_output=True, text=True)


def print_output(title, output):
    print(f"{title}:")
    print(output)


if compile_result.returncode == 0:
    print_output("Compilation Output", compile_result.stdout)
    print_output("Compilation Error", compile_result.stderr)

    # Run the compiled Java project
    run_command = ["java", "-cp", f"{output_dir};{source_dir}", f"{package_name}.{main_class}"]
    run_result = subprocess.run(run_command, capture_output=True, text=True)

    print_output("Return Code", run_result.returncode)
    print_output("Standard Output", run_result.stdout)
    print_output("Standard Error", run_result.stderr)

    # Read CSV files
    original_points = pd.read_csv('points.csv')
    convex_hull_points_GS = pd.read_csv('convex_hull-points_graham_scan.csv')
    convex_hull_points_JM = pd.read_csv('convex_hull-points_jarvis_march.csv')

    # Create subplots
    fig, axs = plt.subplots(2, 2, figsize=(12, 12), gridspec_kw={'width_ratios': [1, 1]})

    # Plot points in the first subplot
    axs[0,0].scatter(original_points['x'], original_points['y'], label='Original Points')
    axs[0,0].scatter(convex_hull_points_GS['x'], convex_hull_points_GS['y'], label='Convex Hull - Graham Scan')
    axs[0,0].plot(
        convex_hull_points_GS['x'].tolist() + [convex_hull_points_GS['x'].iloc[0]],
        convex_hull_points_GS['y'].tolist() + [convex_hull_points_GS['y'].iloc[0]],
        linestyle='-', color='red'
    )
    axs[0,0].set_title('Convex Hull - Graham Scan')
    axs[0,0].legend()

    # Plot points in the second subplot
    axs[0,1].scatter(original_points['x'], original_points['y'], label='Original Points')
    axs[0,1].scatter(convex_hull_points_JM['x'], convex_hull_points_JM['y'], label='Convex Hull - Jarvis March')
    axs[0,1].plot(
        convex_hull_points_JM['x'].tolist() + [convex_hull_points_JM['x'].iloc[0]],
        convex_hull_points_JM['y'].tolist() + [convex_hull_points_JM['y'].iloc[0]],
        linestyle=':', color='green'
    )
    axs[0,1].set_title('Convex Hull - Jarvis March')
    axs[0,1].legend()

    # Read the CSV file containing all elapsed times
    elapsed_times = pd.read_csv('elapsed_times.csv')

    # Create a bar chart for comparison analysis
    axs[1, 0].bar(elapsed_times['Algorithm'], elapsed_times['ElapsedTime'], color=['green', 'red'])
    axs[1, 0].set_xlabel('Algorithm')
    axs[1, 0].set_ylabel('Elapsed Time (ms)')
    axs[1, 0].set_title('Comparison of Elapsed Time')

    axs[1, 1].remove()
    plt.tight_layout(rect=[0, 0, 1, 0])
    # Customize overall plot layout
    plt.suptitle('Convex Hull Visualization')
    plt.show()

else:
    print_output("Compilation Error", compile_result.stderr)
