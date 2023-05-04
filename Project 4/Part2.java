import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
	        if (args.length != 2) {
	            System.out.println("Usage: java Main input_file output_file");
	            return;
	        }
	        String inputFile = args[0];
	        String outputFile = args[1];
	        try {
	            Scanner scanner = new Scanner(new File("input.txt"));
	            PrintWriter writer = new PrintWriter(outputFile);
	            // Process first sub-part
	            while (scanner.hasNextLine()) {
	                String[] line = scanner.nextLine().split(" ");
	                double[] plane = new double[6];
	                double[] point = new double[3];
	                for (int i = 0; i < 6; i++) {
	                    plane[i] = Double.parseDouble(line[i]);
	                }
	                for (int i = 0; i < 3; i++) {
	                    point[i] = Double.parseDouble(line[6 + i]);
	                }
	                double distance = distanceToPlane(plane, point);
	                writer.println(distance);
	            }
	            // Process second sub-part
	            if (scanner.hasNextLine()) {
	                String[] line = scanner.nextLine().split(" ");
	                double[] linePoint1 = new double[3];
	                double[] linePoint2 = new double[3];
	                for (int i = 0; i < 3; i++) {
	                    linePoint1[i] = Double.parseDouble(line[i]);
	                    linePoint2[i] = Double.parseDouble(line[i + 3]);
	                }
	                while (scanner.hasNextLine()) {
	                    String[] triangleLine = scanner.nextLine();
	                }
	            }

	// Compute the distance between a point and a plane
	public static double distanceToPlane(double[] plane, double[] point) {
		double dotProduct = 0;
		for (int i = 0; i < 3; i++) {
			dotProduct += plane[i] * (point[i] - plane[i + 3]);
		}
		return Math.abs(dotProduct) / Math.sqrt(plane[0] * plane[0] + plane[1] * plane[1] + plane[2] * plane[2]);
	}

	// Compute the intersection point between a line and a plane
	public static double[] intersectionPoint(double[] linePoint1, double[] linePoint2, double[] plane) {
		double[] direction = new double[3];
		for (int i = 0; i < 3; i++) {
			direction[i] = linePoint2[i] - linePoint1[i];
		}
		double dotProduct = 0;
		for (int i = 0; i < 3; i++) {
			dotProduct += plane[i] * direction[i];
		}
		if (dotProduct == 0) {
			// The line is parallel to the plane
			return null;
		}
		double[] lineToPlane = new double[3];
		for (int i = 0; i < 3; i++) {
			lineToPlane[i] = plane[i + 3] - linePoint1[i];
		}
		double t = 0;
		for (int i = 0; i < 3; i++) {
			t += lineToPlane[i] * plane[i] / dotProduct;
		}
		double[] intersection = new double[3];
		for (int i = 0; i < 3; i++) {
			intersection[i] = linePoint1[i] + t * direction[i];
		}
		return intersection;
	}

}// end part 2
