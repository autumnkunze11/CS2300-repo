
//constructing parallel and perspective projections
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("input.txt");

		// Check that input file exists
		if (!inputFile.exists()) {
			System.out.println("Input file does not exist");
			return;
		}

		Scanner inputScanner = new Scanner(inputFile);

		// Read plane and projection direction parameters
		double[][] planeParams = new double[2][3];
		double[] projectionDir = new double[3];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				planeParams[i][j] = inputScanner.nextDouble();
			}
		}
		for (int i = 0; i < 3; i++) {
			projectionDir[i] = inputScanner.nextDouble();
		}
		// Perform parallel projection
		PrintWriter parallelOutput = new PrintWriter("parallel_projection.txt");
		while (inputScanner.hasNextLine()) {
			double[] point = new double[3];
			for (int i = 0; i < 3; i++) {
				point[i] = inputScanner.nextDouble();
			}
			double[] projection = parallelProjection(point, planeParams, projectionDir);
			parallelOutput.printf("%.2f %.2f %.2f\n", projection[0], projection[1], projection[2]);
		}
		parallelOutput.close();
        inputScanner.close();
        
		// Reset scanner to read input file again
		inputScanner = new Scanner(inputFile);

		// Skip plane and projection direction parameters
		for (int i = 0; i < 2; i++) {
			inputScanner.nextLine();
		}
		for (int i = 0; i < 3; i++) {
			inputScanner.nextDouble();
		}

		// Perform perspective projection
		PrintWriter perspectiveOutput = new PrintWriter("perspective_projection.txt");
		while (inputScanner.hasNextLine()) {
			double[] point = new double[3];
			for (int i = 0; i < 3; i++) {
				point[i] = inputScanner.nextDouble();
			}
			double[] projection = perspectiveProjection(point, planeParams);
			perspectiveOutput.printf("%.2f %.2f %.2f\n", projection[0], projection[1], projection[2]);
		}
		perspectiveOutput.close();

		inputScanner.close();

	}// end main

	public static double[] parallelProjection(double[] point, double[][] planeParams, double[] projectionDir) {
		double[] projection = new double[3];

		double numerator = dotProduct(subtractVectors(point, planeParams[0]), planeParams[1]);
		double denominator = dotProduct(projectionDir, planeParams[1]);
		double distance = numerator / denominator;

		double[] projectionVector = multiplyVector(projectionDir, distance);
		projection = addVectors(point, projectionVector);

		return projection;
	}// end parallelProjection

	public static double[] perspectiveProjection(double[] point, double[][] planeParams) {
		double[] projection = new double[3];

		double[] projectionDir = multiplyVector(point, -1.0);
		projectionDir = normalizeVector(projectionDir);
		
		return projectionDir;
	}

	// returns the dot product of two vectors
	public static double dotProduct(double[] vector1, double[] vector2) {
		double result = 0.0;
		for (int i = 0; i < vector1.length; i++) {
			result += vector1[i] * vector2[i];
		}
		return result;
	}

	public static double[] multiplyVector(double[] vector, double scalar) {
		double[] result = new double[vector.length];

		for (int i = 0; i < vector.length; i++) {
			result[i] = vector[i] * scalar;
		}

		return result;
	}// end multiply vector

	public static double[] subtractVectors(double[] v1, double[] v2) {
		if (v1.length != v2.length) {
			throw new IllegalArgumentException("Vectors must be of the same size");
		}

		double[] result = new double[v1.length];
		for (int i = 0; i < v1.length; i++) {
			result[i] = v1[i] - v2[i];
		}

		return result;
	}// end subtract vectors

	public static double[] addVectors(double[] vector1, double[] vector2) {
		// Add the vectors
		double[] result = new double[vector1.length];
		for (int i = 0; i < vector1.length; i++) {
			result[i] = vector1[i] + vector2[i];
		}
		return result;
	}//end add vectors
	
	public static double[] normalizeVector(double[] vector) {
        double length = 0.0;
        double[] normalized = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            length += vector[i] * vector[i];
        }
        length = Math.sqrt(length);
        for (int i = 0; i < vector.length; i++) {
            normalized[i] = vector[i] / length;
        }
        return normalized;
    }//end normalize vector 
	
}// end part 1
