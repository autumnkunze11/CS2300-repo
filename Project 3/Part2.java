import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

//Find the diagonal matrix, 2x2 matrix for the eigenvalues, and the solution for all of them multiplied together

public class Part2 {

	public static void main(String[] args) {
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
		
		double[][] R = new double[2][2];
		R[0][0] = value11;
		R[0][1] = value21;
		R[0][1] = value21;
		R[1][1] = value22;

		// Compute the eigenvalues
		double lambda1 = (value11 + value22 + Math.sqrt((value11 - value22) * (value11 - value22 ) + 4 * value12 * value21)) / 2;
		double lambda2 = (value11+ value22 - Math.sqrt((value11 - value22) * (value11 - value22) + 4 * value12 * value21)) / 2;

		// Check if eigenvalues are real
        if (Double.isNaN(lambda1) || Double.isNaN(lambda2)) {
            System.out.println("No real eigenvalues");
        }
        
		// Create the diagonal matrix
		double[][] D = new double[2][2];
		D[0][0] = lambda1;
		D[1][1] = lambda2;

		// Print out the diagonal matrix
		System.out.println("The diagonal matrix with the eigenvalues is:");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.printf("%.4f", D[i][j] , " ");
			}
			System.out.println();
		}

		//Computing the eigenvectors 
		double[] v1 = new double[2];
		double[] v2 = new double[2];

		if (R[0][1] != 0) {
			v1[0] = lambda1 - R[1][1];
			v1[1] = R[0][1];
			v2[0] = lambda2 - R[1][1];
			v2[1] = R[0][1];
		} else {
			v1[0] = R[0][1];
			v1[1] = lambda1 - R[0][0];
			v2[0] = R[0][1];
			v2[1] = lambda2 - R[0][0];
		}

		double norm1 = Math.sqrt(v1[0] * v1[0] + v1[1] * v1[1]);
		double norm2 = Math.sqrt(v2[0] * v2[0] + v2[1] * v2[1]);

		v1[0] /= norm1;
		v1[1] /= norm1;
		v2[0] /= norm2;
		v2[1] /= norm2;

		double[][] V = new double[2][2];
		V[0][0] = v1[0];
		V[1][0] = v1[1];
		V[0][1] = v2[0];
		V[1][1] = v2[1];

		// Print out the eigenvector matrix
		System.out.println("The matrix with the eigenvectors is:");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.printf("%,.4f", V[i][j], " ");
			}
			System.out.println();
		}
		
		// Compute the product
        double[][] C = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                C[i][j] = 0;
                for (int k = 0; k < 2; k++) {
                    C[i][j] += R[i][k] * V[k][j];
                }
            }
        }
        
        // Compute the transpose
        double[][] transpose = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                transpose[i][j] = R[j][i];
            }
        }
        
        //multiplying them all together 
        double [][] finalMatrix = new double [2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                finalMatrix[i][j] = 0;
                for (int k = 0; k < 2; k++) {
                    finalMatrix[i][j] += C[i][k] * transpose[k][j];
                }
            }
        }
        
        //Printing out the values 
        System.out.println("The matrix composition is:");
        for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.printf("%,.4f", finalMatrix[i][j], " ");
			}
			System.out.println();
		}
        
        //creating the file 
    	File fileName = new File("Part2Output.txt");

		// Open file for writing
		PrintWriter outputFile = new PrintWriter(fileName);
		//Writing the R matrix
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				outputFile.print(D[i][j]);
				outputFile.print("");
			}
			outputFile.print("");
			
		}
		//Writing the 2x2  
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				outputFile.print(V[i][j]);
				outputFile.print("");
			}
			outputFile.print("");
		}
		
		//Writing R^R^T
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				outputFile.print(finalMatrix[i][j]);
			}
			outputFile.print("");
		}
		

        inputMat1.close();
        outputFile.close();
	}//end main

}//end part2
