package interlevel;
import java.util.List;

import VO.*;


import org.json.*;

public class CCustomer {
	
	//调用customervo的数据库查询函数并且转换成json
	public static JSONObject getCustomerByID(String ID) throws JSONException{
		System.out.print(ID+"\n");
		CustomerVO customer = CustomerVO.retrieveCustomerVO(ID);
		
		//verify agent match the customer record
		//if(customer.getString("agentID")==agentid)
		if (customer!=null)
		return customer.ToJson();
		else
			return null;
	}
	
	//更新customer之前先验证agentid是否存在,是否和customer的agentid符合，�1ￄ1�7�虑到是先搜索再更新，所以无霄1�7�1�7�证customer
	public static boolean updateCustomer(JSONObject customer) throws JSONException{
		boolean success=false;
		//1.verify if new agent exist
		//2.verify if original agent match the customer record:
		//搜索customerid对应的agentid//验证customer的agent是否是此时update他的agent
		CustomerVO customervo= CustomerVO.retrieveCustomerVO(customer.getString("customerID"));
		String agentid=customervo.retrieveAgentID().getPayload();
		System.out.print("fageshishabi");
		if(customer.getString("loginAgentID").equals(agentid)||customer.getString("loginAgentID").equals("")){
			System.out.print("wangwangwang");
			//把传入的json包装成customervo
			NameVO name=new NameVO(customer.getString("fName"),"null",customer.getString("lName"));
			//IDVO customerid=new IDVO(customer.getString("customerID"));
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
			//CustomerVO customervo1= CustomerVO.retrieveCustomerVO(customer.getString("id"));
			//except customerid doesn't change
			customervo.setAgentID(agentID);
			customervo.setEmail(email);
			customervo.setName(name);
			customervo.setPhoneNo(phone);
			customervo.setAddress(address);
			success =customervo.updateSelf();
			}
		return success;
		
	}
	
	//create customer前需要先验证
	public static String createCustomer(JSONObject customer) throws JSONException{
		//新建丄1�7�1�7�customervo，自动生成id
		CustomerVO customervo=new CustomerVO();
		System.out.print("New customerID is "+customervo.retrieveID().payload);
		//验证该customer是否存在
		String customerresult = "null";
	//	if(customervo.verifyCustomer())
			customerresult = "null";
			//customerresult="customer already exist";
		//验证agent是否存在;
		if(AgentVO.verifyAgent(customer.getString("agentID"))){
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
			
			//是否霄1�7�1�7�存丄1�7�1�7�之前的旧�1ￄ1�7�，然后比较之后再更新？
			//customervo object attribute update
			//customervo.setAddress(a);
			customervo.setAgentID(agentID);
			customervo.setEmail(email);
			customervo.setName(name);
			customervo.setPhoneNo(phone);
			customervo.setAddress(address);
			//database create api到底是调甄1�7�1�7			customerresult=customervo.saveCustomer();
			}
		else
			customerresult = null;
			//customerresult="agent doesn't exist";
		return customerresult;
	}
	
	public static boolean deleteCustomer(String customerid){
		return CustomerVO.deleteCustomer(customerid);
	}
	
	public static List<String> getCustomerIDByAnyQ(String query,int page,int limit) throws JSONException{
		return CustomerVO.lookup(query,page,limit);
	}
	
	public static int checkAmount(String key){
		return CustomerVO.checkCAmount(key);
	}
	

}
