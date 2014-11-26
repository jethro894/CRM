package VO;

import java.util.HashMap;
import java.util.List;

import org.json.*;

import DBAPI.*;


public class AgentVO extends VOBase{
	protected final String[] default_facet = {"ID", "Name", "Email", "PhoneNo", "Address"};
	
	public IDVO retrieveID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	public NameVO retrieveName(){
		return (NameVO)this.retrieveFacet(default_facet[1]);
	}
	
	public EmailVO retrieveEmail(){
		return (EmailVO)this.retrieveFacet(default_facet[2]);
	}
	
	public PhoneVO retrievePhone(){
		return (PhoneVO)this.retrieveFacet(default_facet[3]);
	}
	
	public AddressVO retrieveAddress(){
		return (AddressVO) this.retrieveFacet(default_facet[4]);
	}
	
	private void setID(String id){
		IDVO iv = new IDVO(id);
		this.createFacet(default_facet[0], (VOBase)iv);
	}
	
	public void setName(NameVO n){ this.createFacet(default_facet[1], (VOBase)n);}
	
	public void setEmail(EmailVO e){ this.createFacet(default_facet[2], (VOBase)e);}
	
	public void setAddress(AddressVO a){ this.createFacet(default_facet[4], (VOBase)a);}
	
	public void setPhoneNo(PhoneVO p){ this.createFacet(default_facet[3], (VOBase)p);}
	
	public static AgentVO retrieveAgent(String agentID){
		return AgentDBAPI.retrieveAgent(agentID);
	}
	
	protected List<String> lookup(String key){
		//String agentID = this.retrieveID().getID();
		return AgentDBAPI.lookupAgent(key);
	}
	
/*	public List<VOBase> ambiguousLookupResult(String key){
		List<VOBase> candidateVOs = new ArrayList<VOBase>();
		for(String id: this.lookup(key)){
			candidateVOs.add(this.getCustomer(id));
		}
		return candidateVOs;
	}
*/
	
/*	public CustomerVO getCustomer(String customerID){
		return CustomerDBAPI.retrieveCustomer(customerID);
	}
*/
	
	public static boolean verifyAgent(String AgentID){
		return AgentDBAPI.verifyAgent(AgentID);
	}
	
	//public static boolean searchAgent(String AgentID){
	//	return AgentDBAPI.searchAgent(AgentID);
	//}
	
/*	public RecordVO getRecord(String recordID){
		return RecordDBAPI.retrieveRecord(recordID);
	}
*/
	
	public AgentVO(String id){
		this.myFacets = new HashMap<String , VOBase>();
		this.setID(id);
	}
	
	public JSONObject ToJson() throws JSONException{
		JSONObject jsonObject = new JSONObject();
		//ID attribute
		jsonObject.put("agentID", this.retrieveID().getID());
		//name attribute
		JSONObject nameObject = new JSONObject();
		nameObject.put("firstName", this.retrieveName().firstname);
		nameObject.put("middleName", this.retrieveName().middlename);
		nameObject.put("lastName", this.retrieveName().lastname);
		jsonObject.put("agentName", nameObject);
		//email attribute
		jsonObject.put("agentEmail", this.retrieveEmail().getEmail());
		//Phone No
		jsonObject.put("agentPhone", this.retrievePhone().payload);
		//Address attribute
		JSONObject addressObject = new JSONObject();
		addressObject.put("AddressLine1", this.retrieveAddress().AddressLine1);
		addressObject.put("AddressLine2", this.retrieveAddress().AddressLine2);
		addressObject.put("City", this.retrieveAddress().City);
		addressObject.put("Country", this.retrieveAddress().Country);
		addressObject.put("State", this.retrieveAddress().State);
		addressObject.put("ZipCode", this.retrieveAddress().Zip);
		jsonObject.put("agentAddress", addressObject);
		return jsonObject;
		
	}
}
