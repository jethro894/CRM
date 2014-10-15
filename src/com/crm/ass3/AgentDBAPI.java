package com.crm.ass3;

import java.io.File;
import java.io.IOException;

public class AgentDBAPI {
	private final static String filename = "agents.txt";
	protected static File file;
	
	//given agentID, retrieve a agentVO
	public static AgentVO retrieveAgent(String agentID){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		AgentVO av = null;
		///do sth
		return av;
	}
	
	//given some keyword(email), return agentID
	public static String lookupAgent(){
		file = new File(filename);
		if(!file.exists())
			return null;
		
		String resultID = null;
		///do sth
		return resultID;
	}
	
	//save a agentvo to database
	synchronized public static void saveAgent(AgentVO cv) throws IOException{
		file = new File(filename);
		if(!file.exists())
			file.createNewFile();
		///do sth
	}
	
	//update a agentvo
	synchronized public static void updateAgent(AgentVO cv){
		file = new File(filename);
		if(!file.exists())
			return;
		///do sth
	}
	
	//delete a agent from db
	synchronized public static void deleteAgent(String agentID){
		file = new File(filename);
		if(!file.exists())
			return;
		///do sth
	}
}
