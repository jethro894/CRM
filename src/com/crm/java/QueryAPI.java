package com.crm.java;

import java.util.List;

public class QueryAPI {
	
	public QueryAPI(){}
	
	public void createCustomer(String nm, String em, String ph) throws Exception{
		Customer cm = new Customer(nm,em,ph);
		DataManipulator dm = new DataManipulator();
		dm.saveCustomerToFile(cm);
	}
	
	public void createAgent(String nm, String em, String ph) throws Exception{
		Agent cm = new Agent(nm,em,ph);
		DataManipulator dm = new DataManipulator();
		dm.saveAgentToFile(cm);
	}
	
	public void retrieveCustomer(String nm) throws Exception{
		DataManipulator dm = new DataManipulator();
		List<Customer> ctms = dm.retrieveCustomers(nm);
		/*
		 * 
		 */
	}
	
	public void retrieveAgent(String nm) throws Exception{
		DataManipulator dm = new DataManipulator();
		List<Agent> ags = dm.retrieveAgents(nm);
		/*
		 * 
		 */
	}
}
