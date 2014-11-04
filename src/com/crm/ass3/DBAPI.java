package com.crm.ass3;

import java.util.List;
import java.sql.*;


public class DBAPI {
	protected final String[] customer_facet = {"ID", "Name", "Email", "PhoneNo", "AgentID", "Address"};
	protected final String[] agent_facet = {"ID", "Name", "Email", "PhoneNo", "Address"};
	protected final String[] record_facet = {"CustomerID", "AgentID", "Type", "Data", "TextSummary", "RecordID", "Time"};
	
	/////////////////////////////////////////////////retrieve///////////////////////////////////////////////////////
	
	public static VOBase retrieve(String tableName, String id){
		VOBase vb = null;
		///do sth
		try {
       		Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
       		System.out.println("Success loading Mysql Driver!");
      	}
      	catch (Exception e) {
        	System.out.print("Error loading Mysql Driver!");
        	e.printStackTrace();
      	}
      	try {
        	Connection connect = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dbtest","root","");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        	System.out.println("Success connect Mysql server!");

        	Statement stmt = connect.createStatement();
     
        	switch(tableName){
        	case "view_customer":
        		/*ResultSet rs = stmt.executeQuery("SELECT customer.CustomerID,cname.firstname,cname.middlename,cname.lastname,customer"
        										+ ".Email,customer.PhoneNo,record.AgentID,custadd.AddressLine1"
        										+ ",custadd.AddressLine2,custadd.City,custadd.State,custadd.Country,custadd.Zip"
        										+ " FROM Customer customer, Customer_Name cname, Customer_Address custadd, Record record"
											    + " WHERE customer.CustomerID = cname.CustomerID and customer.CustomerID = custadd.CustomerID "
											    + " and record.CustomerID = customer.CustomerID and customer.CustomerID = id");*/
        		
        		
        		ResultSet rs1 = stmt.executeQuery("SELECT * FROM view_customer WHERE CustomerID = id");
        		
        		//NameVO nv1 = new NameVO(rs1.getString("firstname"), rs1.getString("middlename"), rs1.getString("lastname"));
        		//AddressVO av1 = new AddressVO(rs1.getString("AddressLine1"), rs1.getString("AddressLine2"), rs1.getString("City"),
        		//							 rs1.getString("State"), rs1.getString("Country"), rs1.getString("Zip"));
        		//IDVO iv1 = new IDVO(rs1.getString("CustomerID"));
        		//EmailVO ev1 = new EmailVO(rs1.getString("Email"));
        		//PhoneVO pv1 = new PhoneVO(rs1.getString("PhoneNo"));
        		//IDVO iv2 = new IDVO(rs1.getString("AgentID"));
        		//vb = new CustomerVO(rs1.getString("CustomerID"), nv1, rs1.getString("Email"),rs1.getString("PhoneNo"),rs1.getString("AgentID"),av1);
        		//vb = new CustomerVO(iv1,nv1,ev1,pv1,iv2,av1);
        		vb = new CustomerVO(rs1.getString("CustomerID"));
        		break;
        	case "view_agent":
        	/*	ResultSet rs = stmt.executeQuery("SELECT agent.AgentID,aname.firstname,aname.middlename,aname.lastname,agent.Email
        										.agent.PhoneNo,agentadd.AddressLine1,agentadd.AddressLine2,agentadd.City,agentadd.State,agentadd.Country,
        										agentadd.Zip
												FROM Agent agent, Agent_Name aname, Agent_Address agentadd
											    WHERE agent.AgentID = aname.AgentID and agent.AgentID = agentadd.AgentID and agent.AgentID = id");*/
        		
        		ResultSet rs2 = stmt.executeQuery("SELECT * FROM view_agent WHERE AgentID = id");
        		
        		//NameVO nv2 = new NameVO(rs2.getString("firstname"), rs2.getString("middlename"), rs2.getString("lastname"));
        		//AddressVO av2 = new AddressVO(rs2.getString("AddressLine1"), rs2.getString("AddressLine2"), rs2.getString("City"),
        		//							 rs2.getString("State"), rs2.getString("Country"), rs2.getString("Zip"));
        		//vb = new AgentVO(rs2.getString("AgentID"),nv2,rs2.getString("Email"),rs2.getString("PhoneNo"),av2);
        		vb = new AgentVO(rs2.getString("AgentID"));
        		break;
        	case "view_record":
        		/*ResultSet rs = stmt.executeQuery("SELECT record.CustomerID,record.AgentID,record.Type, record.Data, record.TextSummary, record.RecordID,record.Time
												FROM Record record,Agent agent, Customer customer
												WHERE customer.CustomerID = record.CustomerID and agent.AgentID = record.AgentID and record.RecordID = id");*/
        		ResultSet rs = stmt.executeQuery("SELECT *  FROM view_record WHERE RecordID = id");
        		
        		//vb = new RecordVO(rs.getString("CustomerID"), rs.getString("AgentID"), rs.getString("Type"),rs.getString("Data"),rs.getString("TextSummary"),rs.getString("RecordID"),rs.getString("Time"));
        		vb = new RecordVO(rs.getString("RecordID"));
        		break;
        	
        	}
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
	
      	return vb;
		
	}
	
///////////////////////////////////////////////////lookup//////////////////////////////////////////////////////

	public static List<String> lookup(String tableName, String key){
		List<String> ids = null;

		try {
       Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
       System.out.println("Success loading Mysql Driver!");
      }
      catch (Exception e) {
        System.out.print("Error loading Mysql Driver!");
        e.printStackTrace();
      }
      try {
        Connection connect = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dbtest","root","");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        System.out.println("Success connect Mysql server!");
        Statement stmt = connect.createStatement();
        ResultSet rs;
          switch(tableName){
        	case "view_customer":
        		rs = stmt.executeQuery("select * from view_customer where CustomerID = key");
        		ids.add(rs.getString("CustomerID")); 
        		ids.add(rs.getString("firstname")+rs.getString("middlename")+rs.getString("lastname"));
        		ids.add(rs.getString("Email"));
        		ids.add(rs.getString("PhoneNo"));
        		ids.add(rs.getString("Agentid"));
        		ids.add(rs.getString("AddressLine1")+rs.getString("AddressLine2")+rs.getString("City")+rs.getString("State")+rs.getString("Country"));
        		break;
        	case "view_agent":
        		rs = stmt.executeQuery("select * from view_agent where AgentID = key");
        		ids.add(rs.getString("AgentID"));
        		ids.add(rs.getString("firstname")+rs.getString("middlename")+rs.getString("lastname"));
        		ids.add(rs.getString("Email"));
        		ids.add(rs.getString("PhoneNo"));
        		ids.add(rs.getString("AddressLine1")+rs.getString("AddressLine2")+rs.getString("City")+rs.getString("State")+rs.getString("Country"));
        		break;
        	case "view_record":
        		rs = stmt.executeQuery("select * from view_record where RecordID = key");
        		ids.add(rs.getString("CustomerID"));
        		ids.add(rs.getString("AgentID"));
        		ids.add(rs.getString("Type"));
        		ids.add(rs.getString("Data"));
        		ids.add(rs.getString("TextSummary"));
        		ids.add(rs.getString("RecordID"));
        		ids.add(rs.getString("Time"));
        		break;
        }
      
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
		return ids;
	}
	
	////////////////////////////////////////////////////save////////////////////////////////////////////////////////////
	
	synchronized public static boolean save(String tableName, VOBase vb){
		try {
       Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
       System.out.println("Success loading Mysql Driver!");
      }
      catch (Exception e) {
        System.out.print("Error loading Mysql Driver!");
        e.printStackTrace();
      }
      try {
        Connection connect = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dbtest","root","");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        System.out.println("Success connect Mysql server!");


        switch(tableName){
        	case "view_customer":
        		/*PreparedStatement Statement=connect.prepareStatement("INSERT INTO view_customer VALUES(?,?)");
       
           		Statement.setString(1,vb.retrieveID().getID());
           		Statement.setString(2,vb.retrieveName().getName());
           		Statement.setString(3,vb.retrieveEmail().getEmail());
           		Statement.setString(4,vb.retrievePhone().getPhone());
           		Statement.setString(5,vb.retrieveAgentID().getAgentID());
           		Statement.setString(6,vb.retrieveAddress().getAddress());
           		Statement.executeUpdate();*/
        	CustomerVO vb2 =  (CustomerVO)vb;
            PreparedStatement Statement1 = connect.prepareStatement("INSERT INTO Customer VALUES(?,?)");

            Statement1.setString(1,vb2.retrieveID().getID());
            Statement1.setString(3,vb2.retrieveEmail().getEmail());
            Statement1.setString(2,vb2.retrievePhone().getPhoneNumber());

            PreparedStatement Statement2 = connect.prepareStatement("INSERT INTO Customer_Name VALUES(?,?)");
            String[] NameArray = vb2.retrieveName().getNameArray();
            // seperate into FN|MN|LN, insert from 1 to 3
            Statement2.setString(1,NameArray[0]);
            Statement2.setString(2,NameArray[1]);
            Statement2.setString(3,NameArray[2]);
            Statement2.setString(4,vb2.retrieveID().getID());
            Statement2.setString(5,vb2.retrieveName().retrieveID().getID());

            PreparedStatement Statement3 = connect.prepareStatement("INSERT INTO Customer_Address VALUES(?,?)");
            // seperate into AL1|AL2|CT|STT|ZIP|CN insert from 1 to 6
            String[] AddressArray = vb2.retrieveAddress().getAddressArray();

            // insert CAID
            Statement3.setString(1,AddressArray[0]);
            Statement3.setString(2,AddressArray[1]);
            Statement3.setString(3,AddressArray[2]);
            Statement3.setString(4,AddressArray[3]);
            Statement3.setString(5,AddressArray[4]);
            Statement3.setString(6,AddressArray[5]);
            Statement3.setString(7,vb2.retrieveID().getID());
            Statement3.setString(8,vb2.retrieveAddress().retrieveID().getID());

            return true;
        
        	case "view_agent":
        		/*PreparedStatement Statement=connect.prepareStatement("INSERT INTO view_agent VALUES(?,?)");
       
           		Statement.setString(1,vb.retrieveID().getID());
           		Statement.setString(2,vb.retrieveName().getName());
           		Statement.setString(3,vb.retrieveEmail().getEmail());
           		Statement.setString(4,vb.retrievePhone().getPhone());
           		Statement.setString(5,vb.retrieveAgentID().getAgentID());
           		Statement.setString(6,vb.retrieveAddress().getAddress());
          		Statement.executeUpdate();*/
        		AgentVO vb3 =  (AgentVO)vb;
            PreparedStatement Statement11 = connect.prepareStatement("INSERT INTO Agent VALUES(?,?)");

            Statement11.setString(1,vb3.retrieveID().getID());
            Statement11.setString(3,vb3.retrieveEmail().getEmail());
            Statement11.setString(2,vb3.retrievePhone().getPhoneNumber());

            PreparedStatement Statement21 = connect.prepareStatement("INSERT INTO Agent_Name VALUES(?,?)");
            String[] NameArray1 = vb3.retrieveName().getNameArray();
            // seperate into FN|MN|LN, insert from 1 to 3
            Statement21.setString(1,NameArray1[0]);
            Statement21.setString(2,NameArray1[1]);
            Statement21.setString(3,NameArray1[2]);
            Statement21.setString(4,vb3.retrieveID().getID());// This is for customerID
            //CNID insertation wanted
            Statement21.setString(5,vb3.retrieveName().retrieveID().getID());

            PreparedStatement Statement31 = connect.prepareStatement("INSERT INTO Agent_Address VALUES(?,?)");
            // seperate into AL1|AL2|CT|STT|ZIP|CN insert from 1 to 6
            String[] AddressArray1 = vb3.retrieveAddress().getAddressArray();

            
            Statement31.setString(1,AddressArray1[0]);
            Statement31.setString(2,AddressArray1[1]);
            Statement31.setString(3,AddressArray1[2]);
            Statement31.setString(4,AddressArray1[3]);
            Statement31.setString(5,AddressArray1[4]);
            Statement31.setString(6,AddressArray1[5]);
            // insert CAID needed
            Statement31.setString(7,vb3.retrieveID().getID());
            Statement31.setString(8,vb3.retrieveAddress().retrieveID().getID());
			return true; 
        	case "view_record":
        		RecordVO vb4 = (RecordVO)vb;
        		PreparedStatement Statement=connect.prepareStatement("INSERT INTO record VALUES(?,?)");
       
           		Statement.setString(1,vb4.retrieveCustomerID().getID());
           		Statement.setString(2,vb4.retrieveAgentID().getID());
           		Statement.setString(3,vb4.getTextSummary().getText());
           		Statement.setString(4,vb4.getRecordData().getContactData());
           		Statement.setString(5,vb4.getRecordDataType().getContactType());
           		Statement.setString(6,vb4.retrieveRecordTime().getTime());
              // Record ID wanted
                Statement.setString(7,vb4.retrieveRecordID().getID());
        	return true;
        	
        }
      
       }   
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
	return false;	
	}



////////////////////////////////////////////////update//////////////////////////////////////////////////////////
	
	synchronized public static boolean update(String tableName, VOBase vb){
		switch(tableName){
		case "view_customer":
			CustomerVO vb_customer = (CustomerVO)vb;
			delete(tableName,vb_customer.retrieveID().getID());
			save(tableName,vb);
			return true;
		case "view_record":
			RecordVO vb_record = (RecordVO)vb;
			delete(tableName, vb_record.retrieveRecordID().getID());
			save(tableName,vb);
			return true;
		case "view_agent":
			AgentVO vb_agent = (AgentVO)vb;
			delete(tableName,vb_agent.retrieveID().getID());
			save(tableName,vb);
			return true;
		}
		return false;
	}
	


///////////////////////////////////////////////////delete////////////////////////////////////////////////////
	
	synchronized public static boolean delete(String tableName, String id){
		try {
       Class.forName("com.mysql.jdbc.Driver");     //加载MYSQL JDBC驱动程序   
       System.out.println("Success loading Mysql Driver!");
      }
      catch (Exception e) {
        System.out.print("Error loading Mysql Driver!");
        e.printStackTrace();
      }
      try {
        Connection connect = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dbtest","root","");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        System.out.println("Success connect Mysql server!");
        Statement stmt = connect.createStatement();
        ResultSet rs;
        switch(tableName){
        	case "view_customer":
        		rs = stmt.executeQuery("delete * from view_customer where CustomerID = id");
        		return true;
        	case "view_agent":
        		rs = stmt.executeQuery("delete * from view_agent where AgentID = id");
        		return true;
        	case "view_record":
        		rs = stmt.executeQuery("delete * from view_record where RecordID = id");
        		return true;
        
        }
       
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
	return false;
		
	}
	
}
