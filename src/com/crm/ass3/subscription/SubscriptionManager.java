package com.crm.ass3.subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubscriptionManager {
	//Singleton pattern
	private static volatile SubscriptionManager instance = null;
	
	List<SubscriptionVO> subs;
	List<Thread> threads;
	
	private SubscriptionManager(){
		this.subs = new ArrayList<SubscriptionVO>();
		this.threads = new ArrayList<Thread>();
	}
	
	synchronized public static SubscriptionManager getSubscriptionManager(){
		if(instance == null)
			instance =  new SubscriptionManager();
		return instance;
	}
	
	synchronized public void addSubscription(SubscriptionVO s){
		s.createSubscription();
		subs.add(s);
		Thread t = new Thread(s);
		threads.add(t);
		threads.get(threads.size()-1).start();
	}
	
	synchronized public void stopSubscription(String subscription_id){
		int i = -1;
		for(int j = 0; j < subs.size(); j++){
			if(subs.get(j).getSubscriptionID().getID().equals(subscription_id)){
				i = j;
				break;
			}
		}
		threads.get(i).interrupt();
		
		subs.remove(i);
		threads.remove(i);
		
		SubscriptionVO.deleteSubscription(subscription_id);
	}
	
	public void forceTerminate(){
		subs.clear();
		for(Thread t : threads){
			t.interrupt();
		}
	}
}
