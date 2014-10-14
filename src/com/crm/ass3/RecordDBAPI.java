package com.crm.ass3;

import java.io.File;
import java.io.IOException;

public class RecordDBAPI {
	private final static String filename = "records.txt";
	protected static File file;
	
	public static RecordVO retrieveRecord(String recordID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		RecordVO rv = null;
		///
		return rv;
	}
	
	public static String lookupRecord(){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		String resultID = null;
		///
		return resultID;
	}
	
	synchronized public static void saveRecord(RecordVO rv) throws IOException{
		file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		///
	}
	
	synchronized public static void updateRecord(RecordVO rcv){
		file = new File(filename);
		if(!file.exists())
			return;
		///
	}
	
	synchronized public static void deleteRecord(String recordID){
		file = new File(filename);
		if(!file.exists())
			return;
		///
	}
}
