package com.tcs.ilp.tvm.bean;

import java.math.BigInteger;
import java.sql.Date;

public class Customer {
	
	private String name;
	private String address;
	private String emailId;
	private long contactNumber ;
    private int registrationId;
    private String password;
    private Date reg_date;
    private String status;
    
    public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
       
    public Customer(){
    	
    	
    }
	public Customer(String name, String address, String emailId, long contactNumber, int registrationId,Date reg_date,String status) {
		
		this.name = name;
		this.address = address;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.registrationId = registrationId;
		//this.password=password;
		this.reg_date=reg_date;
		this.status=status;
		
		
		
	}
public Customer(String name, String address, String emailId, long contactNumber, int registrationId) {
		
		this.name = name;
		this.address = address;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.registrationId = registrationId;
		//this.password=password;
		
		
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}
	
	
    
}
