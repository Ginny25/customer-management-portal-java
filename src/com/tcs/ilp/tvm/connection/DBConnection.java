package com.tcs.ilp.tvm.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	
	public static  Connection getDbConnection()
	{
		Connection con=null;
		try{
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String userName="Scott";
			String password="tiger";                
			String driverClass= "oracle.jdbc.driver.OracleDriver";
			
			
		Class.forName(driverClass);
		con=DriverManager.getConnection(url,userName,password);
		if(con!=null)
		{
			//System.out.println("Connection established:");
		}else
			System.out.println("Connection failed");
		
		
		
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
	    
		return con;
	}
    
}
