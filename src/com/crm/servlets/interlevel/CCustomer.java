package com.crm.servlets.interlevel;
import net.sf.json.JSONObject;

import com.crm.ass3.*;

public class CCustomer {
	
	//调用customervo的数据库查询函数并且转换成json
	public static JSONObject getCustomerByID(String ID){
		CustomerVO customer = CustomerVO.retrieveCustomerVO(ID);
		//verify agent match the customer record
		//if(customer.getString("agentID")==agentid)
		if (customer!=null)
		return customer.ToJson();
		else
			return null;
	}
	
	//更新customer之前先验证agentid是否存在,是否和customer的agentid符合，�1�7�虑到是先搜索再更新，所以无霄1�7验证customer
	public static boolean updateCustomer(JSONObject customer){
		boolean success=false;
		//1.verify if new agent exist 畄1�7
		//2.verify if original agent match the customer record:
		//搜索customerid对应的agentid
		//CustomerVO customervo= CustomerVO.retrieveCustomerVO(customer.getString("id"));
		//String agentid=customervo.retrieveAgentID().getPayload();
		//if(customer.getString("agentID")==agentid){
			//新建 attribute
			NameVO name=new NameVO(customer.getString("fName"),"null",customer.getString("lName"));
			IDVO customerid=new IDVO(customer.getString("customerID"));
			IDVO agentID = new IDVO(customer.getString("agentID"));
			EmailVO email=new EmailVO(customer.getString("email"));
			PhoneVO phone=new PhoneVO(customer.getString("tel"));
			AddressVO address =new AddressVO(customer.getString("addressLine1"),
					customer.getString("addressLine2"),
					customer.getString("city"),
					customer.getString("state"),
					customer.getString("country"),
					customer.getString("zipCode"));
			//database update api
			CustomerVO customervo= CustomerVO.retrieveCustomerVO(customer.getString("id"));
			//except customerid doesn't change
			customervo.setAgentID(agentID);
			customervo.setEmail(email);
			customervo.setName(name);
			customervo.setPhoneNo(phone);
			customervo.setAddress(address);
			success =customervo.updateSelf();
			//}
		return success;
		
	}
	
	//create customer前需要先验证
	public static String createCustomer(JSONObject customer){
		//新建丄1�7个customervo，自动生成id
		CustomerVO customervo=new CustomerVO();
		//验证该customer是否存在
		String customerresult = "null";
		//if(customervo.verifyCustomer())
		if(true)
			customerresult = "null";
			//customerresult="customer already exist";
		//验证agent是否存在;
		else if(AgentVO.verifyAgent(customer.getString("agentID"))){
			//调用自动生成的id
			//CustomerVO customervo=new CustomerVO();
			//暂时没有middlename
			NameVO name=new NameVO(customer.getString("fName"),"null",customer.getString("lName"));
			IDVO agentID = new IDVO(customer.getString("agentID"));
			EmailVO email=new EmailVO(customer.getString("email"));
			PhoneVO phone=new PhoneVO(customer.getString("tel"));
			AddressVO address =new AddressVO(customer.getString("addressLine1"),
					customer.getString("addressLine2"),
					customer.getString("city"),
					customer.getString("state"),
					customer.getString("country"),
					customer.getString("zipCode"));
			
			//是否霄1�7要存丄1�7个之前的旧�1�7�，然后比较之后再更新？
			//customervo object attribute update
			//customervo.setAddress(a);
			customervo.setAgentID(agentID);
			customervo.setEmail(email);
			customervo.setName(name);
			customervo.setPhoneNo(phone);
			customervo.setAddress(address);
			//database create api到底是调甄1�7
			customerresult=customervo.saveCustomer();
			}
		else
			customerresult = "null";
			//customerresult="agent doesn't exist";
		return customerresult;
	}
	
	public static boolean deleteCustomer(String customerid){
		return CustomerVO.deleteCustomer(customerid);
	}
	

}
