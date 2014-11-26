package VO;

public class VoiceVO extends VOBase{
	public VoiceVO(Object t){
		this.payload = t.toString();
	}
	
	public String getVoice(){
		return this.payload;
	}
	
}
