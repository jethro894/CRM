package servlet;

import javax.ws.rs.*;
import org.json.*;
import interlevel.*;


@Path("/Customer")
public class Customer {
	@Path("/RetrieveCustomer")
	@GET
	public  javax.ws.rs.core.Response getCustomer(@QueryParam("userID") String customerid) throws JSONException{
		System.out.print(customerid+"\n");
		JSONObject customer=CCustomer.getCustomerByID(customerid);
		/*
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
		System.out.print(customer+"\n");
		*/
		//test///
		return javax.ws.rs.core.Response.ok().entity(customer).build();
	}

	
	
	@Path("/UpdateCustomer")
	@POST
	public javax.ws.rs.core.Response UpdateCustomer(String customer) throws JSONException{
		System.out.print(customer+"\n");
		JSONObject newcustomer=new JSONObject(customer);
		boolean ifsuccess= CCustomer.updateCustomer(newcustomer);
		JSONObject success=new JSONObject();
		success.put("success", ifsuccess);
		return javax.ws.rs.core.Response.ok().entity(success).build();
	}
	
	
}
