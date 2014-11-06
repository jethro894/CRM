package com.crm.ass3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Test {

	private static final ResultSet Null = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//CustomerDBAPI.lookupCustomer("70");
		/*AddressVO avo = new AddressVO("122 la sasdflle","aptdsf 9","new yordfk","ndfy","10027","uds");
		NameVO nv = new NameVO("aafd","bfaf","sbaf");
		EmailVO ev = new EmailVO("sb34@sb2.com");
		PhoneVO pv = new PhoneVO("443543123");
		//IDVO aid = new IDVO("agent007");
		
		AgentVO cv = new AgentVO("9");
		AgentVO cv2 = new AgentVO("10");
		
		cv.setAddress(avo);
		//cv.setAgentID(aid);
		cv.setEmail(ev);
		cv.setName(nv);
		cv.setPhoneNo(pv);
		cv2.setAddress(avo);
		//cv.setAgentID(aid);
		cv2.setEmail(ev);
		cv2.setName(nv);
		cv2.setPhoneNo(pv);
		AgentDBAPI.saveAgent(cv);
		AgentDBAPI.saveAgent(cv2);*/
		//System.out.println(AgentDBAPI.updateAgent(cv));
		
		/*AddressVO avo = new AddressVO("122 la sasdflle","aptdsf 9","new yordfk","ndfy","10027","uds");
		NameVO nv = new NameVO("aafd","bfaf","sbaf");
		EmailVO ev = new EmailVO("sb34@sb2.com");
		PhoneVO pv = new PhoneVO("443543123");
		//IDVO aid = new IDVO("agent007");
		
		CustomerVO cv = new CustomerVO("200");
		CustomerVO cv2 = new CustomerVO("300");
		
		cv.setAddress(avo);
		//cv.setCustomerID(aid);
		cv.setEmail(ev);
		cv.setName(nv);
		cv.setPhoneNo(pv);
		cv2.setAddress(avo);
		//cv2.setCustomerID(aid);
		cv2.setEmail(ev);
		cv2.setName(nv);
		cv2.setPhoneNo(pv);
		CustomerDBAPI.saveCustomer(cv);
		CustomerDBAPI.saveCustomer(cv2);*/
		//System.out.println(AgentDBAPI.updateAgent(cv));
		
		
		/*
		RecordVO rvo = new RecordVO("4");
		RecordVO rvo2 = new RecordVO("14");
		RecordVO rvo3 = new RecordVO("15");
        IDVO av = new IDVO("1");
        IDVO cv = new IDVO("9");
        IDVO cv2 = new IDVO("200");
        IDVO cv3 = new IDVO("300");
        TextVO tv = new TextVO("totally useless");
        ContactDataVO cdv1 = new ContactDataVO("data2b");
        ContactTypeVO cdt = new ContactTypeVO("Voice");
        rvo.setCustomerID(cv);
        rvo.setAgentID(av);        
        rvo.setContactData(cdv1);
        rvo.setContactType(cdt);
        rvo.setTextSummary(tv);
        
        rvo2.setAgentID(av);
        rvo2.setCustomerID(cv2);
        rvo2.setContactData(cdv1);
        rvo2.setContactType(cdt);
        rvo2.setTextSummary(tv);
        
        rvo3.setAgentID(av);
        rvo3.setCustomerID(cv3);
        rvo3.setContactData(cdv1);
        rvo3.setContactType(cdt);
        rvo3.setTextSummary(tv);
        RecordDBAPI.saveRecord(rvo);
        RecordDBAPI.saveRecord(rvo2);
        RecordDBAPI.saveRecord(rvo3);*/
		
        //System.out.println(RecordDBAPI.updateRecord(rvo));
	

		/*RecordVO rc = RecordDBAPI.retrieveRecord("record01");
		System.out.println(rc.retrieveRecordTime().getTime());*/
		
		/*
		try {
       		Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
       		System.out.println("Success loading Mysql Driver!");
      	}
      	catch (Exception e) {
        	System.out.print("Error loading Mysql Driver!");
        	e.printStackTrace();
      	}
      	
    	Connection connect;
		try {
			connect = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
		
         //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

    	System.out.println("Success connect Mysql server!");

    	Statement stmt = connect.createStatement();

    	ResultSet rs1 = stmt.executeQuery("SELECT AgentID FROM view_agent WHERE PhoneNo = \'" + "443543123" + '\'');
    	
		
    	List<String> ids  = new ArrayList<String>();
    	while(rs1.next()){
    	ids.add(rs1.getString("AgentID"));} 

    	System.out.println(ids);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//CustomerVO cv2 = new CustomerVO(rs1.getString("CustomerID"));
		//System.out.println(ids);
		//System.out.println(CustomerDBAPI.deleteCustomer("10"));
		
		System.out.println(RecordDBAPI.lookupRecord("data2b"));
		System.out.println(CustomerDBAPI.lookupCustomer("443543123"));

	}

}
