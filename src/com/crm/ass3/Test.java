package com.crm.ass3;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerVO cvo = new CustomerVO("jethro", "3447", "j@lc");
		System.out.print(cvo.retrieveID().getID());
	}

}
