package com.tcs.ilp.tvm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.ilp.tvm.bean.Customer;
import com.tcs.ilp.tvm.connection.DBConnection;

//import oracle.sql.NUMBER;

public class CustomerDaoImpl {
	
	public CustomerDaoImpl(){}
	
	Connection con = null;
	
	public ArrayList view(int id, boolean isAll)
	{
		String query1 = "",query2 = "";
		con = DBConnection.getDbConnection();
		ArrayList al=new ArrayList();
		try {
		if(isAll)
		{
			 query1 = "select * from cust1821971";
             PreparedStatement ps1 = con.prepareStatement(query1);
			
			ResultSet rs1 = ps1.executeQuery(query1);
			
			while(rs1.next())
			{
				
				//System.out.println(rs.getString("cust_name")+" "+ rs.getString("emailid"));
				//Customer(String name, String address, String emailId, long contactNumber, int registrationId,Date reg_date,String status) {  
				Customer cs=new Customer(rs1.getString("cust_name"), rs1.getString("address"), rs1.getString("emailid"),rs1.getLong("contact_no"), rs1.getInt("reg_id"), rs1.getDate("registration_date"),rs1.getString("status"));
				al.add(cs);
				
				//return al;
				
			}
			rs1.close();
			ps1.close();
			con.close();
			
			
		}else {
			query2="select reg_id,cust_name,emailid,contact_no,address,status from cust1821971 where Reg_id="+id;
		//reg_id,cust_name,emailid,contact_no,address,status
			
			
            PreparedStatement ps2 = con.prepareStatement(query2);
			
			ResultSet rs2 = ps2.executeQuery(query2);
			
			while(rs2.next())
			{
				//System.out.println(rs.getString("cust_name")+" "+ rs.getString("emailid"));
				                 //Customer(String name, String address, String emailId, long contactNumber, int registrationId) {
				Customer cs=new Customer(rs2.getString("cust_name"), rs2.getString("address"), rs2.getString("emailid"),rs2.getLong("contact_no"), rs2.getInt("reg_id"));
				al.add(cs);
				
				//return al;
			}
			rs2.close();
			ps2.close();
			con.close();
		}
		
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return al;
	}

	public void update(int id ,String field,Object fieldValue)
	{
		String query = "update cust1821971 set "+field+"="+fieldValue+" where reg_id=?";
		con = DBConnection.getDbConnection();
		//System.out.println(query);
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			
			int i =ps.executeUpdate();
			
			if(i==1)
			{
				System.out.println("table updated ");
				
			}
			else
			{
				System.out.println(" updation failed");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public boolean loginValidation(int loginId,String pwd, String tbl)
	{
		String query ="";
		con = DBConnection.getDbConnection();
		if(tbl.equals("mgr"))
			query= "select password from"+" ccmp_mgr "+"where mgr_id=?";
		else
			query= "select pwd from"+" CUST1821971 "+"where Reg_Id=?";
		String dbPwd="";
		
		ResultSet rs=null;
		PreparedStatement ps=null;
		boolean loginSuccess=false;
		
		
		if(con==null)
		{
			System.out.println("Connection failed try after some time:");
		}
	 try{
		/*
           if(i<1)
        	   System.out.println("Row is inserted");
           else
        	   System.out.println("Registration is successfull");
           */
		  
	      
           ps=con.prepareStatement(query);
           ps.setInt(1,loginId);
           rs=ps.executeQuery();
           
            
           if(rs!=null)
           {
        	  if(rs.next()){
        	  dbPwd=rs.getString(1);
        	  }
        	  else{
        		  throw new Exception("User Id does not exit:");
        	     }
           }else
           {
        	   //System.out.println("Something Went Wrong");
        	   throw new Exception("Something Went Wrong");
           }
           if(pwd.equals(dbPwd))
           {
        	   loginSuccess=true;
           }
           
           
	 }catch(Exception e)
	 {
		 System.out.println(e.getMessage());
		 //e.printStackTrace();
	 }finally
	 {
		 try{
		 rs.close();
		 ps.close();
		 con.close();
		 }catch(Exception e)
		 {
			 System.out.println("Error in connection closing");
			 e.printStackTrace();
  	 
	     }
         
	 return loginSuccess;
	}

} 
	
	
	public int registerCustomer(Customer cs)
	{
		con = DBConnection.getDbConnection();
		int id=0;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		if(con==null)
		{
			System.out.println("Connection failded try after some time:");
		}
	 try{
		   ps=con.prepareStatement("insert into CUST1821971(Reg_Id,Cust_Name,emailId,Contact_No,Address,Registration_date,pwd,status) values(customers_seq_1821971.nextVal,?,?,?,?,sysdate,?,'Active')");
	       ps.setString(1, cs.getName());
	       ps.setString(2, cs.getEmailId());
	      // NUMBER cn = new NUMBER(cs.getContactNumber());
	       ps.setLong(3,cs.getContactNumber());
           ps.setString(4, cs.getAddress());
           
           ps.setString(5, cs.getPassword());
          // ps.setString(6, cs.getUserType());
           int i=ps.executeUpdate();
           /*
           if(i<1)
        	   System.out.println("Row is inserted");
           else
        	   System.out.println("Registration is successfull");
           */
           ps=con.prepareStatement("select Reg_id from cust1821971 where emailId=?");
           
           ps.setString(1, cs.getEmailId());
           
           rs=ps.executeQuery();
            
           if(rs!=null)
           {
        	  if(rs.next())
        	  {
        	  id=rs.getInt(1);
        	  }
        	  else{
        		  throw new Exception();
        	  }
           }else
        	   id=-1;
           
           
           
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }finally
	 {
		 try{
		 rs.close();
		 ps.close();
		 con.close();
		 }catch(Exception e)
		 {
			 System.out.println("Error in connection closing" +e);
			// e.printStackTrace();
		 }
		 
	 }
	   
	   
	 return id;
	}

}
