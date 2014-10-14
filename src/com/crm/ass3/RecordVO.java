package com.crm.ass3;

public class RecordVO extends VOBase{
	protected final String[] default_facet = {"CustomerID", "AgentID", "Type", "Data", "TextSummary", "RecordID", "Time"};
	
	public IDVO retriveCustomerID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	public IDVO retriveAgentID(){
		return (IDVO)this.retrieveFacet(default_facet[1]);
	}
	
	public ContactTypeVO getRecordDataType(){
		return (ContactTypeVO)this.retrieveFacet(default_facet[2]);
	}
	
	public ContactDataVO getRecordData(){
		return (ContactDataVO)this.retrieveFacet(default_facet[3]);
	}
	
	public TextVO getTextSummary(){
		return (TextVO)this.retrieveFacet(default_facet[4]);
	}
	
	public IDVO retriveRecordID(){
		return (IDVO)this.retrieveFacet(default_facet[5]);
	}
	
	public TimeVO retriveRecordTime(){
		return (TimeVO)this.retrieveFacet(default_facet[6]);
	}

	
	public RecordVO(String customerID, String agentID, boolean isVoice, Object data, String textSummary){
		
	}
	
	public RecordVO(){
		
	}
}
