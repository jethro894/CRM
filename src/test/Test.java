package test;

import VO.*;
import subscription.SubscriptionManager;
import subscription.SubscriptionVO;

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
		c.setAgentID(new IDVO("1"));
		c.setEmail(new EmailVO("jethro@live.cn"));
		c.setName(nvo);
		c.setPhoneNo(new PhoneVO("67014830"));
		c.saveCustomer();
		
	}

}
