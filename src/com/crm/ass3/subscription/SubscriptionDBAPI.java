package com.crm.ass3.subscription;

import java.util.List;

import com.crm.ass3.DBAPI;

public class SubscriptionDBAPI {
private final static String table = "subscriptions";
	
	public static SubscriptionVO retrieveSubscription(String subscriptionID){
		//return (SubscriptionVO)DBAPI.retrieve(table, subscriptionID);
		return null;
	}
	
	public static List<String> lookupSubscription(String key){
		//return DBAPI.lookup(table, key);
		return null;
	}
	
	public static boolean saveSubscription(SubscriptionVO sv){
		//return DBAPI.save(table, sv);
		return true;
	}
	
	public static boolean updateSubscription(SubscriptionVO sv){
		//return DBAPI.update(table, sv);
		return true;
	}
	
	public static boolean deleteSubscription(String subscriptionID){
		//return DBAPI.delete(table, subscriptionID);
		return true;
	}
}
