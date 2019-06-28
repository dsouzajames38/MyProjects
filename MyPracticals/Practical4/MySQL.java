package MyPracticals.Practical4;
    
	import java.sql.*;  
	
    
	
	class MySQL
	{  
		final static int READ_QUERY = 0;
		final static int WRITE_QUERY = 1;
	
		//can test the sql independently from command prompt too
		public static void main(String args[]){ 
			String data ;
			data = executeQuery("SELECT * FROM CUSTOMER WHERE ID=1",READ_QUERY);
			System.out.println(data);
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
		  
		public static String executeQuery(String query, int querytype)
		{
			
			String sSelectAllCustomerQuery = "SELECT ID, NAME FROM CUSTOMER";
			String sCustomer="";
			String sCustomers="<br>";  
			Statement stmt; //used for SELECT query
			PreparedStatement preparedStmt; //used for UPDATE, DELETE, INSERT
			ResultSet rs;
			
			try
			{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/test","root","");  
					
				stmt=con.createStatement();  
		
				if (querytype == READ_QUERY)
				{
					
					sCustomers = sCustomers + "<br><b>YOUR QUERY RETURNED FOLLOWING CUSTOMERS</b>";
					sCustomers = sCustomers + "<br>";
					
					rs=stmt.executeQuery(query);  
					while(rs.next())  
					{
						sCustomer = "<br><br><font = color=green>"+rs.getInt(1)+"            "+rs.getString(2)+ "</font>";  
						sCustomers = sCustomers + sCustomer;
					}
					
					
				}
				else if (querytype == WRITE_QUERY)
				{
					preparedStmt = con.prepareStatement(query);
					preparedStmt.execute();
					sCustomers = sCustomers + "<br> customer data was modified";
					
				}
				
				sCustomers = sCustomers + "<br>";
								
				//get latest list of  customers each and every time
				
				sCustomers = sCustomers + "<br>";
				sCustomers = sCustomers + "<br>";
				sCustomers = sCustomers + "<br><b>DATABASE - ALL CUSTOMER LIST</b>";
				sCustomers = sCustomers + "<br>";
				
				rs=stmt.executeQuery(sSelectAllCustomerQuery);  
				while(rs.next())  
				{
					sCustomer = "<br><br><font = color=blue>"+rs.getInt(1)+"            "+rs.getString(2) + "</font>";  
					sCustomers = sCustomers + sCustomer;
				}
				
				sCustomers = sCustomers + "<br>";
				con.close();  
				
				
			}
			catch(Exception e)
			{ 
				sCustomers = e.toString();
			}  
			
			return sCustomers;
			
		}
	
	
    
    
    }  