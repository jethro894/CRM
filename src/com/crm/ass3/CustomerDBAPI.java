package com.crm.ass3;

import java.io.File;
import java.io.IOException;

public class CustomerDBAPI {
	private final static String filename = "customers.txt";
	protected static File file;
	
	//given customerID, retrieve a customerVO
	public static CustomerVO retrieveCustomer(String customerID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		CustomerVO cv = null;
		///do sth
		return cv;
	}
	
	//given some keyword(email), return customerID
	public static String lookupCustomer(){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		String resultID = null;
		///do sth
		return resultID;
	}
	
	//save a customervo to database
	synchronized public static void saveCustomer(CustomerVO cv) throws IOException{
		file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		///do sth
	}
	
	//update a customervo
	synchronized public static void updateCustomer(CustomerVO cv){
		/*file = new File(filename);
		if(!file.exists())
			return;*/
		///do sth
		cv.print();
	}
	
	//delete a customer from db
	synchronized public static void deleteCustomer(String customerID){
		file = new File(filename);
		if(!file.exists())
			return;
		///do sth
	}
}
