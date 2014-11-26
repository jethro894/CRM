package VO;

import java.util.Calendar;

public class TimeVO extends VOBase{
	public TimeVO(){
		Calendar c = Calendar.getInstance();
		this.payload = c.getTime().toString();
	}
	
	public TimeVO(String time){
		this.payload = time;
	}
	
	public String getTime(){
		return this.payload;
	}
}
