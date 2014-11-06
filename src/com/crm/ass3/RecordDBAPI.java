package com.crm.ass3;

import java.util.List;

public class RecordDBAPI {
	private final static String table = "view_record";
	
	//given recordID, retrieve a recordVO
	public static RecordVO retrieveRecord(String recordID){
		return (RecordVO)DBAPI.retrieve(table, recordID);
	}
	
	//given agentID and customerID, return lists of recordID
	public static List<String> lookupRecord(String key){
		return DBAPI.lookup(table, key);
	}
	
	//save a recordvo to database
	public static boolean saveRecord(RecordVO rcv){
		return DBAPI.save(table, rcv);
	}
	
	//update a recordvo
	public static boolean updateRecord(RecordVO rcv){
		return DBAPI.update(table, rcv);
	}
	
	//delete a record from db
	public static boolean deleteRecord(String recordID){
		return DBAPI.delete(table, recordID);
	}
}
