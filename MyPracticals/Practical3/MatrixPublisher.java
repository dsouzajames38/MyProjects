package MyPracticals.Practical3;
import javax.xml.ws.Endpoint;


class MatrixPublisher {
	public static void main(String[] args){
		try
		{
			int port = 8883;
			String url = "http://localhost:" + port + "/matrix";
			System.out.println("Publishing Matrix service on url " + url );
			
			MatrixService matrixservice = new MatrixService();
			
			System.out.println("Matrix Service object created");
			
			Endpoint.publish(url, matrixservice);
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	
}