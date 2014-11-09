package com.crm.ass3.subscription;

import java.util.HashMap;

import net.sf.json.JSONObject;

import com.crm.ass3.ContactTypeVO;
import com.crm.ass3.EmailVO;
import com.crm.ass3.IDVO;
import com.crm.ass3.PhoneVO;
import com.crm.ass3.VOBase;

public class SubscriptionVO extends VOBase{
	protected final String[] default_facet = {"CustomerID", "Topic", "Type", "ContactInformation", "SubscriptionID"};
	
	public IDVO getCustomerID(){
		return (IDVO)this.retrieveFacet(default_facet[0]);
	}
	
	public TopicVO getTopic(){
		return (TopicVO)this.retrieveFacet(default_facet[1]);
	}
	
	public ContactTypeVO getContactType(){
		return (ContactTypeVO)this.retrieveFacet(default_facet[2]);
	}
	
	public VOBase getContactInformation(){
		if(this.getContactType().getContactType().equals("email"))
			return (EmailVO)this.retrieveFacet(default_facet[3]);
		if (this.getContactType().getContactType().equals("sms"))
			return (PhoneVO)this.retrieveFacet(default_facet[3]);
		return null;
	}
	
	public IDVO getSubscriptionID(){
		return (IDVO)this.retrieveFacet(default_facet[4]);
	}
	
	private void initiateID(){
		IDVO iv = new IDVO();
		this.createFacet(default_facet[4], (VOBase)iv);
	}
	
	public void setCustomerID(IDVO customer_id){this.createFacet(default_facet[0], (VOBase)customer_id);}
	
	public void setTopic(TopicVO topic){this.createFacet(default_facet[1], (VOBase)topic);}
	
	public void setContactType(ContactTypeVO type){this.createFacet(default_facet[2], (VOBase)type);}
	
	public void setContactInfo(VOBase info){this.createFacet(default_facet[3], info);}
	
	public SubscriptionVO(){
		this.myFacets = new HashMap<String , VOBase>();
		this.initiateID();
	}
	
	public SubscriptionVO(String id){
		this.myFacets = new HashMap<String , VOBase>();
		IDVO SubscriptionID=new IDVO(id);
		this.createFacet(default_facet[4], SubscriptionID);
	}
	
	public boolean issueSubscription(){
		return true;
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
		//CustomerID attribute
		jsonObject.put("customerID", this.getCustomerID().getID());
		//AgentID attribute
		jsonObject.put("topic", this.getTopic().getTopic());
		//Type attribute
		jsonObject.put("contactType", this.getContactType().getContactType());
		//data attribute
		jsonObject.put("contactInfo", this.getContactInformation().getPayload());
		//RecordID
		jsonObject.put("subscriptionID", this.getSubscriptionID().getID());


		return jsonObject;
		
	}
}
