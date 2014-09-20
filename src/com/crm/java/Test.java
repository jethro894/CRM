package com.crm.java;

import java.util.List;

public class Test {
	public static void main(String[] argv) throws Exception{
		Customer cm1 = new Customer("HangYin", "hy2368@", "3473251823");
		Customer cm2 = new Customer("Jethro", "jethro894@", "67014830");
		
		DataManipulator dm = new DataManipulator();
		dm.saveCustomerToFile(cm1);
		dm.saveCustomerToFile(cm2);
		
		List<Customer> cms = dm.readCustomersFromFile();
		
		for(int i = 0; i < cms.size(); i++){
			System.out.println("Name: " + cms.get(i).name);
			System.out.println("Email: " + cms.get(i).email);
			System.out.println("Phone: " + cms.get(i).phone);
			System.out.println();
		}
	}
}
