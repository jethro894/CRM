package com.crm.ass3;

import java.io.File;
import java.io.IOException;

public class AgentDBAPI {
	private final static String filename = "agents.txt";
	protected static File file;
	
	public static AgentVO retrieveAgent(String agentID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		AgentVO av = null;
		///
		return av;
	}
	
	public static String lookupAgent(){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		String resultID = null;
		///
		return resultID;
	}
	
	synchronized public static void saveAgent(AgentVO cv) throws IOException{
		file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		///
	}
	
	synchronized public static void updateAgent(AgentVO cv){
		file = new File(filename);
		if(!file.exists())
			return;
		///
	}
	
	synchronized public static void deleteAgent(String agentID){
		file = new File(filename);
		if(!file.exists())
			return;
		///
	}
}
