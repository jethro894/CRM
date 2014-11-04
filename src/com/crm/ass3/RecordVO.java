package com.crm.ass3;

import java.util.HashMap;

import net.sf.json.JSONObject;

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
	
	//创建record甄1�7
	public RecordVO(){
		this.myFacets = new HashMap<String , VOBase>();
		this.initiateID();
		this.initiateTime();
	}
	
	//更新record甄1�7
	public RecordVO(String recordid){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO recordID=new IDVO(recordid);
		this.createFacet(default_facet[5], recordID);
		this.initiateTime();//修改时间
	}
	
	//更新record
	public boolean updateSelf(){
		return RecordDBAPI.updateRecord(this);
	}
	
	//创建record,如果成功，则返回recordid，如果失败，则返回null
	public boolean createRecord(){
		return RecordDBAPI.saveRecord(this);
		
	}
	
	public static RecordVO getRecord(String recordID){
		return RecordDBAPI.retrieveRecord(recordID);
	}
	
	public static boolean deleteRecord(String recordID){
		return RecordDBAPI.deleteRecord(recordID);		
	}
	
	public JSONObject ToJson(){
		JSONObject jsonObject = new JSONObject();
		//CustomerID attribute
		jsonObject.put("customerID", this.retrieveCustomerID().getID());
		//AgentID attribute
		jsonObject.put("agentID", this.retrieveAgentID().getID());
		//Type attribute
		jsonObject.put("contactType", this.getRecordDataType().getContactType());
		//data attribute
		jsonObject.put("contactData", this.getRecordData().getContactData());
		//TextSummary
		jsonObject.put("TextSummary", this.getTextSummary().getText());
		//RecordID
		jsonObject.put("RecordID", this.retrieveRecordID().getID());
		//Time attribute
		jsonObject.put("RecordTime", this.retrieveRecordTime().getTime());

		return jsonObject;
		
	}
}
