package VO;

public class TextVO extends VOBase{
	public TextVO(String t){
		this.payload = t;
	}
	
	public String getText(){
		return this.payload;
	}
}