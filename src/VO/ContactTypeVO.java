package VO;

public class ContactTypeVO extends VOBase{
	public ContactTypeVO(String tp){
			this.payload = tp;
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
