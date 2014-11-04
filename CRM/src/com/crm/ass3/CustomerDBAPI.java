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
	public static boolean saveCustomer(CustomerVO cv){
		return DBAPI.save(table, cv);
	}
	
	//update a customervo
	public static boolean updateCustomer(CustomerVO cv){
		return DBAPI.update(table, cv);
	}
	
	//delete a customer from db
	public static boolean deleteCustomer(String customerID){
		return DBAPI.delete(table, customerID);
	}
	
	//verify a customer from db
	public static boolean verifyCustomer(String customerid){
		return DBAPI.verify(table, customerid);
	}
}
