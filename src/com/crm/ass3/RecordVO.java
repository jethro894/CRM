package com.crm.ass3;

import java.util.HashMap;

public class RecordVO extends VOBase{
	protected final String[] default_facet = {"CustomerID", "AgentID", "Type", "Data", "TextSummary", "RecordID", "Time"};
	
	public IDVO retrieveCustomerID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	public IDVO retrieveAgentID(){
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
	
	public IDVO retrieveRecordID(){
		return (IDVO)this.retrieveFacet(default_facet[5]);
	}
	
	public TimeVO retrieveRecordTime(){
		return (TimeVO)this.retrieveFacet(default_facet[6]);
	}
	
	private void initiateID(){
		IDVO iv = new IDVO();
		this.createFacet(default_facet[5], (VOBase)iv);
	}
	
	private void initiateTime(){
		TimeVO t = new TimeVO();
		this.createFacet(default_facet[6], (VOBase)t);
	}

	public void setCustomerID(IDVO i){ this.createFacet(default_facet[0], (VOBase)i);}
	
	public void setAgentID(IDVO i){ this.createFacet(default_facet[1], (VOBase)i);}
	
	public void setContactType(ContactTypeVO ct){ this.createFacet(default_facet[2], (VOBase)ct);}
	
	public void setContactData(ContactDataVO cv){ this.createFacet(default_facet[3], (VOBase)cv);}
	
	public void setTextSummary(TextVO t){ this.createFacet(default_facet[4], (VOBase)t);}
	
	public RecordVO(){
		this.myFacets = new HashMap<String , VOBase>();
		this.initiateID();
		this.initiateTime();
	}
	
	public RecordVO(RecordParams rp){
		this.myFacets = rp.myFacets;
		this.initiateID();
		this.initiateTime();
	}
}
