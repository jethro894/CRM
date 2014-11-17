package com.crm.ass3;

import java.util.Calendar;

public class TimeVO extends VOBase{
	public TimeVO(){
		Calendar c = Calendar.getInstance();
		this.payload = c.getTime().toString();
	}
	
	public TimeVO(String timestamp){
		this.payload = timestamp;
	}
	
	public String getTime(){
		return this.payload;
	}
}
