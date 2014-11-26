package DBAPI;

import java.util.List;

import VO.*;

public class CustomerDBAPI {
	private final static String table = "view_customer";
	
	//given customerID, retrieve a customerVO
	public static CustomerVO retrieveCustomer(String customerID){
		System.out.print(customerID+"\n");
		return (CustomerVO)DBAPI.retrieve(table, customerID);
	}
	
	//given some keyword(email), return customerID
	public static List<String> lookupCustomer(String key,int page,int limit){
		int offset=page*limit;
		return DBAPI.lookupPagination(table, key,offset,limit);
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
	
	public static int searchCAmount(String key){
		return DBAPI.lookupAmount(table, key);
	}
	
	//verify a customer from db
/*	public static boolean verifyCustomer(String customerid){
		return DBAPI.verify(table, customerid);
	}
*/
}
