import java.util.Scanner;
//Finding the eigenvalues and eigenvectors of a 2x2 matrix

public class Part4 {

	public static void main(String[] args) {
		//Creating the scanner for the using to input values into
	    Scanner input = new Scanner(System.in);
	    
	    System.out.println("Enter the elements of the 2x2 matrix:");
	    double a = input.nextDouble();
	    double b = input.nextDouble();
	    double c = input.nextDouble();
	    double d = input.nextDouble();
	    
	    //Compute the eigenvalues 
	    double lambda1 = (a+d + Math.sqrt((a-d) * (a-d) + 4 * b * c)) /2;
	    double lambda2 = (a+d - Math.sqrt((a-d) * (a-d) + 4 * b * c)) /2;
	    
	    //Print out the values 
	    System.out.println("The eigenvalues are: " + lambda1 + " and " + lambda2);
	    
	    
	    System.out.println(lambda1 + " is the dominant eigenvalue");
	   
	    
	    
	    //Computing the eigenvectors 
	    double[] v1 = {(lambda1-d) /b, 1};
	    double[] v2 = {(lambda2-d) /b, 1}; 
	    
	    //normalizing the vectors 
	    double norm1 = Math.sqrt(v1[0] * v1[0] + v1[1] * v1[1]);
	    double norm2 = Math.sqrt(v2[0] * v2[0] + v2[1] * v2[1]);
	    
	    v1[0] /= norm1;
	    v1[1] /= norm1;
	    v2[0] /= norm2; 
	    v2[1] /= norm2; 
	    
	    System.out.println("The eigenvectors are: [" + v1[0] + ", " + v1[1] + "] and [" + v2[0] + ", " + v2[1] + "]");
	   
	    input.close();

	}

}
