package com.crm.java;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person{
	
	List<Agent> agents;
	
	public Manager(){
		super();
	}
	
	public Manager(String nm, String em, String ph){
		super(nm,em,ph);
		agents = new ArrayList<Agent>();
	}
}
