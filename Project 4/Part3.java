
//building the google page rank matix with the power method
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Part3 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Please provide input file name");
			return;
		}
		String inputFile = args[0];
		double[][] matrix = readMatrix(inputFile);
		if (!isStochastic(matrix)) {
			System.out.println("Invalid input");
			return;
		}
		int n = matrix.length;
		double[] x = new double[n];
		Arrays.fill(x, 1.0 / n);
		double[] y = new double[n];
		double tolerance = 0.00001;
		double error = tolerance + 1.0;
		int maxIterations = 1000;
		int iterations = 0;
		while (error > tolerance && iterations < maxIterations) {
			multiply(matrix, x, y);
			double norm = 0;
			for (int i = 0; i < n; i++) {
				norm += y[i] * y[i];
			}
			norm = Math.sqrt(norm);
			for (int i = 0; i < n; i++) {
				y[i] /= norm;
			}
			error = 0;
			for (int i = 0; i < n; i++) {
				error += Math.abs(y[i] - x[i]);
			}
			iterations++;
			double[] temp = x;
			x = y;
			y = temp;
		}
		double[] rank = new double[n];
		for (int i = 0; i < n; i++) {
			rank[i] = x[i];
		}
		Arrays.sort(rank);
		int[] indices = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (x[j] == rank[n - i - 1]) {
					indices[i] = j;
					break;
				}
			}
		}
		System.out.println("Eigenvector:");
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("%.4f", x[i]));
		}
		System.out.println("Webpage indices sorted by rank:");
		for (int i = 0; i < n; i++) {
			System.out.print(indices[i] + " ");
		}
	}

	private static double[][] readMatrix(String filename) {
		double[][] matrix = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = br.readLine();
			int n = Integer.parseInt(line);
			matrix = new double[n][n];
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				String[] parts = line.split(" ");
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Double.parseDouble(parts[j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matrix;
	}

	private static boolean isStochastic(double[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] < 0) {
					return false;
				}
				sum += matrix[i][j];
			}
			if (Math.abs(sum - 1.0) > 1e-6) {
				return false;
			}
		}
		// Check that all entries are non-negative
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] < 0.0) {
					return false;
				}
			}
		}

		return true;
	}// end isStochastic

	public static double[] multiply(double[][] matrix, double[] vector1, double[] vector2) {
		double[] result = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			int sum = 0;
			for (int j = 0; j < matrix[0].length; j++) {
				sum += matrix[i][j] * vector1[j] * vector2[i];
			}
			result[i] = sum;
		}
		return result;
	}
}// end part 3
