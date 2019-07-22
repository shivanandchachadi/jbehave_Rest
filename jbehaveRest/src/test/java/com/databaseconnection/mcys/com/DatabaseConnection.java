package com.databaseconnection.mcys.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	public int bdconnection(String query) throws SQLException
	{
		int result=0;
		Connection con = null;
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydemo","root","root");
		}
		catch( Exception e)
		{
			System.out.println("the connection could not establish");
			
		}
		
		Statement state= con.createStatement();
		ResultSet se=state.executeQuery(query);
		se.next();
		
		result=se.getInt(1);
		
		return result;
		
		
		
		
	}
	
	
}
