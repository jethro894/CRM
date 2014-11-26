package interlevel;
import java.util.List;

import VO.*;


import org.json.*;

public class CCustomer {
	
	//璋冪敤customervo鐨勬暟鎹簱鏌ヨ鍑芥暟骞朵笖杞崲鎴恓son
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
	
	//鏇存柊customer涔嬪墠鍏堥獙璇乤gentid鏄惁瀛樺湪,鏄惁鍜宑ustomer鐨刟gentid绗﹀悎锛岋拷1锟�借檻鍒版槸鍏堟悳绱㈠啀鏇存柊锛屾墍浠ユ棤闇��岃瘉customer
	public static boolean updateCustomer(JSONObject customer) throws JSONException{
		boolean success=false;
		//1.verify if new agent exist
		//2.verify if original agent match the customer record:
		//鎼滅储customerid瀵瑰簲鐨刟gentid//楠岃瘉customer鐨刟gent鏄惁鏄鏃秛pdate浠栫殑agent
		CustomerVO customervo= CustomerVO.retrieveCustomerVO(customer.getString("customerID"));
		String agentid=customervo.retrieveAgentID().getPayload();
		System.out.print("fageshishabi");
		if(customer.getString("loginAgentID").equals(agentid)||customer.getString("loginAgentID").equals("")){
			System.out.print("wangwangwang");
			//鎶婁紶鍏ョ殑json鍖呰鎴恈ustomervo
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
	
	//create customer鍓嶉渶瑕佸厛楠岃瘉
	public static String createCustomer(JSONObject customer) throws JSONException{
		//鏂板缓涓��猚ustomervo锛岃嚜鍔ㄧ敓鎴恑d
		CustomerVO customervo=new CustomerVO();
		System.out.print("New customerID is "+customervo.retrieveID().payload);
		//楠岃瘉璇ustomer鏄惁瀛樺湪
		String customerresult = "null";
	//	if(customervo.verifyCustomer())
			customerresult = "null";
			//customerresult="customer already exist";
		//楠岃瘉agent鏄惁瀛樺湪;
		if(AgentVO.verifyAgent(customer.getString("agentID"))){
			//璋冪敤鑷姩鐢熸垚鐨刬d
			//CustomerVO customervo=new CustomerVO();
			//鏆傛椂娌℃湁middlename
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
			
			//鏄惁闇��佸瓨涓��箣鍓嶇殑鏃э拷1锟�斤紝鐒跺悗姣旇緝涔嬪悗鍐嶆洿鏂帮紵
			//customervo object attribute update
			//customervo.setAddress(a);
			customervo.setAgentID(agentID);
			customervo.setEmail(email);
			customervo.setName(name);
			customervo.setPhoneNo(phone);
			customervo.setAddress(address);
			//database create api鍒板簳鏄皟鐢��			customerresult=customervo.saveCustomer();
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
