package MyPracticals.Practical4;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;



public class CustomerRestService extends HttpServlet {
	 
	 public String data;
	 public MySQL db;
	 public String query;
	 final int READ_QUERY = 0;
	 final int WRITE_QUERY = 1;


	 // Executed when servlet is first loaded into container.    
	 public void init() {        
				db = new MySQL();
		}

	 // fetches customer information from the database
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	 {
		 String ID = request.getParameter("ID");
		 
		 if (ID == "")
		 {	 
			query = "SELECT * FROM CUSTOMER";
			data = db.executeQuery(query, READ_QUERY) ; //return all customers
			data = data + "<br><br>GET METHOD WAS CALLED with ID = " + ID;
			
		 }
		 else
		 {
			query = "SELECT * FROM CUSTOMER WHERE ID = " + ID;
			data = db.executeQuery(query,READ_QUERY) ; //return specific customer only
			data = data + "<br>GET CALLED with ID = " + ID;
		 }
		 
		 
		 sendresponse(response, data);
	 }
	 
	 // creates a customer in database
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	 {
		 // Modern Browsers do not support PUT and DELETE. This is a workaround to manage the same
		 String method = request.getParameter("_method"); 
		 
		 data = data + "<br>POST CALLED.";
		 
		 if (method.equals("post"))
		 {
		 
			 String ID = request.getParameter("ID");
			 String name = request.getParameter("name");
			 query = "INSERT INTO CUSTOMER VALUES ("+ID+",'"+name+"')";
					 
			 data = db.executeQuery(query,WRITE_QUERY) ; //fetch all details from request and create customers and return
			 data = data + " with name = " + name + "<br>Query=" + query ;
			 
			 sendresponse(response, data);
			 
		 }
		 else if (method.equals("delete"))
		 {
			 doDelete(request,response);
		 }
		 else if (method.equals("put"))
		 {
			 doPut(request,response);
		 }
		 else
		 {
			 data = "<br>http method not found. method=" + method;
			 sendresponse(response, data);
		 }
			 
	 }
	 
	 // updates a customer in database
	 public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	 {
		 String ID = request.getParameter("ID");
		 String name = request.getParameter("name");
		 query = "UPDATE CUSTOMER SET NAME = '" + name+ "' WHERE ID=" + ID;
		 data = db.executeQuery(query, WRITE_QUERY) ;//update the customer requested
		 data = data + "<br>PUT CALLED with ID = " + ID + "<br>Query=" + query ;
		 
		 sendresponse(response, data);
	 }
	 
	 // Deletes a customer from database
	 public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
	 {
		 String ID = request.getParameter("ID");
		 
		 query = "DELETE FROM CUSTOMER WHERE ID = " + ID;
		 data = db.executeQuery(query,WRITE_QUERY) ;//delete the customer requested and return
		 data = data + "<br>DELETE CALLED with ID = " + ID + "<br>Query=" + query ;
		 
		 sendresponse(response, data);
	 }
	 
	 public void sendresponse(HttpServletResponse response, String data)
	 {
		 try {            
			
			data = "<html>" + data ;
			data = data + "<br><a href='CustomerRestClient.html'>Back to Main Page</a>";
			data = data + "</html>";
			
			OutputStream out = response.getOutputStream();            
			out.write(data.toString().getBytes());            
			out.flush();        
		}        
		catch(IOException e) {            
			throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 
}

	 
	  