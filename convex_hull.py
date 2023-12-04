import subprocess
import matplotlib.pyplot as plt
import pandas as pd

class JavaProject:
    def __init__(self, source_dir, output_dir, package_name, main_class):
        self.source_dir = source_dir
        self.output_dir = output_dir
        self.package_name = package_name
        self.main_class = main_class

    def compile(self):
        compile_command = [
            "javac", "-d", self.output_dir, "-cp", f"{self.source_dir};{self.output_dir}",
            f"{self.source_dir}/{self.package_name}/*.java"
        ]
        return subprocess.run(compile_command, capture_output=True, text=True)

    def run(self):
        run_command = ["java", "-cp", f"{self.output_dir};{self.source_dir}", f"{self.package_name}.{self.main_class}"]
        return subprocess.run(run_command, capture_output=True, text=True)

def read_csv(file_path):
    return pd.read_csv(file_path)

def plot_convex_hull(ax, original, convex_hull, title, linestyle, color):
    ax.scatter(original['x'], original['y'], label='Original Points')
    ax.scatter(convex_hull['x'], convex_hull['y'], label=f'Convex Hull - {title}')
    ax.plot(
        convex_hull['x'].tolist() + [convex_hull['x'].iloc[0]],
        convex_hull['y'].tolist() + [convex_hull['y'].iloc[0]],
        linestyle=linestyle, color=color
    )
    ax.set_title(f'Convex Hull - {title}')
    ax.legend()

def create_comparison_chart(ax, elapsed_times):
    ax.bar(elapsed_times['Algorithm'], elapsed_times['ElapsedTime'], color=['green', 'red'])
    ax.set_xlabel('Algorithm')
    ax.set_ylabel('Elapsed Time (ms)')
    ax.set_title('Comparison of Elapsed Time')

def main():
    source_dir = "C:\\Users\\dhvan\\OneDrive\\Desktop\\CG\\Convex-Hull\\ConvexHullAlgorithms\\src"
    output_dir = "C:\\Users\\dhvan\\OneDrive\\Desktop\\CG\\Convex-Hull\\ConvexHullAlgorithms\\out"
    package_name = "main"
    main_class = "Main"

    java_project = JavaProject(source_dir, output_dir, package_name, main_class)

    compile_result = java_project.compile()

    if compile_result.returncode != 0:
        print_output("Compilation Error", compile_result.stderr)
    else:
        print_output("Compilation Output", compile_result.stdout)
        print_output("Compilation Error", compile_result.stderr)

        run_result = java_project.run()

        print_output("Return Code", run_result.returncode)
        print_output("Standard Output", run_result.stdout)
        print_output("Standard Error", run_result.stderr)

        original_points = read_csv('points.csv')
        convex_hull_points_GS = read_csv('convex_hull-points_graham_scan.csv')
        convex_hull_points_JM = read_csv('convex_hull-points_jarvis_march.csv')

        fig, axs = plt.subplots(2, 2, figsize=(25, 25), gridspec_kw={'width_ratios': [1, 1]})

        plot_convex_hull(axs[0, 0], original_points, convex_hull_points_GS, 'Graham Scan', '-', 'red')
        plot_convex_hull(axs[0, 1], original_points, convex_hull_points_JM, 'Jarvis March', ':', 'green')

        elapsed_times = read_csv('elapsed_times.csv')
        create_comparison_chart(axs[1, 0], elapsed_times)

        axs[1, 1].remove()
        plt.tight_layout(rect=[0, 0, 1, 0.97])
        plt.suptitle('Convex Hull Visualization')
        plt.show()

def print_output(title, output):
    print(f"{title}:")
    print(output)

if __name__ == "__main__":
    main()
