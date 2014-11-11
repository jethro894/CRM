package com.crm.ass3.subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriptionManager {
	//Singleton pattern
	private static volatile SubscriptionManager instance = null;
	
	ExecutorService executor;
	List<SubscriptionVO> subs;
	
	private SubscriptionManager(){
		this.subs = new ArrayList<SubscriptionVO>();
		this.executor = Executors.newCachedThreadPool();
	}
	
	synchronized public static SubscriptionManager getSubscriptionManager(){
		if(instance == null)
			instance =  new SubscriptionManager();
		return instance;
	}
	
	public void addSubscription(SubscriptionVO s){
		synchronized(this){
			s.createSubscription();
			subs.add(s);
		}
		executor.execute(s);
	}
	
	public void forceTerminate(){
		subs.clear();
		executor.shutdownNow();
	}
}
