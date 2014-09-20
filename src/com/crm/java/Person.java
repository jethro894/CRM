package com.crm.java;

import java.util.UUID;

public class Person {
	String uuid;
	String name;
	String email;
	String phone;
	
	public Person(){this.uuid = UUID.randomUUID().toString();}
	
	public Person(String nm, String em, String ph){
		this.uuid = UUID.randomUUID().toString();
		this.name = nm;
		this.email = em;
		this.phone = ph;
	}
}
