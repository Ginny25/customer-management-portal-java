package com.tcs.ilp.tvm.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tcs.ilp.tvm.bean.Customer;
import com.tcs.ilp.tvm.service.CustomerServiceImpl;

public class CustomerController {
	
	 Scanner sc=new Scanner(System.in);
	CustomerServiceImpl customerServiceImpl=new CustomerServiceImpl();
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		
		CustomerController customerController=new CustomerController();
         //Manager 1008
		//Pass ginny
    
		//System.out.println("tcstvm".hashCode());
   		System.out.println("Enter your choice");
		System.out.println("Press 1 Customer");
		System.out.println("Press 2 Manager");
		int i=Integer.parseInt(sc.nextLine());
			

	
		switch(i)
		{
		   case 1:
		   {
			     System.out.println("Enter your choice");
				System.out.println("Press 1 For Registration");
				//1003 tcstvm
				System.out.println("Press 2 For Already Register");
				
				int j=Integer.parseInt(sc.nextLine());
				switch(j)
				{
				   case 1:
				   {
					   
						customerController.registerCustomer();
						break;
					
				   }
				   case 2:
				   {
					    System.out.print("Enter your Login id");
						//Scanner sc=new Scanner(System.in);
						int loginId=Integer.parseInt(sc.nextLine());
						System.out.print("Enter your password: ");
						String pwd=sc.nextLine();
						Integer k=pwd.hashCode();
						pwd=k.toString();
					   
					   if(customerController.login("cust",loginId, pwd))
					   {
						   
						   int c;
						   customerController.displayRecord(false,loginId);
		               System.out.println("For update press 1");
		               c=Integer.parseInt(sc.nextLine());
		               
		               if(c==1) 
		               {
					   
		            	   
		            	   customerController.updateRecord(loginId);
					   }   
		               
		               
					   }  
					   break;
				   }
				   
				   default:
						System.out.println("Wrong choice:");
				
				}
				break;
				
		   }
		   
		   case 2:
		   {
			   
				System.out.println("Press Enter for login:");
				System.out.print("Enter your Login id");
				//Scanner sc=new Scanner(System.in);
				int loginId=Integer.parseInt(sc.nextLine());
				System.out.print("Enter your password: ");
				String pwd=sc.nextLine();
				Integer k=pwd.hashCode();
				pwd=k.toString();
				if(customerController.login("mgr",loginId, pwd))
				{
					int ch;
					System.out.println("Enter your choice");
					System.out.println("Press 1 to view the customer's records");
					System.out.println("Press 2 to remove the customer");
					
					ch = Integer.parseInt(sc.nextLine());
					boolean b=ch==2?true:false;
					boolean bt=false;
					if(ch==1)
					{
						customerController.displayRecord(true,loginId);
						System.out.println("Press 1 to remove the customer");
						
						 ch = Integer.parseInt(sc.nextLine());
						 bt=ch==1?true:false;
						
						
					}
					if(b || bt)
					{
						System.out.println("Delete  customer");
						
						
					}
			
					
					
				}
				else
				{
					System.out.println("invalid credentials");
				}
				
				break;
			
		   }
		   default:
				System.out.println("Wrong choice:");
		
		}
		
	}
	
	public boolean login(String tbl,int loginId,String pwd)
	{
		
		//System.out.println(i);
		boolean flag=customerServiceImpl.loginValidation(loginId,pwd,tbl);
		if(flag)
		{
		System.out.println("You login Successfully");
		return true;
		}else
		{
			System.out.println("Your loginId or password is Wrong ");
		    return false;
		}
	}
	
	public  void registerCustomer()
	{
		System.out.println("Please enter your detail:");
		try{
		
		
		System.out.println("Full Name:");
		String name=sc.nextLine();
		Pattern p=Pattern.compile("[^A-za-z ]");
		Matcher m=p.matcher(name);
		if(name.equals("") || name==null  || m.find())
		{
			System.out.println("Please enter the valid Name:");
			return;
			
		}
		System.out.println(" Address:");
		String address=sc.nextLine();
		
		if(address.equals("") || address==null || address.length()==0)
		{
			System.out.println("Please enter the valid address:");
			return;
			
		}
		System.out.println("Email Id:");
		String emailId=sc.nextLine();
		Pattern ep=Pattern.compile("[^A-za-z0-9@.]");
		Matcher em=ep.matcher(emailId);
		
		int count=0;
		char ch[]=emailId.toCharArray();
		for(int i=0;i<ch.length;i++)
		{
			if(ch[i]=='@')
			{
				
				count++;
			}
			
		}
		
		if(emailId.equals("") || emailId==null || em.find() || count!=1)
		{
			System.out.println("Please enter the valid emailId:");
			return;
			
		}
		System.out.println("Please enter your contact no:");
		String cn=sc.nextLine();
		
		
		if(cn.equals("") || cn==null || cn.length()!=10)
		{
			System.out.println("Please enter the valid contact No:");
			return;
			
		}
		System.out.println("Please enter your password");
		String pwd=sc.nextLine();
		if(pwd.length()<5 && pwd.length()>10)
		{
			System.out.println("Invalid password /n Password length is between 5 to 10:");
		    return; 
		}
		System.out.println("confirm password");
		String pwdConfirm=sc.nextLine();
		if(!pwdConfirm.equals(pwd))
		{
			System.out.println("Password not matched:");
		    return; 
		}
		
		
		Customer cs=new Customer();
		cs.setName(name);
		cs.setAddress(address);
		cs.setEmailId(emailId);
		BigInteger contactNumber=new BigInteger(cn);
		long contactnumber = Long.parseLong(cn);
		cs.setContactNumber(contactnumber);
		Integer i=pwdConfirm.hashCode();
		//System.out.println(i);
		cs.setPassword(i.toString());
		///cs.setUserType("Customer");
		int id=customerServiceImpl.registerCustomer(cs);
		if(id<=0)
			System.out.println("Registration is unsuccessfull");
		else{
			System.out.println("Your Register Successfully");
		   System.out.println("Please Note Your Registration ID "+id);
		    }
		}catch(NumberFormatException nm)
		{
		System.out.println("Please enter the valid contact No:");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
  }
	
	public void displayRecord(boolean isAll,int id)
	{
		
		
		ArrayList al=null;
		if(isAll)
		{
			al=customerServiceImpl.view(0,isAll);
			if(al==null || al.size()==0)
			{
				System.out.println("no record founf");
				
			}
			for(int i=0;i<al.size();i++)
			{
				Customer cs=(Customer)al.get(i);
				System.out.println(cs.getName()+"\t\t"+cs.getRegistrationId()+"\t\t"+cs.getAddress()+"\t\t"+cs.getEmailId()+"\t\t"+cs.getContactNumber()+"\t\t"+cs.getReg_date()+"\t\t"+cs.getStatus());
				
			}
			return;
		
		}else {
			
		   al=customerServiceImpl.view(id,isAll);
		   if(al==null || al.size()==0)
			{
				System.out.println("no record founf");
				
			}
		   for(int i=0;i<al.size();i++)
			{
				Customer cs=(Customer)al.get(i);
				System.out.println(cs.getName()+"\t\t"+cs.getRegistrationId()+"\t\t"+cs.getAddress()+"\t\t"+cs.getEmailId()+"\t\t"+cs.getContactNumber()+"\t\t");
				
			}
		return;
		}
		
		
		
		
		
	}
	
	public void updateRecord(int id)
	
	{
		/*System.out.println("please enter your field which needs to be updated");
		System.out.println("1: name \n 2:contactno \n  3: emailid \n  4: password ");*/
		
		
		String field;
		
		//String fieldvalue;
		
		System.out.println("please select the field which you want to update");
		System.out.println("1: name \n 2:emailid \n  3: contactno \n  4: password ");
		
		int ch = Integer.parseInt(sc.nextLine());
		
		switch(ch)
		{
		
		case 1:{
			//Scanner sc = new Scanner(System.in);
			//System.out.println(sc);
			field = "cust_name";
			System.out.println("please enter the new name");
			String fieldvalue=sc.nextLine();
			fieldvalue = "'"+fieldvalue+"'";
			//System.out.println("please enter the new name");
			customerServiceImpl.update(id ,field,fieldvalue);
			break;
			
		}
		
		case 2:{
			
			field = "emailid";
			System.out.println("please enter the new mailid");
			String fieldvalue=sc.nextLine();
			fieldvalue = "'"+fieldvalue+"'";
			customerServiceImpl.update(id ,field,fieldvalue);
			break;
			
			
		}
		
		case 3:{
			
			field = "contact_no";
			System.out.println("please enter the new number");
			long fieldvalue=sc.nextLong();
			
			customerServiceImpl.update(id ,field,fieldvalue);
			break;
			
			
			
		}
		
		case 4:{
			//Scanner sc = new Scanner(System.in);
			//System.out.println(sc);
			field = "pwd";
			System.out.println("please enter the new password");
			String fieldvalue=sc.nextLine();
			fieldvalue = "'"+fieldvalue+"'";
			//System.out.println("please enter the new name");
			customerServiceImpl.update(id ,field,fieldvalue);
			break;
			
		}
		
		
		}
		
		
		
		
		//customerServiceImpl.update(id ,field,fieldvalue);
		
		
	}
	public void deleteCustomer()
	{
		
		
		
	}
}
