package com.crm.ass3;

import java.util.List;

public class CustomerDBAPI {
	private final static String table = "customers";
	
	//given customerID, retrieve a customerVO
	public static CustomerVO retrieveCustomer(String customerID){
		//return (CustomerVO)DBAPI.retrieve(table, customerID);
		return null;
	}
	
	//given some keyword(email), return customerID
	public static List<String> lookupCustomer(String key){
		//return DBAPI.lookup(table, key);
		return null;
	}
	
	//save a customervo to database
	public static boolean saveCustomer(CustomerVO cv){
		//return DBAPI.save(table, cv);
		return true;
	}
	
	//update a customervo
	public static boolean updateCustomer(CustomerVO cv){
		//return DBAPI.update(table, cv);
		return true;
	}
	
	//delete a customer from db
	public static boolean deleteCustomer(String customerID){
		//return DBAPI.delete(table, customerID);
		return true;
	}
	
	//verify a customer from db
	public static boolean verifyCustomer(String customerid){
		//return DBAPI.verify(table, customerid);
		return true;
	}
}
