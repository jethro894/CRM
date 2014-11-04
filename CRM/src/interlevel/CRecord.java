package interlevel;

import net.sf.json.JSONObject;

import com.crm.ass3.ContactDataVO;
import com.crm.ass3.ContactTypeVO;
import com.crm.ass3.CustomerVO;
import com.crm.ass3.IDVO;
import com.crm.ass3.RecordVO;
import com.crm.ass3.TextVO;

public class CRecord {
	
	public static JSONObject getRecordByID(String ID){
		//加上record 的agent id 的match验证？？
		RecordVO record = RecordVO.getRecord(ID);
		if(record!=null)
			return record.ToJson();
		else
			return null;
	}
	
	public static JSONObject updateRecord(JSONObject record){
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
		success= recordvo.updateself();
		if (success)
			return recordvo.ToJson();
		else
			return null;
	}

	public static JSONObject createRecord(JSONObject record){
		
		//验证record是否存在,是否重复,agentid customerid是否存在,暂时不用
		ContactTypeVO contacttype=new ContactTypeVO(record.getString("contactType"));
		ContactDataVO contactdata=new ContactDataVO(record.getString("contactData"));
		TextVO textsummary=new TextVO(record.getString("TextSummary"));
		IDVO agentid=new IDVO(record.getString("agentID"));
		IDVO customerid=new IDVO(record.getString("customerID"));
		//新建record
		RecordVO recordvo=new RecordVO();//自动生成id和最新时间
		recordvo.setAgentID(agentid);
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
