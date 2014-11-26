package servlet;
import java.util.List;

import javax.ws.rs.*;

import org.json.*;

import interlevel.*;
import subscription.*;
import VO.*;

@Path("/Agent")
public class Agent {
	
	
	@Path("/AgentLogin")
	@GET
	public javax.ws.rs.core.Response AgentLogin(@QueryParam("agentID") String agentid) throws JSONException{
		System.out.print(agentid);
		boolean ifexist=CAgent.verifyAgent(agentid);
		JSONObject success=new JSONObject();
		success.put("success",ifexist);
		System.out.print("sdfsdfsdfsf");
		return javax.ws.rs.core.Response.ok().entity(success).build();
		
	}
	
	
	
	@Path("/RetrieveCustomer")
	@GET
	public javax.ws.rs.core.Response RetrieveCustomer(@QueryParam("userID") String customerid) throws JSONException{
		System.out.print(customerid+"\n");
		JSONObject customer =CCustomer.getCustomerByID(customerid);
		//test///
		/*
				JSONObject customer=new JSONObject();
				JSONObject customerAddress=new JSONObject();
				customer.put("customerID", "fage");
				customer.put("agentID", "sb");
				customer.put("firstName", "fage");
				customer.put("lastName", "fage");
				customer.put("customerEmail", "fage");
				customer.put("customerPhone", "fage");
				customerAddress.put("AddressLine1", "fage");
				customerAddress.put("AddressLine2", "fage");
				customerAddress.put("City", "fage");
				customerAddress.put("State", "fage");
				customerAddress.put("Country", "fage");
				customerAddress.put("ZipCode", "fage");
				customer.put("customerAddress", customerAddress);
				
				JSONObject customer2=new JSONObject();
				//JSONObject customerAddress2=new JSONObject();
				customer2.put("customerID", "wqf");
				customer2.put("agentID", "wqf");
				customer2.put("firstName", "wqf");
				customer2.put("lastName", "wqf");
				customer2.put("customerEmail", "wqf");
				customer2.put("customerPhone", "wqf");
				//customerAddress.put("AddressLine1", "fage");
				//customerAddress.put("AddressLine2", "fage");
				//customerAddress.put("City", "fage");
				//customerAddress.put("State", "fage");
				//customerAddress.put("Country", "fage");
				//customerAddress.put("ZipCode", "fage");
				customer2.put("customerAddress", customerAddress);
			*/	
				JSONArray customers=new JSONArray();
				
				
				customers.put(customer);
				//customers.put(customer2);
				//customers.put(0, customer);
				//System.out.print(customers.getJSONObject(0));
				//System.out.print('\n');
				//System.out.print(customers.getJSONObject(1));
				//System.out.print('\n');
				
				
	
		//test///
		return javax.ws.rs.core.Response.ok().entity(customers.toString()).build();
	}
	
	
	
	@Path("/CreateCustomer")
	@POST
	public javax.ws.rs.core.Response CreateCustomer(String customer) throws JSONException{
		System.out.print(customer+"\n");
		JSONObject newcustomer=new JSONObject(customer);
		String customerid=CCustomer.createCustomer(newcustomer);
		//String customerid="fageshisbsbsb";
		//test//
		return javax.ws.rs.core.Response.ok().entity(customerid).build();
	}
	
	@Path("/UpdateCustomer")
	@POST
	public javax.ws.rs.core.Response UpdateCustomer(String customer) throws JSONException{
		System.out.print(customer+"\n");
		JSONObject newcustomer=new JSONObject(customer);
		boolean ifsuccess=CCustomer.updateCustomer(newcustomer);
		System.out.print(ifsuccess);
		JSONObject success=new JSONObject();
		success.put("success", ifsuccess);
		return javax.ws.rs.core.Response.ok().entity(success).build();
	}
	
	
	@Path("/DeleteCustomer")
	@GET
	public javax.ws.rs.core.Response DeleteCustomer(@QueryParam("userID") String customerid) throws JSONException{
		System.out.print(customerid+"\n");
		boolean success=CCustomer.deleteCustomer(customerid);
		JSONObject sb=new JSONObject();
		sb.put("success",success);
		return javax.ws.rs.core.Response.ok().entity(sb).build();
	}
	
	
	
	@Path("/RetrieveRecord")
	@GET
	public javax.ws.rs.core.Response RetrieveRecord(@QueryParam("recordID") String recordid) throws JSONException{
		System.out.print(recordid+"\n");
		JSONObject recordresult=CRecord.getRecordByID(recordid);
		/*
		JSONObject result=new JSONObject();
		recordresult.put("customerID", "sb");
		recordresult.put("contactType", "phone");
		recordresult.put("contactData", "hehe");
		recordresult.put("TextSummary", "hehehe");
		recordresult.put("RecordID", "fage");
		recordresult.put("RecordTime", "shabi");
		recordresult.put("agentID", "fage");
		
		if (recordid.equals(recordresult.getString("RecordID")))
			result=recordresult;
		else
			result=null;
			*/
		return javax.ws.rs.core.Response.ok().entity(recordresult).build();
	}
	
	
	
	@Path("/DeleteRecord")
	@GET
	public javax.ws.rs.core.Response DeleteRecord(@QueryParam("recordID") String recordid) throws JSONException{
		System.out.print(recordid+"\n");
		boolean ifsuccess=CRecord.deleteRecord(recordid);
		JSONObject success=new JSONObject();
		success.put("success", ifsuccess);
		return javax.ws.rs.core.Response.ok().entity(success).build();
	}
	
	
	
