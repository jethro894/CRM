package com.crm.ass3.subscription;

import com.crm.ass3.VOBase;

public class TopicVO extends VOBase{
	public TopicVO(String e){
		this.payload = e;
	}
	
	public String getTopic(){
		return this.payload;
	}
}
