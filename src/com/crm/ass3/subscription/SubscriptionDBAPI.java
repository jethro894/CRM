package com.crm.ass3.subscription;

import java.util.List;

import com.crm.ass3.DBAPI;

public class SubscriptionDBAPI {
private final static String table = "subscriptions";
	
	public static SubscriptionVO retrieveSubscription(String subscriptionID){
		return (SubscriptionVO)DBAPI.retrieve(table, subscriptionID);
	}
	
	public static List<String> lookupSubscription(String customerID, String agentID){
		String key = customerID + ":" + agentID;
		return DBAPI.lookup(table, key);
	}
	
	public static boolean saveSubscription(SubscriptionVO sv){
		return DBAPI.save(table, sv);
	}
	
	public static boolean updateSubscription(SubscriptionVO sv){
		return DBAPI.update(table, sv);
	}
	
	public static boolean deleteSubscription(String subscriptionID){
		return DBAPI.delete(table, subscriptionID);
	}
}
