//Creating a linear map used to map the source triangle to the target triangle 
import java.util.Scanner;

public class Part3 {

	public static void main(String[] args) {
		//Creating the scanner for the using to input values into
		Scanner input = new Scanner(System.in);
		
		//Values for the source triangle 
		System.out.println("Enter the vertices for the source triangle: ");
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double x2 = input.nextDouble();
		double y2 = input.nextDouble();
		double x3 = input.nextDouble();
		double y3 = input.nextDouble();
		
		//Values for the target triangle 
		System.out.println("Enter the vertices for the target triangle: ");
		double u1 = input.nextDouble();
		double v1 = input.nextDouble();
		double u2 = input.nextDouble();
		double v2 = input.nextDouble();
		double u3 = input.nextDouble();
		double v3 = input.nextDouble();
		
		double[][] matrixA = {{x1,y1,1}, {x2,y2,1}, {x3,y3,1}};
		double[][] matrixB = {{u1,v1,1}, {u2,v2,1}, {u3,v3,1}};
		double[][] inverseA = invert(matrixA);
		double[][] linearMap = multiply(matrixB, inverseA);
		
		System.out.println("The linear map that maps the source triangle to the target triangle is: ");
		System.out.println(linearMap[0][0] + " " + linearMap[0][1] + " " + linearMap[0][2]);
		System.out.println(linearMap[1][0] + " " + linearMap[1][1] + " " + linearMap[1][2]);
		System.out.println(linearMap[2][0] + " " + linearMap[2][1] + " " + linearMap[2][2]);
		
		
		input.close();

	}//end main 
	
	public static double[][] invert(double[][] matrix){
		
		double a = matrix[0][0]; 
		double b = matrix[0][1];
		double c = matrix[0][2];
		double d = matrix[1][0];
		double e = matrix[1][1];
		double f = matrix[1][2];
		double g = matrix[2][0];
		double h = matrix[2][1];
		double i = matrix[2][2];
		
		double determinant = a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
		
		double[][] inverse = new double [3][3];
		inverse[0][0] = (e * i - f * h) / determinant; 
		inverse[0][1] = (c * h - b * i) / determinant; 
		inverse[0][2] = (b * f - c * e) / determinant;
		inverse[1][0] = (f * g - d * i) / determinant; 
		inverse[1][1] = (a * i - c * g) / determinant; 
		inverse[1][2] = (c * d - a * f) / determinant; 
		inverse[2][0] = (d * h - e * g) / determinant; 
		inverse[2][1] = (b * g - a * h) / determinant; 
		inverse[2][2] = (a * e - b * d) / determinant; 
		
		return inverse; 
	}// end invert 
	
	public static double[][] multiply(double[][] matrix1, double[][] matrix2){
		double product[][] = new double[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; i < 3; j++) {
				double sum = 0; 
				for(int k = 0; k <3; k++) {
					sum += matrix1[i][k] * matrix2[k][j];
				}
				product[i][j] = sum; 
			}
		}
		return product;
	}//end multiply

}
