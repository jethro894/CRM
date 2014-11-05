package com.crm.ass3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	private static final ResultSet Null = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//TODO Auto-generated method stubt
		/*AddressVO avo = new AddressVO("122 la sasdflle","aptdsf 9","new yordfk","ndfy","10027","uds");
		NameVO nv = new NameVO("aafd","bfaf","sbaf");
		EmailVO ev = new EmailVO("sb34@sb2.com");
		PhoneVO pv = new PhoneVO("443543123");
		//IDVO aid = new IDVO("agent007");
		
		AgentVO cv = new AgentVO("6");
		
		cv.setAddress(avo);
		//cv.setAgentID(aid);
		cv.setEmail(ev);
		cv.setName(nv);
		cv.setPhoneNo(pv);
		
		
		System.out.println(AgentDBAPI.updateAgent(cv));*/
		
		RecordVO rvo = new RecordVO("87");
        IDVO av = new IDVO("1");
        //IDVO cv = new IDVO("customer001");
        TextVO tv = new TextVO("totally useless");
        ContactDataVO cdv1 = new ContactDataVO("data2b");
        ContactTypeVO cdt = new ContactTypeVO("Voice");
        rvo.setAgentID(av);
        //rvo.setCustomerID(cv);
        rvo.setContactData(cdv1);
        rvo.setContactType(cdt);
        rvo.setTextSummary(tv);
        System.out.println(RecordDBAPI.updateRecord(rvo));
	

		/*RecordVO rc = RecordDBAPI.retrieveRecord("record01");
		System.out.println(rc.retrieveRecordTime().getTime());*/
		/*try {
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

    	ResultSet rs1 = stmt.executeQuery("SELECT * FROM view_customer WHERE CustomerID = \'" + "50jaioshvuizskfaufjewHefovw" + '\'');
    	System.out.println(rs1.next());
		
		
		//CustomerVO cv2 = new CustomerVO(rs1.getString("CustomerID"));
		//System.out.println(rs1.getString("CustomerID") + rs1.getString("PhoneNo") );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(CustomerDBAPI.deleteCustomer("10"));

	}

}
