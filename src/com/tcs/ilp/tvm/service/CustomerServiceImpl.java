package com.tcs.ilp.tvm.service;

import java.util.ArrayList;

import com.tcs.ilp.tvm.bean.Customer;
import com.tcs.ilp.tvm.dao.CustomerDaoImpl;

public class CustomerServiceImpl {
	
	CustomerDaoImpl customerDaoImpl=new CustomerDaoImpl();
	
	public CustomerServiceImpl(){}
	
	public int registerCustomer(Customer cs)
	{
		return customerDaoImpl.registerCustomer(cs);
		
		
	}
	public boolean loginValidation(int loginId,String pwd,String tbl)
	{
		
		return customerDaoImpl.loginValidation(loginId,pwd,tbl);
	}
	
	public boolean update(int id ,String field,Object fieldValue)
	{
		customerDaoImpl.update(id, field, fieldValue);
		return false;
	}
	
	public ArrayList view(int id, boolean isAll)
	{
		
		return customerDaoImpl.view(id,isAll);
	}
	
}
