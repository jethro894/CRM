package com.crm.ass3;

public class CustomerDBAPI {
	private final static String table = "customers";
	
	//given customerID, retrieve a customerVO
	public static CustomerVO retrieveCustomer(String customerID){
		return (CustomerVO)DBAPI.retrieve(table, customerID);
	}
	
	//given some keyword(email), return customerID
	public static String lookupCustomer(String key){
		return DBAPI.lookup(table, key).get(0);
	}
	
	//save a customervo to database
	synchronized public static void saveCustomer(CustomerVO cv){
		DBAPI.save(table, cv);
	}
	
	//update a customervo
	synchronized public static void updateCustomer(CustomerVO cv){
		DBAPI.update(table, cv);
	}
	
	//delete a customer from db
	synchronized public static void deleteCustomer(String customerID){
		DBAPI.delete(table, customerID);
	}
}
