package com.crm.ass3;

import java.io.File;
import java.io.IOException;

public class CustomerDBAPI {
	private final static String filename = "customers.txt";
	protected static File file;
	
	public static CustomerVO retrieveCustomer(String customerID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		CustomerVO cv = null;
		///
		return cv;
	}
	
	public static String lookupCustomer(){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		String resultID = null;
		///
		return resultID;
	}
	
	synchronized public static void saveCustomer(CustomerVO cv) throws IOException{
		file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		///
	}
	
	synchronized public static void updateCustomer(CustomerVO cv){
		file = new File(filename);
		if(!file.exists())
			return;
		///
	}
	
	synchronized public static void deleteCustomer(String customerID){
		file = new File(filename);
		if(!file.exists())
			return;
		///
	}
}
