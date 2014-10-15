package com.crm.ass3;

public class NameVO extends VOBase{
	protected String firstname, middlename, lastname; 
	
	public NameVO(String n){
		this.payload = n;
	}
	
	public NameVO(String fn, String mn, String ln){
		this.firstname = fn;
		this.lastname = ln;
		this.middlename = mn;
		this.payload = this.firstname + " " + this.middlename + " " + this.lastname;
	}
	public String getName(){
		return this.payload;
	}
}
