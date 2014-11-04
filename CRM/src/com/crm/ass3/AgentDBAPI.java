package com.crm.ass3;

public class AgentDBAPI {
	private final static String table = "agents";
	
	//given agentID, retrieve a agentVO
	public static AgentVO retrieveAgent(String agentID){
		return (AgentVO)DBAPI.retrieve(table, agentID);
		}
	
	//given some keyword(email), return agentID
	public static String lookupAgent(String key){
		return DBAPI.lookup(table, key).get(0);
	}
	
	//save a agentvo to database
	public static void saveAgent(AgentVO av){
		DBAPI.save(table, av);
	}
	
	//update a agentvo
	public static void updateAgent(AgentVO av){
		DBAPI.update(table, av);
	}
	
	//delete a agent from db
	public static void deleteAgent(String agentID){
		DBAPI.delete(table, agentID);
	}
	
	public static boolean verifyAgent(String agentID){
		///;
		return true;
	}
}
