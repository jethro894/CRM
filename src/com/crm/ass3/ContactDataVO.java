package com.crm.ass3;

public class ContactDataVO extends VOBase{
	public ContactDataVO(String d){
		/*if(ct.isVoice()){}
		else{}*/
		this.payload = d;
	}
	
	public String getContactData(){
		return this.payload;
	}
}
