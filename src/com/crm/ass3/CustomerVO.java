package com.crm.ass3;

import java.util.HashMap;
import net.sf.json.JSONObject;


public class CustomerVO extends VOBase{
	protected final String[] default_facet = {"ID", "Name", "Email", "PhoneNo", "AgentID", "Address"};
	
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
	
	public IDVO retrieveAgentID(){
		return (IDVO)this.retrieveFacet(default_facet[4]);
	}
	
	public AddressVO retrieveAddress(){
		return (AddressVO) this.retrieveFacet(default_facet[5]);
	}
	
	private void initiateID(){
		IDVO iv = new IDVO();
		this.createFacet(default_facet[0], (VOBase)iv);
	}
	
	public void setName(NameVO n){ this.createFacet(default_facet[1], (VOBase)n);}
	
	public void setEmail(EmailVO e){ this.createFacet(default_facet[2], (VOBase)e);}
	
	public void setAddress(AddressVO a){ this.createFacet(default_facet[5], (VOBase)a);}
	
	public void setPhoneNo(PhoneVO p){ this.createFacet(default_facet[3], (VOBase)p);}
	
	public void setAgentID(IDVO aid){ this.createFacet(default_facet[4], (VOBase)aid);}
	
	public void setAgentID(String agentID){
		IDVO av = new IDVO(agentID);
		this.createFacet(default_facet[4], (VOBase)av);
	}
	
	//用于新生成一个customervo的object，自动产生id
	public CustomerVO(){
		this.myFacets = new HashMap<String , VOBase>();
		this.initiateID();
	}
	
	//用已知id生成丄1�7个customer object，用于更斄1�7
	public CustomerVO(String id){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO customerID=new IDVO(id);
		this.createFacet(default_facet[0], customerID);
	}
	
	//在database中创造一个customer，并且返回success/error
	public String saveCustomer(){
		if(CustomerDBAPI.saveCustomer(this))
			return this.retrieveID().payload;
		else
			return "null";
	}
	
	public static CustomerVO retrieveCustomerVO(String customerID){
		return CustomerDBAPI.retrieveCustomer(customerID);
	}
	
	public boolean updateSelf(){
		return CustomerDBAPI.updateCustomer(this);
	}
	
/*	public boolean verifyCustomer(){
		String id=this.retrieveID().payload;
		return CustomerDBAPI.verifyCustomer(id);
	}
*/	
	public static boolean deleteCustomer(String id){
		return CustomerDBAPI.deleteCustomer(id);
	}
	
	public JSONObject ToJson(){
		JSONObject jsonObject = new JSONObject();
		//ID attribute
		jsonObject.put("customerID", this.retrieveID().getID());
		//name attribute
		JSONObject nameObject = new JSONObject();
		//nameObject.put("firstName", this.retrieveName().firstname);
		//nameObject.put("middleName", this.retrieveName().middlename);
		//nameObject.put("lastName", this.retrieveName().lastname);
		//jsonObject.put("customerName", nameObject);
		jsonObject.put("firstName",this.retrieveName().firstname);
		jsonObject.put("lastName",this.retrieveName().lastname);
		//email attribute
		jsonObject.put("customerEmail", this.retrieveEmail().getEmail());
		//Phone No
		jsonObject.put("customerPhone", this.retrievePhone().payload);
		//AgentID
		jsonObject.put("agentID", this.retrieveAgentID().payload);
		//Address attribute
		JSONObject addressObject = new JSONObject();
		addressObject.put("AddressLine1", this.retrieveAddress().AddressLine1);
		addressObject.put("AddressLine2", this.retrieveAddress().AddressLine2);
		addressObject.put("City", this.retrieveAddress().City);
		addressObject.put("Country", this.retrieveAddress().Country);
		addressObject.put("State", this.retrieveAddress().State);
		addressObject.put("ZipCode", this.retrieveAddress().Zip);
		jsonObject.put("customerAddress", addressObject);
		return jsonObject;
		
	}
}
