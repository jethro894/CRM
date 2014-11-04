package com.crm.servlets.interlevel;
import com.crm.ass3.*;


public class CAgent {
	//调用agentvo的数据库查询函数并且转换成json
	//	public static JSONObject getCustomerByID(String ID){
		//	AgentVO agent = AgentVO.retrieveAgentVO(ID);
			
		//	return customer.ToJson();
	//	}
		
		public static boolean verifyAgent(String ID) 
		{
			boolean ifexist = AgentVO.verifyAgent(ID);
			return ifexist;
		}

}
