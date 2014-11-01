package com.crm.ass3;

import java.util.List;
import java.sql.*;


public class DBAPI {
	protected final String[] customer_facet = {"ID", "Name", "Email", "PhoneNo", "AgentID", "Address"};
	protected final String[] agent_facet = {"ID", "Name", "Email", "PhoneNo", "Address"};
	protected final String[] record_facet = {"CustomerID", "AgentID", "Type", "Data", "TextSummary", "RecordID", "Time"};
	
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
        		ResultSet rs = stmt.executeQuery("SELECT customer.CustomerID,cname.firstname,cname.middlename,cname.lastname,customer.Email,
        										customer.PhoneNo,record.AgentID,custadd.AddressLine1,
        										custadd.AddressLine2,custadd.City,custadd.State,custadd.Country,custadd.Zip
												FROM Customer customer, Customer_Name cname, Customer_Address custadd, Record record
											    WHERE customer.CustomerID = cname.CustomerID and customer.CustomerID = custadd.CustomerID 
											    and record.CustomerID = customer.CustomerID and customer.CustomerID = id");
        		NameVO nv = new NameVO(rs.getString(firstname), rs.getString(middlename), rs.getString(lastname));
        		AddressVO av = new AddressVO(rs.getString(AddressLine1), rs.getString(AddressLine2), rs.getString(City),
        									 rs.getString(State), rs.getString(Country), rs.getString(Zip));
        		vb = CustomerVO(rs.getString(CustomerID),nv,rs.getString(Email),rs.getString(PhoneNo),rs.getString(AgentID),av);
        		break;
        	case "view_agent":
        		ResultSet rs = stmt.executeQuery("SELECT agent.AgentID,aname.firstname,aname.middlename,aname.lastname,agent.Email,
        										agent.PhoneNo,agentadd.AddressLine1,agentadd.AddressLine2,agentadd.City,agentadd.State,agentadd.Country,
        										agentadd.Zip
												FROM Agent agent, Agent_Name aname, Agent_Address agentadd
											    WHERE agent.AgentID = aname.AgentID and agent.AgentID = agentadd.AgentID and agent.AgentID = id");
        		NameVO nv = new NameVO(rs.getString(firstname), rs.getString(middlename), rs.getString(lastname));
        		AddressVO av = new AddressVO(rs.getString(AddressLine1), rs.getString(AddressLine2), rs.getString(City),
        									 rs.getString(State), rs.getString(Country), rs.getString(Zip));
        		vb = AgentVO(rs.getString(AgentID),nv,rs.getString(Email),rs.getString(PhoneNo),av);
        		break;
        	case "view_record":
        		ResultSet rs = stmt.executeQuery("SELECT record.CustomerID,record.AgentID,record.Type, record.Data, record.TextSummary, record.RecordID,record.Time
												FROM Record record,Agent agent, Customer customer
												WHERE customer.CustomerID = record.CustomerID and agent.AgentID = record.AgentID and record.RecordID = id");
        		vb = RecordVO(rs.getString(CustomerID), rs.getString(AgentID), rs.getString(Type),
        			rs.getString(Data),rs.getString(TextSummary),rs.getString(RecordID),rs.getString(Time));
        		break;
        	}
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
		return vb;
	}
	
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

          switch(tableName){
        	case "view_customer":
        		ResultSet rs = stmt.executeQuery("select * from view_customer where CustomerID = key");
        		ids.add(rs.getString(CustomerID)); 
        		ids.add(rs.getString(firstname)+rs.getString(middlename)+rs.getString(lastname));
        		ids.add(rs.getString(Email));
        		ids.add(rs.getString(PhoneNo));
        		ids.add(rs.getString(Agentid));
        		ids.add(rs.getString(AddressLine1)+rs.getString(AddressLine2)+rs.getString(City)+rs.getString(State)+rs.getString(Country));
        		break;
        	case "view_agent":
        		ResultSet rs = stmt.executeQuery("select * from view_agent where AgentID = key");
        		ids.add(rs.getString(AgentID));
        		ids.add(rs.getString(firstname)+rs.getString(middlename)+rs.getString(lastname));
        		ids.add(rs.getString(Email));
        		ids.add(rs.getString(PhoneNo));
        		ids.add(rs.getString(AddressLine1)+rs.getString(AddressLine2)+rs.getString(City)+rs.getString(State)+rs.getString(Country));
        		break;
        	case "view_record":
        		ResultSet rs = stmt.executeQuery("select * from view_record where RecordID = key");
        		ids.add(rs.getString(CustomerID));
        		ids.add(rs.getString(AgentID));
        		ids.add(rs.getString(Type));
        		ids.add(rs.getString(Data));
        		ids.add(rs.getString(TextSummary));
        		ids.add(rs.getString(RecordID),rs.getString(Time));
        		break;
        }
      
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
		return ids;
	}
	
		
	synchronized public static void save(String tableName, VOBase vb){
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
            PreparedStatement Statement1 = connect.prepareStatement("INSERT INTO Customer VALUES(?,?)");

            Statement1.setString(1,vb.retrieveID().getID());
            Statement1.setString(2,vb.retrieveEmail().getEmail());
            Statement1.setString(3,vb.retrievePhone().getPhone());

            PreparedStatement Statement2 = connect.prepareStatement("INSERT INTO Customer_Name VALUES(?,?)");
            String[] NameArray ＝ vb.retrieveName().getNameArray();
            // seperate into FN|MN|LN, insert from 1 to 3
            Statement2.setString(1,NameArray[0]);
            Statement2.setString(2,NameArray[1]);
            Statement2.setString(3,NameArray[2]);
            Statement2.setString(4,vb.retrieveID().getID());

            PreparedStatement Statement3 = connect.prepareStatement("INSERT INTO Customer_Address VALUES(?,?)");
            // seperate into AL1|AL2|CT|STT|ZIP|CN insert from 1 to 6
            String[] AddressArray = vb.retrieveAddress().getAddressArray();

            // insert CAID
            Statement3.setString(1,AddressArray[0]);
            Statement3.setString(2,AddressArray[1]);
            Statement3.setString(3,AddressArray[2]);
            Statement3.setString(4,AddressArray[3]);
            Statement3.setString(5,AddressArray[4]);
            Statement3.setString(6,AddressArray[5]);
            Statement3.setString(7,vb.retrieveID().getID());

   
        	break;
        	case "view_agent":
        		/*PreparedStatement Statement=connect.prepareStatement("INSERT INTO view_agent VALUES(?,?)");
       
           		Statement.setString(1,vb.retrieveID().getID());
           		Statement.setString(2,vb.retrieveName().getName());
           		Statement.setString(3,vb.retrieveEmail().getEmail());
           		Statement.setString(4,vb.retrievePhone().getPhone());
           		Statement.setString(5,vb.retrieveAgentID().getAgentID());
           		Statement.setString(6,vb.retrieveAddress().getAddress());
          		Statement.executeUpdate();*/
            PreparedStatement Statement1 = connect.prepareStatement("INSERT INTO Agent VALUES(?,?)");

            Statement1.setString(1,vb.retrieveID().getID());
            Statement1.setString(2,vb.retrieveEmail().getEmail());
            Statement1.setString(3,vb.retrievePhone().getPhone());

            PreparedStatement Statement2 = connect.prepareStatement("INSERT INTO Agent_Name VALUES(?,?)");
            String[] NameArray ＝ vb.retrieveName().getNameArray();
            // seperate into FN|MN|LN, insert from 1 to 3
            Statement2.setString(1,NameArray[0]);
            Statement2.setString(2,NameArray[1]);
            Statement2.setString(3,NameArray[2]);
            Statement2.setString(4,vb.retrieveID().getID());// This is for customerID
            //CNID insertation wanted

            PreparedStatement Statement3 = connect.prepareStatement("INSERT INTO Agent_Address VALUES(?,?)");
            // seperate into AL1|AL2|CT|STT|ZIP|CN insert from 1 to 6
            String[] AddressArray = vb.retrieveAddress().getAddressArray();

            
            Statement3.setString(1,AddressArray[0]);
            Statement3.setString(2,AddressArray[1]);
            Statement3.setString(3,AddressArray[2]);
            Statement3.setString(4,AddressArray[3]);
            Statement3.setString(5,AddressArray[4]);
            Statement3.setString(6,AddressArray[5]);
            // insert CAID needed
            Statement3.setString(7,vb.retrieveID().getID());
			    break; 
        	case "view_record":
        		PreparedStatement Statement=connect.prepareStatement("INSERT INTO record VALUES(?,?)");
       
           		Statement.setString(1,vb.retriveCostumerID().getID());
           		Statement.setString(2,vb.retriveAgentID().getID());
           		Statement.setString(3,vb.getTextSummary().getText());
           		Statement.setString(4,vb.getRecordData().getContactData());
           		Statement.setString(5,vb.getRecordDataType().getContactType());
           		Statement.setString(6,vb.retriveRecordTime().getTime());
              // Record ID wanted
        	break;
        }
      
       }   
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
		
	}




	
	synchronized public static void update(String tableName, VOBase vb){
		delete(tableName, vb.retrieveID().getID());
		save(tableName,vb);
		
	}
	



	synchronized public static void delete(String tableName, String id){
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
        		ResultSet rs = stmt.executeQuery("delete * from view_customer where CustomerID = id");
        		break;
        	case "view_agent":
        		ResultSet rs = stmt.executeQuery("delete * from view_agent where AgentID = id");
        		break;
        	case "view_record":
        		ResultSet rs = stmt.executeQuery("delete * from view_record where RecordID = id");
        		
        		break;
        }
       
      }
      catch (Exception e) {
        System.out.print("get data error!");
        e.printStackTrace();
      }
		
	}
	
}
