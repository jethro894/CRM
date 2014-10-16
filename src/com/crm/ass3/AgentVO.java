package com.crm.ass3;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
	
	public void setName(NameVO n){ this.createFacet(default_facet[1], (VOBase)n);}
	
	public void setEmail(EmailVO e){ this.createFacet(default_facet[2], (VOBase)e);}
	
	public void setAddress(AddressVO a){ this.createFacet(default_facet[4], (VOBase)a);}
	
	public void setPhoneNo(PhoneVO p){ this.createFacet(default_facet[3], (VOBase)p);}
	
	public static AgentVO retrieveAgent(String agentID){
		return AgentDBAPI.retrieveAgent(agentID);
	}
	
	protected List<String> lookup(String agentID, String key){
		List<String> candidateIDs = null;
		///
		return candidateIDs;
	}
	
	public void updateCustomer(CustomerParams cp){
		CustomerDBAPI.updateCustomer((CustomerVO)cp);
	}
	
	public void updateRecord(RecordParams rp){
		RecordDBAPI.updateRecord((RecordVO)rp);
	}
	
	public CustomerVO createCustomer() throws IOException{
		CustomerVO cv = null;
		///
		//CustomerDBAPI.saveCustomer(cv);
		return cv;
	}
	
	public RecordVO createRecord() throws IOException{
		RecordVO rv = null;
		///
		return rv;
	}
	
	public CustomerVO getCustomer(String customerID){
		return CustomerDBAPI.retrieveCustomer(customerID);
	}
	
	public RecordVO getRecord(String recordID){
		return RecordDBAPI.retrieveRecord(recordID);
	}
	
	public AgentVO(String id, String name, String email, String phone){
		this.myFacets = new HashMap<String , VOBase>();
		
		IDVO iv = new IDVO(id);
		NameVO nv = new NameVO(name);
		EmailVO ev = new EmailVO(email);
		PhoneVO pv = new PhoneVO(phone);
		
		this.createFacet(default_facet[0], (VOBase)iv);
		this.createFacet(default_facet[1], (VOBase)nv);
		this.createFacet(default_facet[2], (VOBase)ev);
		this.createFacet(default_facet[3], (VOBase)pv);
	}
	
	public AgentVO(String id){
		this.myFacets = new HashMap<String , VOBase>();
		
		IDVO iv = new IDVO(id);
		this.createFacet(default_facet[0], (VOBase)iv);
	}
}
