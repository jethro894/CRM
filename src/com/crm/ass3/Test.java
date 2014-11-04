package com.crm.ass3;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stubt
		AddressVO avo = new AddressVO("122 la salle","apt 9","new york","ny","10027","us");
		CustomerParams cp = new CustomerParams("target");
		cp.setAddress(avo);
		//AgentVO.updateCustomer(cp);
		
	}

}
