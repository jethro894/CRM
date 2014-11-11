package com.crm.ass3.rmq;

import java.io.IOException;

public class TestEmitter {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//String[] filter = {"agent.12345", "zipcode.10027"};
		String[] filter = {"#.agent.12345.#", "#.zipcode.10027"};
		Receiver rlt = new Receiver(filter);
		Thread t = new Thread(rlt);
		t.start();
		
		Thread.sleep(2000);
		Emitter elt = new Emitter();
		elt.publish("customer.create.agent.123456.zipcode.10025", "customer 1 created!");
		elt.publish("customer.create.agent.12345.zipcode.10025", "customer 2 created!");
		elt.publish("customer.create.agent.1234.zipcode.10027", "customer 3 created!");
		elt.publish("customer.create.agent.12345.zipcode.10027", "customer 4 created!");
		elt.close();
	}

}
