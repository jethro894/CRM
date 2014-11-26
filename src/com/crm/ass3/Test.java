package com.crm.ass3;

import com.crm.ass3.subscription.SubscriptionManager;
import com.crm.ass3.subscription.SubscriptionVO;

public class Test {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method
		
		
		AddressVO avo = new AddressVO("122 la salle","apt 9","new york","ny","us","10025");
		NameVO nvo = new NameVO("hang", "jethro", "yin");
		CustomerVO c = new CustomerVO();
		c.setAddress(avo);
		c.setAgentID(new IDVO("agent001"));
		c.setEmail(new EmailVO("jethro@live.cn"));
		c.setName(nvo);
		c.setPhoneNo(new PhoneVO("67014830"));
		
		c.saveCustomer();
		
	}

}
