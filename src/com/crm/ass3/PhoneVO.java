package com.crm.ass3;

public class PhoneVO extends VOBase{
	public PhoneVO(String p){
		this.payload = p;
	}
	
	public String getPhoneNumber(){
		return this.payload;
	}
}
