package com.crm.ass3;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddressVO avo = new AddressVO("122 la salle","apt 9","new york","ny","10027","us");
		CustomerVO cvo = new CustomerVO("jethro", "3447", "j@lc", "agent k", avo);
		System.out.print(cvo.retrieveAddress().getAddress());
		
	}

}
