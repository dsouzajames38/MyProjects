package MyPracticals.Practical3;


import javax.jws.WebService;
import javax.jws.WebMethod;

/* Both SIB and SEI is not implemented together */
@WebService(endpointInterface="MyPracticals.Practical3.MatrixInterface")
public class MatrixService implements MatrixInterface{
	private MatrixUtility utils;
	
	public MatrixService(){
		
		utils = new MatrixUtility(); //create the matrix utility to help with matrix multiplication
	}
	
	public int[][] multiply(int[][] mA, int[][] mB) { 

		return utils.multiplyMatrices(mA,mB); 
	} 

}