	@Path("/CreateRecord")
	@POST
	public javax.ws.rs.core.Response CreateRecord(String record) throws JSONException{
		System.out.print(record+"\n");
		JSONObject newrecord=new JSONObject(record);
		/*
		String customerid="fageshabi";
		newrecord.put("customerID", customerid);
		newrecord.put("RecordID", "sbsbsb");
		newrecord.put("RecordTime", "fage");
		System.out.print(newrecord+"\n");
		*/
		JSONObject recordresult=CRecord.createRecord(newrecord);
		return javax.ws.rs.core.Response.ok().entity(recordresult).build();
	}

	
	
	@Path("/UpdateRecord")
	@POST
	public javax.ws.rs.core.Response UpdateRecord(String record) throws JSONException{
		System.out.print(record+"\n");
		JSONObject newrecord=new JSONObject(record);
		//old data
		/*
		JSONObject olddata =new JSONObject();
		olddata.put("customerID", "sb");
		olddata.put("contactType", "phone");
		olddata.put("contactData", "hehe");
		olddata.put("TextSummary", "hehehe");
		olddata.put("RecordID", "fage");
		olddata.put("RecordTime", "shabi");
		olddata.put("agentID", "fage");
		//update olddata to the new one
		olddata.put("customerID", newrecord.getString("customerID"));
		olddata.put("contactType", newrecord.getString("contactType"));
		olddata.put("contactData", newrecord.getString("contactData"));
		olddata.put("TextSummary", newrecord.getString("TextSummary"));
		//olddata.put("RecordID", newrecord.getString("RecordID"));
		//olddata.put("RecordTime", newrecord.getString("RecordTime"));
		olddata.put("agentID", newrecord.getString("agentID"));
		*/
		JSONObject recordresult=CRecord.updateRecord(newrecord);
		return javax.ws.rs.core.Response.ok().entity(recordresult).build();
	}
	
	@Path("/Subscription")
	@GET
	public void Subscription(@QueryParam("agenid") String agentid, @QueryParam("zipcode") String zipcode, @QueryParam("ContactType") String ContactType, @QueryParam("ContactInfo") String ContactInfo) throws JSONException{
		SubscriptionVO sb=new SubscriptionVO();
		sb.setContactType(new ContactTypeVO(ContactType));
		if(ContactType.equals("sms"))
			sb.setContactInfo(new PhoneVO(ContactInfo));
		else
			sb.setContactInfo(new EmailVO(ContactInfo));
		sb.setTopic(agentid, zipcode);
		
		SubscriptionManager sm = SubscriptionManager.getSubscriptionManager();
		sm.addSubscription(sb);
	}
	
	@Path("/SearchCustomerByAnyQ")
	@GET
	public javax.ws.rs.core.Response SearchCustomer(@QueryParam("query") String query, @QueryParam("limit") String limit, @QueryParam("page") String page ) throws JSONException{
		System.out.print(query+'\n');
		System.out.print("-------------");
		/*
		System.out.print("-------------");
		System.out.print(limit+'\n');
		System.out.print("-------------");
		System.out.print(page+'\n');
		System.out.print("-------------");
		*/
		//System.out.print("wqfqwfqwfqw");
		int l=Integer.parseInt(limit);
		int p=Integer.parseInt(page);
		System.out.print(l);
		System.out.print("-------------");
		System.out.print(p);
		
		int resultamount=CCustomer.checkAmount(query);
		System.out.print(resultamount);
		
		List<String> results=CCustomer.getCustomerIDByAnyQ(query,p,l);
		JSONArray hehe=new JSONArray();
		for(String id: results){
			JSONObject newresult=CCustomer.getCustomerByID(id).put("totalAmount",resultamount);
			hehe.put(newresult);
		}
		
		//test///
		/*
		JSONObject customer=new JSONObject();
		JSONObject customerAddress=new JSONObject();
		customer.put("customerID", "fage");
		customer.put("agentID", "sb");
		customer.put("firstName", "fage");
		customer.put("lastName", "fage");
		customer.put("customerEmail", "fage");
		customer.put("customerPhone", "fage");
		customerAddress.put("AddressLine1", "fage");
		customerAddress.put("AddressLine2", "fage");
		customerAddress.put("City", "fage");
		customerAddress.put("State", "fage");
		customerAddress.put("Country", "fage");
		customerAddress.put("ZipCode", "fage");
		customer.put("customerAddress", customerAddress);
		
		JSONObject customer2=new JSONObject();
		//JSONObject customerAddress2=new JSONObject();
		customer2.put("customerID", "wqf");
		customer2.put("agentID", "wqf");
		customer2.put("firstName", "wqf");
		customer2.put("lastName", "wqf");
		customer2.put("customerEmail", "wqf");
		customer2.put("customerPhone", "wqf");
		//customerAddress.put("AddressLine1", "fage");
		//customerAddress.put("AddressLine2", "fage");
		//customerAddress.put("City", "fage");
		//customerAddress.put("State", "fage");
		//customerAddress.put("Country", "fage");
		//customerAddress.put("ZipCode", "fage");
		customer2.put("customerAddress", customerAddress);
		
		JSONArray customers=new JSONArray();
		
		
		customers.put(customer);
		customers.put(customer2);
		*/
		
		return javax.ws.rs.core.Response.ok().entity(hehe.toString()).build();

	}
	
	
}
