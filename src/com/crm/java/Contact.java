package com.crm.java;

public class Contact {
	Customer customer;
	Agent agent;
	String model;
	String data;
	String text_summary;
	
	public Contact(){}
	
	public Contact(Customer ctm, Agent ag, String md, String dt, String ts){
		this.customer = ctm;
		this.agent = ag;
		this.model = md;
		this.data = dt;
		this.text_summary = ts;
	}
	
}
