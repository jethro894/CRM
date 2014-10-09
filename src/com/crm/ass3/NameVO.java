package com.crm.ass3;

public class NameVO extends VOBase{
	
	public NameVO(String n){
		this.payload = n;
	}
	
	public String getName(){
		return this.payload;
	}
}
