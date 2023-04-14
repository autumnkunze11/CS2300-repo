
//using the input files to find the area of the triangle and then compute the bisector and find the distance of the third point to the line 
//or plane
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Part3 {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("pa3_test_input.txt"));

		// Read in the coordinates of the three points
		double[][] points = new double[3][];
		for (int i = 0; i < 3; i++) {
			points[i] = new double[scanner.nextInt()];
			for (int j = 0; j < points[i].length; j++) {
				points[i][j] = scanner.nextDouble();
			}
		}
		
		// Compute the area of the triangle
        double area = computeTriangleArea(points);

        // Compute the distance of the third point to the bisector of the first two points
        double distance = computeDistanceToBisector(points[0], points[1], points[2]);

        // Print out the results
        System.out.printf("%.4f\n%.4f", area, distance);
        
        scanner.close();
        
        //creating the file 
    	File fileName = new File("Part3Output.txt");

		// Open file for writing
		PrintWriter outputFile = new PrintWriter(fileName);
		outputFile.print(area);
		outputFile.print(distance);
		
		outputFile.close();

	}//end main
	
	private static double computeTriangleArea(double[][] points) {
        // Compute the area of the triangle using the cross product
        double[] v1 = new double[points[0].length];
        double[] v2 = new double[points[0].length];

        for (int i = 0; i < points[0].length; i++) {
            v1[i] = points[1][i] - points[0][i];
            v2[i] = points[2][i] - points[0][i];
        }

        double area = 0.5 * Math.sqrt(
                v1[1] * v2[2] - v1[2] * v2[1] +
                v1[2] * v2[0] - v1[0] * v2[2] +
                v1[0] * v2[1] - v1[1] * v2[0]);

        return area;
    }

    private static double computeDistanceToBisector(double[] p1, double[] p2, double[] p3) {
        // Compute the bisector of the first two points
        double[] bisector = new double[p1.length];
        double length = 0;
        for (int i = 0; i < p1.length; i++) {
            bisector[i] = p1[i] + (p2[i] - p1[i]) / 2;
            length += (p2[i] - p1[i]) * (p2[i] - p1[i]);
        }
        length = Math.sqrt(length);

        // Normalize the bisector vector
        for (int i = 0; i < bisector.length; i++) {
            bisector[i] = bisector[i] + (p2[i] - p1[i]) / 2 / length;
        }

        // Compute the distance of the third point to the bisector
        double distance = 0;
        for (int i = 0; i < p3.length; i++) {
            distance += (p3[i] - bisector[i]) * (p3[i] - bisector[i]);
        }
        distance = Math.sqrt(distance);

        return distance;
    }

}//end part3
