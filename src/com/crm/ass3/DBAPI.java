package com.crm.ass3;

import java.util.ArrayList;
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
            "jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
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
        		ResultSet rs1 = stmt.executeQuery("SELECT * FROM view_customer WHERE CustomerID = \'" + id + '\'');
        		rs1.next();        		
        		CustomerVO cvb = new CustomerVO(rs1.getString("CustomerID"));        		
        		NameVO nv1 = new NameVO(rs1.getString("firstname"), rs1.getString("middlename"), rs1.getString("lastname"));       		
        		AddressVO av1 = new AddressVO(rs1.getString("AddressLine1"), rs1.getString("AddressLine2"), rs1.getString("City"),rs1.getString("State"), rs1.getString("Country"), rs1.getString("Zip"));
        		EmailVO ev1 = new EmailVO(rs1.getString("Email"));       		
        		PhoneVO pv1 = new PhoneVO(rs1.getString("PhoneNo"));        		
        		IDVO iv1 = new IDVO(rs1.getString("AgentID"));
        		rs1.next();
        		cvb.setAddress(av1);
        		cvb.setAgentID(iv1);
        		cvb.setEmail(ev1);
        		cvb.setName(nv1);
        		cvb.setPhoneNo(pv1);
        		return cvb;
        	case "view_agent":
        	/*	ResultSet rs = stmt.executeQuery("SELECT agent.AgentID,aname.firstname,aname.middlename,aname.lastname,agent.Email
        										.agent.PhoneNo,agentadd.AddressLine1,agentadd.AddressLine2,agentadd.City,agentadd.State,agentadd.Country,
        										agentadd.Zip
												FROM Agent agent, Agent_Name aname, Agent_Address agentadd
											    WHERE agent.AgentID = aname.AgentID and agent.AgentID = agentadd.AgentID and agent.AgentID = id");        		
        		ResultSet rs2 = stmt.executeQuery("SELECT * FROM view_agent WHERE AgentID = id");        		
        		//NameVO nv2 = new NameVO(rs2.getString("firstname"), rs2.getString("middlename"), rs2.getString("lastname"));
        		//AddressVO av2 = new AddressVO(rs2.getString("AddressLine1"), rs2.getString("AddressLine2"), rs2.getString("City"),
        		//							 rs2.getString("State"), rs2.getString("Country"), rs2.getString("Zip"));
        		//vb = new AgentVO(rs2.getString("AgentID"),nv2,rs2.getString("Email"),rs2.getString("PhoneNo"),av2);
        		vb = new AgentVO(rs2.getString("AgentID"));
        		break;*/
        		ResultSet rs2 = stmt.executeQuery("SELECT * FROM view_Agent WHERE AgentID = \'" + id + '\'');
        		rs2.next();        		
        		AgentVO avb = new AgentVO(rs2.getString("AgentID"));        		
        		NameVO nv2 = new NameVO(rs2.getString("firstname"), rs2.getString("middlename"), rs2.getString("lastname"));       		
        		AddressVO av2 = new AddressVO(rs2.getString("AddressLine2"), rs2.getString("AddressLine2"), rs2.getString("City"),rs2.getString("State"), rs2.getString("Country"), rs2.getString("Zip"));
        		EmailVO ev2 = new EmailVO(rs2.getString("Email"));       		
        		PhoneVO pv2 = new PhoneVO(rs2.getString("PhoneNo"));        		
        		rs2.next();
        		avb.setAddress(av2);
        		avb.setEmail(ev2);
        		avb.setName(nv2);
        		avb.setPhoneNo(pv2);
        		return avb;
        	case "view_record":
        		/*ResultSet rs = stmt.executeQuery("SELECT record.CustomerID,record.AgentID,record.Type, record.Data, record.TextSummary, record.RecordID,record.Time
												FROM Record record,Agent agent, Customer customer
												WHERE customer.CustomerID = record.CustomerID and agent.AgentID = record.AgentID and record.RecordID = id");*/
        		ResultSet rs3 = stmt.executeQuery("SELECT *  FROM view_record WHERE RecordID = \'" + id + '\'');
        		rs3.next();  
        		RecordVO rvo = new RecordVO(rs3.getString("RecordID"),rs3.getString("Time"));
                IDVO av = new IDVO(rs3.getString("AgentID"));
                IDVO cv = new IDVO(rs3.getString("CustomerID"));
                TextVO tv = new TextVO(rs3.getString("TextSummary"));
                ContactDataVO cdv1 = new ContactDataVO(rs3.getString("Data"));
                ContactTypeVO cdt = new ContactTypeVO(rs3.getString("Type"));
                rs3.next();
                rvo.setAgentID(av);
                rvo.setCustomerID(cv);
                rvo.setContactData(cdv1);
                rvo.setContactType(cdt);
                rvo.setTextSummary(tv);  
                return rvo;
        		//vb = new RecordVO(rs.getString("CustomerID"), rs.getString("AgentID"), rs.getString("Type"),rs.getString("Data"),rs.getString("TextSummary"),rs.getString("RecordID"),rs.getString("Time"));
        		//vb = new RecordVO(rs.getString("RecordID"));
        		//break;
        	
        	}
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
	
      	return vb;
		
	}
	
