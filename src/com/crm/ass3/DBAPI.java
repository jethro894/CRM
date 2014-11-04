package com.crm.ass3;

import java.util.List;

public class DBAPI {
	protected final String[] customer_facet = {"ID", "Name", "Email", "PhoneNo", "AgentID", "Address"};
	protected final String[] agent_facet = {"ID", "Name", "Email", "PhoneNo", "Address"};
	protected final String[] record_facet = {"CustomerID", "AgentID", "Type", "Data", "TextSummary", "RecordID", "Time"};
	
	public static VOBase retrieve(String tableName, String id){
		VOBase vb = null;
		///do sth
		return vb;
	}
	
	public static List<String> lookup(String tableName, String key){
		List<String> ids = null;
		return ids;
	}
	
	synchronized public static boolean save(String tableName, VOBase vb){
		//if successfully save, return true
		return true;
	}
	
	synchronized public static boolean update(String tableName, VOBase vb){
		return true;
		
	}
	
	synchronized public static boolean delete(String tableName, String id){
		return true;
		
	}
	
	public static boolean verify(String tableName, String id){
		//do something
		//return true if exist, false if not
		//used to verify if customer or agent or record exist
		return  true;
	}
	
}
