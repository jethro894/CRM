package com.crm.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DataManipulator {
	public DataManipulator(){}
	
	public void saveCustomerToFile (Customer cm) throws Exception{

		File file = new File("customer.txt");
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        
        String data = cm.name + ";" + cm.email + ";" + cm.phone + ";" + cm.uuid ;
        bufferWritter.write(data);
        bufferWritter.newLine();
        bufferWritter.flush();
        bufferWritter.close();
	}
	
	public void saveAgentToFile (Agent ag) throws Exception{

		File file = new File("agent.txt");
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        
        String data = ag.name + ";" + ag.email + ";" + ag.phone + ";" + ag.uuid;
        bufferWritter.write(data);
        bufferWritter.newLine();
        bufferWritter.flush();
        bufferWritter.close();
	}
	
	public List<Customer> readCustomersFromFile() throws Exception{
		File file = new File("customer.txt");
		if(!file.exists())
			return null;
		
		List<Customer> cms = new ArrayList<Customer>();
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		String line = null;
		
		while((line = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line, ";");
			Customer newCustomer  = new Customer(st.nextToken(), st.nextToken(), st.nextToken());
			cms.add(newCustomer);
		}
		
		br.close();
		return cms;
	}
	
	public List<Agent> readAgentsFromFile() throws Exception{
		File file = new File("agent.txt");
		if(!file.exists())
			return null;
		
		List<Agent> ags = new ArrayList<Agent>();
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		String line = null;
		
		while((line = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line, ";");
			Agent newCustomer  = new Agent(st.nextToken(), st.nextToken(), st.nextToken());
			ags.add(newCustomer);
		}
		br.close();
		return ags;
	}
	
	public List<Customer> retrieveCustomers(String name) throws Exception{
		List<Customer> ctms = readCustomersFromFile();
		List<Customer> hits = new ArrayList<Customer>();
		for(int i = 0; i < ctms.size(); i++){
			Customer c = ctms.get(i);
			if(c.name.equals(name))
				hits.add(c);
		}
		return hits;	
	}
	
	public List<Agent> retrieveAgents(String name) throws Exception{
		List<Agent> ags = readAgentsFromFile();
		List<Agent> hits = new ArrayList<Agent>();
		for(int i = 0; i < ags.size(); i++){
			Agent c = ags.get(i);
			if(c.name.equals(name))
				hits.add(c);
		}
		return hits;	
	}
	
	
}