///////////////////////////////////////////////////lookup//////////////////////////////////////////////////////

	/*public static List<String> lookup(String tableName, String key){
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
            "jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        System.out.println("Success connect Mysql server!");
        Statement stmt = connect.createStatement();
        ResultSet rs;
          switch(tableName){
        	case "view_customer":
        		rs = stmt.executeQuery("select * from view_customer where CustomerID = \'" + key +'\'');
        		rs.next();
        		ids.add(rs.getString("CustomerID")); 
        		ids.add(rs.getString("firstname")+rs.getString("middlename")+rs.getString("lastname"));
        		ids.add(rs.getString("Email"));
        		ids.add(rs.getString("PhoneNo"));
        		ids.add(rs.getString("Agentid"));
        		ids.add(rs.getString("AddressLine1")+rs.getString("AddressLine2")+rs.getString("City")+rs.getString("State")+rs.getString("Country"));
        		break;
        	case "view_agent":
        		rs = stmt.executeQuery("select * from view_agent where AgentID =  \'" + key +'\'');
        		rs.next();
        		ids.add(rs.getString("AgentID"));
        		ids.add(rs.getString("firstname")+rs.getString("middlename")+rs.getString("lastname"));
        		ids.add(rs.getString("Email"));
        		ids.add(rs.getString("PhoneNo"));
        		ids.add(rs.getString("AddressLine1")+rs.getString("AddressLine2")+rs.getString("City")+rs.getString("State")+rs.getString("Country"));
        		break;
        	case "view_record":
        		rs = stmt.executeQuery("select * from view_record where RecordID =  \'" + key +'\'');
        		rs.next();
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
	}*/
	
	
	public static List<String> lookup(String tableName, String key){
		List<String> ids  = new ArrayList<String>();

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
            "jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        System.out.println("Success connect Mysql server!");
        Statement stmt = connect.createStatement();
        ResultSet rs;
          switch(tableName){
        	case "view_customer":
        		
        		rs = stmt.executeQuery("select * from view_customer where CustomerID =  \'" + key + "\' or firstname = \'" + key + "\' or middlename = \'" + key + "\' or lastname = \'" + key + "\' or Email = \'" + key +"\' or PhoneNo = \'" + key + "\' or AgentID = \'" + key +"\' or AddressLine1 = \'"+ key +"\' or Addressline2 = \'"+ key +"\' or AddressLine2 = \'"+ key +"\' or City =\'"+ key +"\' or State = \'"+ key +"\' or Country = \'"+ key+"\'" );		
            	while(rs.next()){
                	ids.add(rs.getString("CustomerID"));} 
        		break;
        	case "view_agent":
        	    rs = stmt.executeQuery("select * from view_agent where AgentID =  \'" + key + "\' or firstname = \'" + key + "\' or middlename = \'" + key + "\' or lastname = \'" + key + "\' or Email = \'" + key +"\' or PhoneNo = \'" + key +"\' or AddressLine1 = \'"+ key +"\' or Addressline2 = \'"+ key +"\' or AddressLine2 = \'"+ key +"\' or City =\'"+ key +"\' or State = \'"+ key +"\' or Country = \'"+ key+"\'");
        			
            	while(rs.next()){
                	ids.add(rs.getString("AgentID"));}
         		break;
        	case "view_record":
        		rs = stmt.executeQuery("select * from view_record where CustomerID =  \'" + key + "\' or AgentID = \'" + key + "\' or recordID = \'" + key + "\' or Type = \'" + key + "\' or Data = \'" + key +"\' or Time = \'" + key + "\' or TextSummary = \'" + key +"\'" );
    			
            	while(rs.next()){
                	ids.add(rs.getString("RecordID"));}
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
            "jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
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
            PreparedStatement Statement1 = connect.prepareStatement("INSERT INTO Customer(customerid,phoneno,email)"+"VALUES(?,?,?)");
        	//PreparedStatement Statement1 = connect.prepareStatement("INSERT INTO Customer VALUES("+vb2.retrieveID().getID()+vb2.retrievePhone().getPhoneNumber()+vb2.retrieveEmail().getEmail()+")");
        	
            Statement1.setString(1,vb2.retrieveID().getID());
            Statement1.setString(2,vb2.retrievePhone().getPhoneNumber());
            Statement1.setString(3,vb2.retrieveEmail().getEmail());
            Statement1.execute();
            PreparedStatement Statement2 = connect.prepareStatement("INSERT INTO Customer_Name(firstname,middlename,lastname,cnid,customerid)" + "VALUES(?,?,?,?,?)");
            String[] NameArray = vb2.retrieveName().getNameArray();
            // seperate into FN|MN|LN, insert from 1 to 3
            Statement2.setString(1,NameArray[0]);
            Statement2.setString(2,NameArray[1]);
            Statement2.setString(3,NameArray[2]);
            Statement2.setString(4,vb2.retrieveName().retrieveID().getID());
            Statement2.setString(5,vb2.retrieveID().getID());
            Statement2.execute();
            PreparedStatement Statement3 = connect.prepareStatement("INSERT INTO Customer_Address(AddressLine1,AddressLine2, City,State, Country, Zip, CustomerID, CAID)" + "VALUES(?,?,?,?,?,?,?,?)");
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
            Statement3.execute();
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
            PreparedStatement Statement11 = connect.prepareStatement("INSERT INTO Agent(agentid,phoneno,email)"+"VALUES(?,?,?)");

            Statement11.setString(1,vb3.retrieveID().getID());
            Statement11.setString(3,vb3.retrieveEmail().getEmail());
            Statement11.setString(2,vb3.retrievePhone().getPhoneNumber());
            Statement11.execute();
            PreparedStatement Statement21 = connect.prepareStatement("INSERT INTO Agent_Name(firstname,middlename,lastname,anid,agentid)" + "VALUES(?,?,?,?,?)");
            String[] NameArray1 = vb3.retrieveName().getNameArray();
            // seperate into FN|MN|LN, insert from 1 to 3
            Statement21.setString(1,NameArray1[0]);
            Statement21.setString(2,NameArray1[1]);
            Statement21.setString(3,NameArray1[2]);
            // This is for customerID
            //CNID insertation wanted
            Statement21.setString(4,vb3.retrieveName().retrieveID().getID());
            Statement21.setString(5,vb3.retrieveID().getID());
            Statement21.execute();
            PreparedStatement Statement31 = connect.prepareStatement("INSERT INTO Agent_Address(AddressLine1,AddressLine2, City,State, Country, Zip, AgentID, AAID)" + "VALUES(?,?,?,?,?,?,?,?)");
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
            Statement31.execute();
			return true; 
        	case "view_record":
        		RecordVO vb4 = (RecordVO)vb;
        		PreparedStatement Statement=connect.prepareStatement("INSERT INTO record(RecordID,TextSummary,Data,Type,Time,AgentID,CustomerID)" + "VALUES(?,?,?,?,?,?,?)");
       
           		Statement.setString(7,vb4.retrieveCustomerID().getID());
           		Statement.setString(6,vb4.retrieveAgentID().getID());
           		Statement.setString(2,vb4.getTextSummary().getText());
           		Statement.setString(3,vb4.getRecordData().getContactData());
           		Statement.setString(4,vb4.getRecordDataType().getContactType());
           		Statement.setString(5,vb4.retrieveRecordTime().getTime());
                Statement.setString(1,vb4.retrieveRecordID().getID());
                Statement.execute();
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
	
	synchronized public static boolean update(String tableName, VOBase vb)/*{

		switch(tableName){
		case "view_customer":
			CustomerVO vb_customer = (CustomerVO)vb;
			if(delete(tableName,vb_customer.retrieveID().getID())){
			save(tableName,vb);
			return true;}
			else return false;
		case "view_record":
			RecordVO vb_record = (RecordVO)vb;
			if(delete(tableName, vb_record.retrieveRecordID().getID())){
			save(tableName,vb);
			return true;}
			else return false;
		case "view_agent":
			AgentVO vb_agent = (AgentVO)vb;
			if(delete(tableName,vb_agent.retrieveID().getID())){
			save(tableName,vb);
			return true;
			}
			else return false;
		}
		return false;
	}*/
			/*UPDATE table_name
		SET column1=value1,column2=value2,...
				WHERE some_column=some_value;*/
	{
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
		            "jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
		             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

		        System.out.println("Success connect Mysql server!");
		        Statement stmt = connect.createStatement();
		        int rs;
		        ResultSet ju;
		        switch(tableName){
		        	case "view_customer":
		        		CustomerVO vb_customer = (CustomerVO)vb;
		        		String CustomerId = vb_customer.retrieveID().getID();
		        		ju = stmt.executeQuery("select * from view_customer where CustomerID = \'" + CustomerId  +'\'');
		        		if (ju.next()){
		        			String phoneNoString = vb_customer.retrievePhone().getPhoneNumber();
		        			String emailString = vb_customer.retrieveEmail().getEmail();
		        			String aaAL1String = vb_customer.retrieveAddress().getAddressArray()[0];
		        			String aaAL2String = vb_customer.retrieveAddress().getAddressArray()[1];
		        			String cityString = vb_customer.retrieveAddress().getAddressArray()[2];
		        			String stateString = vb_customer.retrieveAddress().getAddressArray()[3];
		        			String countryString = vb_customer.retrieveAddress().getAddressArray()[4];
		        			String zipString =  vb_customer.retrieveAddress().getAddressArray()[5];
		        			String aaidString =  vb_customer.retrieveAddress().retrieveID().getID();
		        			String firstNameString = vb_customer.retrieveName().getNameArray()[0];
		        			String middleNameString = vb_customer.retrieveName().getNameArray()[1];
		        			String lastNameString = vb_customer.retrieveName().getNameArray()[2];
		        			String anidString = vb_customer.retrieveName().retrieveID().getID();
		        			rs = stmt.executeUpdate("update Customer a, Customer_Address aa, Customer_Name an "
		        					+ "set a.PhoneNo = \'" + phoneNoString + " \',a.Email = \'"+ emailString + "\', aa.AddressLine1 = \'"+ aaAL1String +"\', aa.AddressLine2 = \'"+ aaAL2String +"\', aa.City = \'"+cityString+"\',aa.State = \'"+stateString+"\',"
		        					+ "aa.Country = \'"+ countryString + "\', aa.zip = \'"+zipString+"\', aa.CAID = \'"+ aaidString + "\',an.firstname=\'"+ firstNameString+"\',an.middlename=\'"+ middleNameString+"\',an.lastname=\'"+ lastNameString+"\',an.Cnid = \'"+ anidString+"\'"
		        					+ " where a.Customerid = \'"+ CustomerId +"\' and aa.Customerid = a.Customerid and an.Customerid = a.Customerid;");
		        		    return true;
		        		}
		        		else return false;
		        	case "view_agent":
		        	    AgentVO vb_agent = (AgentVO)vb;
		        		String AgentId = vb_agent.retrieveID().getID();
		        		ju = stmt.executeQuery("select * from view_agent where agentID = \'" + AgentId +'\'');
		        		if (ju.next()){
		        			String phoneNoString = vb_agent.retrievePhone().getPhoneNumber();
		        			String emailString = vb_agent.retrieveEmail().getEmail();
		        			String aaAL1String = vb_agent.retrieveAddress().getAddressArray()[0];
		        			String aaAL2String = vb_agent.retrieveAddress().getAddressArray()[1];
		        			String cityString = vb_agent.retrieveAddress().getAddressArray()[2];
		        			String stateString = vb_agent.retrieveAddress().getAddressArray()[3];
		        			String countryString = vb_agent.retrieveAddress().getAddressArray()[4];
		        			String zipString =  vb_agent.retrieveAddress().getAddressArray()[5];
		        			String aaidString =  vb_agent.retrieveAddress().retrieveID().getID();
		        			String firstNameString = vb_agent.retrieveName().getNameArray()[0];
		        			String middleNameString = vb_agent.retrieveName().getNameArray()[1];
		        			String lastNameString = vb_agent.retrieveName().getNameArray()[2];
		        			String anidString = vb_agent.retrieveName().retrieveID().getID();
		        			rs = stmt.executeUpdate("update Agent a, Agent_Address aa, Agent_Name an "
		        					+ "set a.PhoneNo = \'" + phoneNoString + " \',a.Email = \'"+ emailString + "\', aa.AddressLine1 = \'"+ aaAL1String +"\', aa.AddressLine2 = \'"+ aaAL2String +"\', aa.City = \'"+cityString+"\',aa.State = \'"+stateString+"\',"
		        					+ "aa.Country = \'"+ countryString + "\', aa.zip = \'"+zipString+"\', aa.AAID = \'"+ aaidString + "\',an.firstname=\'"+ firstNameString+"\',an.middlename=\'"+ middleNameString+"\',an.lastname=\'"+ lastNameString+"\',an.anid = \'"+ anidString+"\'"
		        					+ " where a.agentid = \'"+ AgentId +"\' and aa.agentid = a.agentid and an.agentid = a.agentid;");
		        		    return true;
		        		}
		        		else return false;
		        	case "view_record":
		        		 RecordVO vb_Record = (RecordVO)vb;
			        	String RecordId = vb_Record.retrieveRecordID().getID();
		        		ju = stmt.executeQuery("select * from view_record where recordID = \'" + RecordId + "\'");
		        		if (ju.next()){
		        			String TextString = vb_Record.getTextSummary().getText();
		        			String DataString = vb_Record.getRecordData().getContactData();
		        			String TypeString = vb_Record.getRecordDataType().getContactType();
		        			String TimeString = vb_Record.retrieveRecordTime().getTime();
		        			String agentIdString = vb_Record.retrieveAgentID().getID();
		        			rs = stmt.executeUpdate("update Record r "+
		        					"set r.TextSummary = \'"+TextString + "\',r.Data = \'"+DataString +"\',r.Type = \'"+TypeString+"\', r.Time = \'"+TimeString+"\', r.agentId = \'"+agentIdString+"\' "+
		        					"where r.RecordID = \'" + RecordId + "\'");
		        		    return true;
		        		}
		        		else return false;
		        
		        }
		       
		      }
		      catch (Exception e) {
		        System.out.print("get data error!");
		        e.printStackTrace();
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
            "jdbc:mysql://localhost:3306/dbtest","root","wangqifeishabi");
             //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码

        System.out.println("Success connect Mysql server!");
        Statement stmt = connect.createStatement();
        int rs;
        ResultSet ju;
        switch(tableName){
        	case "view_customer":
        		ju = stmt.executeQuery("select * from view_customer where CustomerID = \'" + id +'\'');
        		if (ju.next()){
        			rs = stmt.executeUpdate("delete from customer where CustomerID =  \'" + id +'\'');
        		    return true;
        		}
        		else return false;
        	case "view_agent":
        		ju = stmt.executeQuery("select * from view_agent where agentID = \'" + id +'\'');
        		if (ju.next()){
        			 
        			rs = stmt.executeUpdate("delete from agent where agentID =  \'" + id +'\'');
        		    return true;
        		}
        		else return false;
        	case "view_record":
        		ju = stmt.executeQuery("select * from view_record where recordID = \'" + id +'\'');
        		if (ju.next()){
        			rs = stmt.executeUpdate("delete from record where recordID =  \'" + id +'\'');
        		    return true;
        		}
        		else return false;
        
        }
       
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
	return false;
		
	}
	
}
