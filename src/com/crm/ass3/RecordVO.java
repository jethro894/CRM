package com.crm.ass3;

import java.util.HashMap;

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

	public void setCustomerID(IDVO i){ this.createFacet(default_facet[0], (VOBase)i);}
	
	public void setAgentID(IDVO i){ this.createFacet(default_facet[1], (VOBase)i);}
	
	public void setContactType(ContactTypeVO ct){ this.createFacet(default_facet[2], (VOBase)ct);}
	
	public void setContactData(ContactDataVO cv){ this.createFacet(default_facet[3], (VOBase)cv);}
	
	public void setTextSummary(TextVO t){ this.createFacet(default_facet[4], (VOBase)t);}
	
	
	public RecordVO(String customerID, String agentID, boolean isVoice, Object data, String textSummary){
		
	}
	
	public RecordVO(){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO iv = new IDVO();
		TimeVO t = new TimeVO();
		this.createFacet(default_facet[5], (VOBase)iv);
		this.createFacet(default_facet[6], (VOBase)t);
	}
}
