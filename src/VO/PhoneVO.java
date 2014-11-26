package VO;

public class PhoneVO extends VOBase{
	public PhoneVO(String p){
		this.payload = p;
	}
	
	public String getPhoneNumber(){
		return this.payload;
	}
}
