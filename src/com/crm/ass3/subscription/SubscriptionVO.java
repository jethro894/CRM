package com.crm.ass3.subscription;

import java.io.IOException;
import java.util.HashMap;

import net.sf.json.JSONObject;

import com.crm.ass3.ContactTypeVO;
import com.crm.ass3.EmailVO;
import com.crm.ass3.IDVO;
import com.crm.ass3.PhoneVO;
import com.crm.ass3.VOBase;
import com.crm.ass3.rmq.Receiver;

public class SubscriptionVO extends VOBase implements Runnable{
	protected final String[] default_facet = {"SubscriptionID", "Type", "ContactInformation"};
	String[] topics;

	public ContactTypeVO getContactType(){
		return (ContactTypeVO)this.retrieveFacet(default_facet[1]);
	}
	
	public VOBase getContactInformation(){
		if(this.getContactType().getContactType().equals("email"))
			return (EmailVO)this.retrieveFacet(default_facet[2]);
		if (this.getContactType().getContactType().equals("sms"))
			return (PhoneVO)this.retrieveFacet(default_facet[2]);
		return null;
	}
	
	public IDVO getSubscriptionID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	private void initiateID(){
		IDVO iv = new IDVO();
		this.createFacet(default_facet[0], (VOBase)iv);
	}
	
	public void setContactType(ContactTypeVO type){this.createFacet(default_facet[1], (VOBase)type);}
	public void setContactInfo(VOBase info){this.createFacet(default_facet[2], info);}
	public void setTopic(String agentID, String zip){
		this.topics = new String[2];
		this.topics[0] = agentID;
		this.topics[1] = zip;
	}
	
	public SubscriptionVO(){
		this.myFacets = new HashMap<String , VOBase>();
		this.initiateID();
	}
	
	public SubscriptionVO(String id){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO SubscriptionID=new IDVO(id);
		this.createFacet(default_facet[0], SubscriptionID);
	}
	
	public void issueSubscription(){
		//
	}
	
	public boolean updateSelf(){
		return SubscriptionDBAPI.updateSubscription(this);
	}

	public boolean createSubscription(){
		return SubscriptionDBAPI.saveSubscription(this);
	}
	
	public static SubscriptionVO getSubscription(String SubscriptionID){
		return SubscriptionDBAPI.retrieveSubscription(SubscriptionID);
	}
	
	public static boolean deleteSubscription(String SubscriptionID){
		return SubscriptionDBAPI.deleteSubscription(SubscriptionID);		
	}
	
	public JSONObject ToJson(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("subscriptionID", this.getSubscriptionID().getID());
		jsonObject.put("contactType", this.getContactType().getContactType());
		jsonObject.put("contactInfo", this.getContactInformation().getPayload());
		return jsonObject;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(this.topics.length != 2)
			return;
		if(this.getContactType() == null || this.getContactInformation() == null || this.getSubscriptionID() == null)
			return;
		
		String[] filter = new String[2];
		filter[0] = "customer.agentid." + this.topics[0] + ".#";
		filter[1] = "#.zipcode." + this.topics[1];
		try {
			Receiver r = new Receiver(filter);
			r.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
