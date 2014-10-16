package com.crm.ass3;

import java.util.HashMap;

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
	
	public void setName(NameVO n){ this.createFacet(default_facet[1], (VOBase)n);}
	
	public void setEmail(EmailVO e){ this.createFacet(default_facet[2], (VOBase)e);}
	
	public void setAddress(AddressVO a){ this.createFacet(default_facet[5], (VOBase)a);}
	
	public void setPhoneNo(PhoneVO p){ this.createFacet(default_facet[3], (VOBase)p);}
	
	public void setAgentID(IDVO aid){ this.createFacet(default_facet[4], (VOBase)aid);}
	
	public void setAgentID(String agentID){
		IDVO av = new IDVO(agentID);
		this.createFacet(default_facet[4], (VOBase)av);
	}
	
	/*public static List<String> ambiguousLookup(String key){
		List<String> candidateIDs = null;
		///
		return candidateIDs;
	}*/
	
	public static CustomerVO retrieveCustomerVO(String customerID){
		return CustomerDBAPI.retrieveCustomer(customerID);
	}
	
	public void updateSelf(){
		CustomerDBAPI.updateCustomer(this);
	}
	
	public CustomerVO(String name, String email, String phone, String agentID, AddressVO avo){
		this.myFacets = new HashMap<String , VOBase>();
		
		IDVO iv = new IDVO();
		NameVO nv = new NameVO(name);
		EmailVO ev = new EmailVO(email);
		PhoneVO pv = new PhoneVO(phone);
		IDVO av = new IDVO(agentID);
		
		this.createFacet(default_facet[0], (VOBase)iv);
		this.createFacet(default_facet[1], (VOBase)nv);
		this.createFacet(default_facet[2], (VOBase)ev);
		this.createFacet(default_facet[3], (VOBase)pv);
		this.createFacet(default_facet[4], (VOBase)av);
		this.createFacet(default_facet[5], (VOBase)avo);
		
	}
	
	public CustomerVO(){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO iv = new IDVO();
		this.createFacet(default_facet[0], (VOBase)iv);
	}
}
