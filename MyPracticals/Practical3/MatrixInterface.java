package MyPracticals.Practical3; 

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/*
The annotation @WebService signals that this is the SEI (Service Endpoint Interface).
@WebMethod signals that each method is a service operation

The @SOAPBinding annotation impacts the under-the-hood construction of the service contract,
the WSDL (Web Services Definition Language) document.

Style.DOCUMENT is to support rich data types

*/

@WebService
@SOAPBinding(style=Style.DOCUMENT)
public interface MatrixInterface {
	
	@WebMethod int[][] multiply(int[][] A, int[][] B);

}