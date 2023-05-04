//explaining all methods and approaches used 
public class pseudocode {
	//Part 1 is finding the parallel and perspective projections for a given matrix 
	//parallel projection:
	//defined by a direction of projection and a projection plane 
	//x' = [I3 - vnT/v*n]x + (q*n/v*n)v
	//perspective projection:
	//used to create more 3D objects 
	// x' = (q*n/x*n)x
	
	//Part 2 is the intersection of a triangle and a line
	//x' = x + ([q-x]*n/v*n)v
	
	
	
	
	//Part 3 is making the google matrix 
	//pseudocode for the program 
	//initialization
	//estimate dominant eigenvector r1 != 0
	//find j where |j1| == ||r1|| and set r1 == r1/rj1
	//set lambda 1 = 0
	//set tolerance 
	//set maximum number of iterations 
	//for k = 2....m
	//y = Ar(k-1)
	//lambda (k) = yi
	//find j where |yi| == ||y||
	//if yi = 0 then output "eigenvalue is zero"
	//r(k) = y/yi
	//if |lambda(k) - lambda(k-1)| < tolerance the output the values
	//if k = m output: maximum iterations exceeded 
	

}
