package DBAPI;

import java.util.List;

import VO.*;

public class AgentDBAPI {
	private final static String table = "view_agent";
	
	//given agentID, retrieve a agentVO
	public static AgentVO retrieveAgent(String agentID){
		return (AgentVO)DBAPI.retrieve(table, agentID);
		}
	
	//given some keyword(email), return agentID
	public static List<String> lookupAgent(String key){
		return DBAPI.lookup(table, key);
	}
	
	//save a agentvo to database
	public static void saveAgent(AgentVO av){
		DBAPI.save(table, av);
	}
	
	//update a agentvo
	public static boolean updateAgent(AgentVO av){
		return DBAPI.update(table, av);
	}
	
	//delete a agent from db
	public static boolean deleteAgent(String agentID){
		return DBAPI.delete(table, agentID);
	}
	
	public static boolean verifyAgent(String agentID){
		///;
		VOBase result=DBAPI.retrieve(table, agentID);
		if (result==null)
			return false;
		else
			return true;
	}
}
