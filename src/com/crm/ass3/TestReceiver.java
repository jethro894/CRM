package com.crm.ass3;

import com.crm.ass3.subscription.SubscriptionManager;
import com.crm.ass3.subscription.SubscriptionVO;

public class TestReceiver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubscriptionManager submanager = SubscriptionManager.getSubscriptionManager();
		SubscriptionVO new_subscription = new SubscriptionVO();
		new_subscription.setContactType(new ContactTypeVO("email"));
		new_subscription.setContactInfo(new EmailVO("email@gmail.com"));
		new_subscription.setTopic("agent001", "10027");
		submanager.addSubscription(new_subscription);
		
		SubscriptionVO new_subscription2 = new SubscriptionVO();
		new_subscription2.setContactType(new ContactTypeVO("email"));
		new_subscription2.setContactInfo(new EmailVO("email@gmail.com"));
		new_subscription2.setTopic("agent002", "10025");
		
		submanager.addSubscription(new_subscription2);
	}

}
