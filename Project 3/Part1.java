
//solving for x in Ax=b using Guassian elimination
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Part1 {

	public static void main(String[] args) throws FileNotFoundException {
		// opens file for reading
		File mat1File = new File("pa3_test_input_1.txt");

		// Creating the scanner for the using to input values into
		Scanner inputMat1 = new Scanner(mat1File);

		double value11 = mat1File.nextDouble();
		double value12 = mat1File.nextDouble();
		double value13 = mat1File.nextDouble();
		double value21 = mat1File.nextDouble();
		double value22 = mat1File.nextDouble();
		double value23 = mat1File.nextDouble();

		double[][] A = new double[2][2];

		double[] b = new double[2];

		// solving using gaussian elimination
		for (int k = 0; k < 2 - 1; k++) {
			for (int i = k + 1; i < 2; i++) {
				double factor = A[i][k] / A[k][k];
				for (int j = k; j < 3; j++) {
					A[i][j] -= factor * A[k][j];
				}
				b[i] -= factor * b[k];
			}
		} // end for

		// Check if the system is inconsistent or underdetermined
		if (A[1][2] == 0 && b[1] != 0) {
			System.out.println("System inconsistent");

		} else if (A[0][1] == 0 && A[0][2] == 0 && b[0] != 0) {
			System.out.println("System inconsistent");

		} else if (A[0][1] == 0 && A[0][2] == 0 && b[0] == 0 && A[1][1] == 0 && A[1][2] == 0 && b[1] == 0) {
			System.out.println("System underdetermined");

		} else {
			// Back-substitute to find x
			double[] x = new double[2];
			x[2] = b[1] / A[1][2];
			x[1] = (b[0] - A[0][2] * x[2]) / A[0][1];

			// Print the solution
			System.out.println("The solution is:");
			System.out.printf("x1 = ", "%,.4f", x[0]);
			System.out.printf("x2 = ", "%.4f", x[1]);
			
			File fileName = new File("Part1Output.txt");

			// Open file for writing
			PrintWriter outputFile = new PrintWriter(fileName);

			// Writing to the file
			for (int j = 0; j < 2; j++) {
				outputFile.print(x[j] + " ");
				outputFile.print("");

			}
			outputFile.close();

		}

		inputMat1.close();

	}

}
