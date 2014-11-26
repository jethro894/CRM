package VO;

public class EmailVO extends VOBase{
	public EmailVO(String e){
		this.payload = e;
	}
	
	public String getEmail(){
		return this.payload;
	}
}
