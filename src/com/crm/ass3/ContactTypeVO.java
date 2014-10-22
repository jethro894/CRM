package com.crm.ass3;

public class ContactTypeVO extends VOBase{
	public ContactTypeVO(boolean isVoice){
		if(isVoice == true)
			this.payload = "Voice";
		else
			this.payload = "Text";
	}
	
	public String getContactType(){
		return this.payload;
	}
	
	public boolean isVoice(){
		if(this.payload.equals("Voice"))
			return true;
		else
			return false;
	}
}
