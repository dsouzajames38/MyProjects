package com.example.rest;
    
import java.sql.*;  

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


	class MyTwitterSQL 
	{  
		final static int READ_QUERY = 0;
		final static int WRITE_QUERY = 1;
	
		//can test the sql independently from command prompt too
		public static void main(String args[]) throws ParserConfigurationException, ClassNotFoundException, Exception 
		{ 
			
			Document doc;
			doc = executeQuery("SELECT * FROM MyTweets",READ_QUERY);
			printXMLDocument(doc);
			
		}
		
		/* querytype = 0 means SELECT query, querytype 1 means data manipulation query */
		// create the mysql insert preparedstatement
		  /*
		  //EXAMPLE OF DATA MANIPULATION QUERY
		  PreparedStatement preparedStmt = conn.prepareStatement(query);
		  preparedStmt.setString (1, "Barney");
		  preparedStmt.setString (2, "Rubble");
		  preparedStmt.setDate   (3, startDate);
		  preparedStmt.setBoolean(4, false);
		  preparedStmt.setInt    (5, 5000);

		  // execute the preparedstatement
		  preparedStmt.execute();
		  */
		  
		public static Document executeQuery(String query, int querytype) throws ParserConfigurationException, ClassNotFoundException, Exception
		{
			Statement stmt; //used for SELECT query
			PreparedStatement preparedStmt; //used for UPDATE, DELETE, INSERT
			ResultSet rs = null;
			ResultSetMetaData metaData = null;
			String columnName;
			int count;
			int type;
			Document doc=null;
			
			try
			{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/mytwitter","root","");  
					
				stmt=con.createStatement();  
		
				if (querytype == READ_QUERY)
				{
					
					rs=stmt.executeQuery(query);  
					
					//UNCOMMENT TO PRINT RECORDS
					/*while (rs.next()) {
						 metaData = rs.getMetaData();
						 count = metaData.getColumnCount();
						 System.out.println("Total columns =" + count);
						 for (int i = 1; i <= count; i++)
						 {
						   columnName = metaData.getColumnName(i);
						   System.out.println("ColumnName =" + columnName);
						   type = metaData.getColumnType(i);
						   System.out.println("ColumnType=" + type);
						   if (type == Types.VARCHAR || type == Types.CHAR || type == 93) {
							   System.out.println(rs.getString(columnName));
							   
						   }
						   if (type==Types.INTEGER){
							   System.out.println(rs.getInt(columnName	));
							}
						 }
					}*/
					
				doc = toDocument(rs);	
				}
				
				else if (querytype == WRITE_QUERY)
				{
					preparedStmt = con.prepareStatement(query);
					preparedStmt.execute();
				}
					
									
				con.close();  
				
				
			}
			catch(Exception e)
			{ 
				System.out.println(e.toString());
				rs = null;
				throw e;
			}  
			
			return doc;
		}
		
		
		public static Document toDocument(ResultSet rs) throws ParserConfigurationException, ClassNotFoundException, Exception
			{
			   Document doc=null;
			   
			   try
			   {
				   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				   DocumentBuilder builder        = factory.newDocumentBuilder();
				   doc                   = builder.newDocument();

				   Element results = doc.createElement("Results");
				   doc.appendChild(results);

				   ResultSetMetaData rsmd = rs.getMetaData();
				   int colCount           = rsmd.getColumnCount();

				   while (rs.next())
				   {
					  Element row = doc.createElement("Row");
					  results.appendChild(row);

					  for (int i = 1; i <= colCount; i++)
					  {
						 String columnName = rsmd.getColumnName(i);
						 Object value      = rs.getObject(i);

						 Element node      = doc.createElement(columnName);
						 node.appendChild(doc.createTextNode(value.toString()));
						 row.appendChild(node);
					  }
				   }
			   }
			   catch (Exception e)
			   {
				   System.out.println(e.toString());
				   throw e;
			   }
			   return doc;
			}
		
			
		
		public static String printXMLDocument(Document doc) throws ParserConfigurationException, ClassNotFoundException, Exception
		{
			DOMSource domSource = new DOMSource(doc);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			StringWriter sw = new StringWriter();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);

			System.out.println(sw.toString());
			return sw.toString();
		}
		
		public static String printHTMLDocument(Document doc) throws ParserConfigurationException, ClassNotFoundException, Exception
		{
			String htmloutput = "";
			
			//start with the root node processing
			htmloutput = doSomethingOnXMLNode(doc.getDocumentElement(), htmloutput);
			
			return htmloutput;
		}
		
		public static String doSomethingOnXMLNode(Node node, String htmloutput) 
		{
			
			htmloutput = htmloutput + "<br>" + node.getNodeName() + "=" + node.getTextContent() ;
			
			
			// do something with the current node instead of System.out
			System.out.println(node.getNodeName() + "=" + node.getTextContent() );

			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node currentNode = nodeList.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					//calls this method for all the children which is Element
					htmloutput = doSomethingOnXMLNode(currentNode, htmloutput);
				}
			}
			
			return htmloutput;
		}
		
		
	}  