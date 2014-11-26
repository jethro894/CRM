package VO;

import java.util.UUID;

public class IDVO extends VOBase{
	public IDVO(String i){
		this.payload = i;
	}
	
	public IDVO(){
		this.payload = UUID.randomUUID().toString();
	}
	
	public String getID(){
		return this.payload;
	}
}
