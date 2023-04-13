//Converting between parametric and implicit equations 
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) {
		//Creating the scanner for the using to input values into
	    Scanner input = new Scanner(System.in);
	    
		System.out.println("Parametric or Implicit?");
		String answer = input.next();
	
		if(answer.equals("Implicit")) {
			System.out.println("What do you want for the value of A?");
			double valueA = input.nextDouble();
			
			System.out.println("What do you want for the value of B?");
			double valueB = input.nextDouble();
			
			System.out.println("What do you want for the value of C?");
			double valueC = input.nextDouble();
			
			double zero = 0; 
		    double negA = ((valueA * -1));
		    
		    
		    if(Math.abs(valueA) > Math.abs(valueB)) {
		    	double valueP = ((valueC * -1)/valueA);
		    	System.out.println("The parametric equation is: ");
		    	System.out.println("L(t) =  [" + valueP + "] + t[" + valueB + "]");
		    	System.out.println("        [" + zero +  "]  [" + negA  + "]");
		    	
		    }
		    if(Math.abs(valueA) < Math.abs(valueB)) {
		    	double valueP = ((valueC * -1)/valueB);
		    	System.out.println("The parametric equation is: ");
		    	System.out.println("L(t) =  [" + valueP + "] + t[" + valueB + "]");
		    	System.out.println("        [" + zero +  "]  [" + negA  + "]");
		    }
		    
		}
		
		if(answer.equals("Parametric")) {
			System.out.println("What do you want for the values of P1 and P2?");
			double valueP1 = input.nextDouble();
			double valueP2 = input.nextDouble();
			
			System.out.println("What do you want for the value of V1 and V2?");
			double valueV1 = input.nextDouble();
			double valueV2 = input.nextDouble();
			
			double valueA = ((valueV2 * -1));
			double valueB = valueV1;
			double valueC = ((valueA * valueP1) - (valueB * valueP2));
			
			System.out.println("The implicit equation is: " + valueA + "x1 + " + valueB + "x2 + " + valueC + " = 0");
		}
		
		
		
		
	    //closing the scanner 
	    input.close();

	}//end main 
	
	

}
