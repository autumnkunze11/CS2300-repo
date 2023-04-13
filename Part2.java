//Finding the inverse of a matrix 
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
		//Creating a constant for the size of the matrix 
		final int MATRIX_SIZE = 2; 
		
		//Creating the scanner for the using to input values into
		Scanner input = new Scanner(System.in);
		
		//Ask using for the values 
		System.out.println("Enter the element values for the matrix: ");
		
		//Creating the matrix where the values are getting stored
		double matrix [][] = new double[MATRIX_SIZE][MATRIX_SIZE];
		
		//Placing the values into the matrix
		for(int i = 0; i < MATRIX_SIZE; i++) {
			for(int j = 0; j < MATRIX_SIZE; j++) {
				matrix[i][j] = input.nextDouble();
			}
		}
		
		double d[][] = invert(matrix);
		
		System.out.println("The inverse is: ");
		for(int i = 0; i < MATRIX_SIZE; i++) {
			for(int j = 0; j < MATRIX_SIZE; j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println("");
		}
		
		//Close the scanner 
		input.close();

	}//end main
	
	public static double[][] invert (double matrix[][]){
		 int length = matrix.length; 
		 double x[][] = new double[length][length];
		 double y[][] = new double [length][length];
		 int index[] = new int[length];
		
		 for(int i = 0; i < length; i++) {
			 y[i][i] = 1; 
		 }
		 
		 //transform matrix into upper triangle
		 gaussian(matrix, index);
		 
		 //update the matrix with the ratios stored 
		 for(int i = 0; i < length - 1 ; i++) {
			 for(int j = i + 1; j < length; j++) {
				 for(int k = 0; k < length; k++) {
					 y[index[j]][k] -= matrix[index[j]][i] * y[index[i]][k];
				 }
			 }
		 }
		 
		 //backward substitutions 
		 for(int i = 0; i < length; i++) {
			 x[length-1][i] = y[index[length-1]][i]/ matrix[index[length-1]][length-1];
			 for(int j = length - 2 ;j >= 0; j--) {
				 x[j][i] = y[index[j]][i];
				 for(int k = j + 1; k < length; k++) {
					x[j][i] -= matrix[index[j]][k] * x[k][i];
				 }
				 x[j][i] /= matrix[index[j]][j];
			 }
			 
		 }
		 return x; 
	}//end invert 
	
	public static void gaussian(double matrix[][], int index[]) {
		int length = index.length; 
		double c[] = new double[length];
		
		//Initialize the index 
		for(int i = 0; i < length; i++) {
			index[i] = i; 
		}
		
		//Find the rescaling factors 
		for(int i = 0; i < length; i++) {
			double c1 = 0; 
			
			for(int j = 0; j < length; j++) {
				double c0 = Math.abs(matrix[i][j]);
				if(c0 > c1) {
					c1 = c0; 
				}
			}
			c[i] = c1;
		}
		
		//Search the pivoting element from each column
		int k = 0; 
		for(int j = 0; j < length -1; j++) {
			double pi1 = 0; 
			for(int i = j; i < length; i++) {
				double pi0 = Math.abs(matrix[index[i]][j]);
				pi0 /= c[index[i]];
				if(pi0 > pi1) {
					pi1 = pi0; 
					k = i; 
				}
			}
			//interchanging the rows according to pivot order 
			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for(int i = j+1; i < length; i++) {
				double pj = matrix[index[i]][j] / matrix[index[j]][j];
				
				matrix[index[i]][j] = pj; 
				
				for(int l = j+1; l < length; l++) {
					matrix[index[i]][l] -= pj*matrix[index[j]][l];
				}
			}
		}
	}//end guassian 

}//end part2 
