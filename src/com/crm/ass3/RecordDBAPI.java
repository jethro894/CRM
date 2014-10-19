package com.crm.ass3;

import java.util.List;

public class RecordDBAPI {
	private final static String table = "records";
	
	//given recordID, retrieve a recordVO
	public static RecordVO retrieveRecord(String recordID){
		return (RecordVO)DBAPI.retrieve(table, recordID);
	}
	
	//given agentID and customerID, return lists of recordID
	public static List<String> lookupRecord(String customerID, String agentID){
		String key = customerID + ":" + agentID;
		return DBAPI.lookup(table, key);
	}
	
	//save a recordvo to database
	synchronized public static void saveRecord(RecordVO rcv){
		DBAPI.save(table, rcv);
	}
	
	//update a recordvo
	synchronized public static void updateRecord(RecordVO rcv){
		DBAPI.update(table, rcv);
	}
	
	//delete a record from db
	synchronized public static void deleteRecord(String recordID){
		DBAPI.delete(table, recordID);
	}
}
