package com.crm.ass3;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecordDBAPI {
	private final static String filename = "records.txt";
	protected static File file;
	
	//given recordID, retrieve a recordVO
	public static RecordVO retrieveRecord(String recordID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		RecordVO rv = null;
		///do sth
		return rv;
	}
	
	//given agentID and customerID, return lists of recordID
	public static List<String> lookupRecord(String customerID, String agentID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		List<String> resultIDs = null;
		///do sth
		return resultIDs;
	}
	
	//save a recordvo to database
	synchronized public static void saveRecord(RecordVO rv) throws IOException{
		file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		///do sth
	}
	
	//update a recordvo
	synchronized public static void updateRecord(RecordVO rcv){
		/*file = new File(filename);
		if(!file.exists())
			return;*/
		///do sth
		rcv.print();
	}
	
	//delete a record from db
	synchronized public static void deleteRecord(String recordID){
		file = new File(filename);
		if(!file.exists())
			return;
		///do sth
	}
}
