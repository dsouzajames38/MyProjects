package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.http.HTTPException;


import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import java.sql.*;  
 

/**
 * Root resource (exposed at "MyTwitter" path)
 */
@Path("/MyTwitter")
public class MyTwitter {
  
  public String data;
  public MyTwitterSQL db;
  public String query;
  final int READ_QUERY = 0;
  final int WRITE_QUERY = 1;

  public MyTwitter(){
	db = new MyTwitterSQL(); //create the database object  
  }
  
  public Response sendresponse(Document doc)
	 {
		 try {            
			
			data = "<html>";
			data = data + "<h2><a href='/tweet.html'>Back to Home<a></h2><br><br>";
						
			//add any additional data here data = data + additionaldata			
			 data = data + "<br>"; //new line
			if(doc==null)
			{
				data = data + "data updated";
			}
			else
			{
			  //data =  data + db.printXMLDocument(doc);
			  data =  data + db.printHTMLDocument(doc);
			  
			}		
			
			data = data + "/html";

			return Response.status(200).entity(data).build();
			
		}        
		catch(Exception e) {            
			throw new HTTPException(400);
		}
	 }
	 
	public Response sendresponse(String output)
	 {
		 try {            
			data = output;
			data = "<html>" + data + "</html>";
			return Response.status(200).entity(data).build();
		}        
		catch(Exception e) {            
			throw new HTTPException(400);
		}
	 }
  
  
  @GET
  @Path("/profile/all")
  @Produces(MediaType.TEXT_HTML)
  public Response getAllProfiles() {
    
	System.out.println("Get out profiles called.");
	//ResultSet rs;
	Document doc;
	/* connect to database and get all Profiles */
	query = "SELECT * FROM PROFILE";
	
	try
	{
		doc = db.executeQuery(query, READ_QUERY) ; //return all customers
	}
	catch(Exception e)
	{
		return sendresponse(e.toString()); //send the error as response
	}
	
	return sendresponse(doc);
	
  }
  
 @GET
  @Path("/tweet/all")
  @Produces(MediaType.TEXT_HTML)
  public Response getAllTweets() {
    
	System.out.println("Get all tweets called.");
	//ResultSet rs;
	Document doc;
	/* connect to database and get all Profiles */
	query = "SELECT * FROM mytweets";
	try
	{
		doc = db.executeQuery(query, READ_QUERY) ; //return all customers
	}
	catch (Exception e)
	{
		return sendresponse(e.toString()); //send the error as response
	}
	
	return sendresponse(doc);
	
  }

  
  @POST
  @Path("/tweet/post")
  @Produces(MediaType.TEXT_HTML)
  public Response postMyTweet(@FormParam("sTweet") String sTweet) {
    
	
	//ResultSet rs;
	Document doc=null;
	/* connect to database and get all Profiles */
	query = "insert into mytweets (nProfileID,Tweet)values(1,'"+sTweet+"')";
	System.out.println(query);
	
	try
	{
		doc = db.executeQuery(query, WRITE_QUERY) ; //return all customers
	}
	catch(Exception e)
	{
		return sendresponse(e.toString()); //send the error as response
	}
	
	return sendresponse(doc);
	
  } 
  
}
