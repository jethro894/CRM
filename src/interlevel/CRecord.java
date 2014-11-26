package interlevel;

import org.json.*;


import VO.*;

public class CRecord {
	
	public static JSONObject getRecordByID(String ID) throws JSONException{
		//加上record 的agent id 的match验证？？
		RecordVO record = RecordVO.getRecord(ID);
		if(record!=null)
			return record.ToJson();
		else
			return null;
	}
	
	public static JSONObject updateRecord(JSONObject record) throws JSONException{
		boolean success=false;
		//可以在这里加更新之前的验证，暂时不动
		//新建attributeVO
		ContactTypeVO contacttype=new ContactTypeVO(record.getString("contactType"));
		ContactDataVO contactdata=new ContactDataVO(record.getString("contactData"));
		TextVO textsummary=new TextVO(record.getString("TextSummary"));
		IDVO agentid=new IDVO(record.getString("agentID"));
		IDVO customerid=new IDVO(record.getString("customerID"));
		String recordid=record.getString("RecordID");
		//新建recordVO
		RecordVO recordvo=new RecordVO(recordid);//继承原id，新生成修改时间
		recordvo.setAgentID(agentid);
		recordvo.setCustomerID(customerid);
		recordvo.setContactData(contactdata);
		recordvo.setContactType(contacttype);
		recordvo.setTextSummary(textsummary);
		System.out.print(recordvo);
		success= recordvo.updateSelf();
		if (success)
			return recordvo.ToJson();
		else
			return null;
	}

	public static JSONObject createRecord(JSONObject record) throws JSONException{
		
		ContactTypeVO contacttype=new ContactTypeVO(record.getString("contactType"));
		ContactDataVO contactdata=new ContactDataVO(record.getString("contactData"));
		TextVO textsummary=new TextVO(record.getString("TextSummary"));
		//IDVO agentid=new IDVO(record.getString("agentID"));
		IDVO customerid=new IDVO(record.getString("customerID"));
		//新建record
		RecordVO recordvo=new RecordVO();
		recordvo.setCustomerID(customerid);
		recordvo.setContactData(contactdata);
		recordvo.setContactType(contacttype);
		recordvo.setTextSummary(textsummary);
		boolean success=recordvo.createRecord();
		if (success)
			return recordvo.ToJson();
		else
			return null;
	}
	
	public static boolean deleteRecord(String recordid){
		return RecordVO.deleteRecord(recordid);
		
	}
}
