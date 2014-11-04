create database homework2_update;
use homework2_update;
create table Agent(AgentID integer primary key auto_increment,PhoneNo VARCHAR(20),Email VARCHAR(20));
create table Customer(CustomerID integer primary key auto_increment,PhoneNo VARCHAR(20),Email VARCHAR(20));
create table Customer_Address(CAID integer primary key auto_increment, AddressLine1 VARCHAR(20),AddressLine2 VARCHAR(20), City VARCHAR(20),State VARCHAR(20), Country VARCHAR(20), Zip VARCHAR(20));
create table Customer2Customer_Address(CAID integer, CustomerID integer, PRIMARY KEY(CustomerID),FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),FOREIGN KEY (CAID) REFERENCES Customer_Address(CAID));							
								

create table Agent_Address(AAID integer primary key auto_increment, AddressLine1 VARCHAR(20),AddressLine2 VARCHAR(20), City VARCHAR(20),State VARCHAR(20), Country VARCHAR(20), Zip VARCHAR(20));
create table Agent2Agent_Address(AAID integer, AgentID integer, PRIMARY KEY(AgentID),FOREIGN KEY (AgentID) REFERENCES Agent(AgentID),FOREIGN KEY (AAID) REFERENCES Agent_Address(AAID));							
							
								

create table Customer_Name(CNID integer primary key auto_increment, firstname VARCHAR(20), middlename VARCHAR(20), lastname VARCHAR(20));
create table Customer2Customer_Name(CNID integer, CustomerID integer, PRIMARY KEY(CustomerID), FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID), FOREIGN KEY (CNID) REFERENCES Customer_Name(CNID));							

create table Agent_Name(ANID integer primary key auto_increment,firstname VARCHAR(20), middlename VARCHAR(20), lastname VARCHAR(20));
create table Agent2Agent_Name(ANID integer, AgentID integer, PRIMARY KEY(AgentID), FOREIGN KEY(AgentID) REFERENCES Agent(AgentID), FOREIGN KEY (ANID) REFERENCES Agent_Name(ANID));							
						

create table Record(RecordID integer,TextSummary VARCHAR(20),Data VARCHAR(20),Type VARCHAR(20),Time VARCHAR(20),AgentID integer,CustomerID integer,PRIMARY KEY (CustomerID),FOREIGN KEY(AgentID) REFERENCES Agent(AgentID),FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID));
					
					

load data local infile '~/Desktop/updated_homework2/agent.txt' into table Agent lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/customer.txt' into table Customer lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/customer_address.txt' into table Customer_Address lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/agent_address.txt' into table Agent_Address lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/customer_name.txt' into table Customer_Name lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/agent_name.txt' into table Agent_Name lines terminated by '\n';


load data local infile '~/Desktop/updated_homework2/record.txt' into table Record lines terminated by '\n';

load data local infile '~/Desktop/updated_homework2/c2customer_address.txt' into table Customer2Customer_Address lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/a2agent_address.txt' into table Agent2Agent_Address lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/c2customer_name.txt' into table Customer2Customer_Name lines terminated by '\n';
load data local infile '~/Desktop/updated_homework2/a2agent_name.txt' into table Agent2Agent_Name lines terminated by '\n';



create view view_agent as 
select agent.AgentID,aname.firstname,aname.middlename,aname.lastname,agent.Email,agent.PhoneNo,agentadd.AddressLine1,agentadd.AddressLine2,agentadd.City,agentadd.State,agentadd.Country,agentadd.Zip
from Agent agent, Agent_Name aname, Agent_Address agentadd, Agent2Agent_Address agent2agentadd, Agent2Agent_Name agent2agentname
where agent.AgentID = agent2agentadd.AgentID and agent.AgentID = agent2agentname.AgentID and agent2agentadd.AAID = agentadd.AAID and agent2agentname.ANID = aname.ANID;

create view view_customer as 
select customer.CustomerID,cname.firstname,cname.middlename,cname.lastname,customer.Email,customer.PhoneNo,record.AgentID,custadd.AddressLine1,custadd.AddressLine2,custadd.City,custadd.State,custadd.Country,custadd.Zip
from Customer customer, Customer_Name cname, Customer_Address custadd, Record record, Customer2Customer_Name customer2cname, Customer2Customer_Address customer2custadd
where customer.CustomerID = customer2cname.CustomerID and customer.CustomerID = customer2custadd.CustomerID and record.CustomerID = customer.CustomerID and customer2custadd.CAID = custadd.CAID and customer2cname.CNID = cname.CNID;

create view view_record as 
select record.CustomerID,record.AgentID,record.Type, record.Data, record.TextSummary, record.RecordID,record.Time
from Record record,Agent agent, Customer customer
where customer.CustomerID = record.CustomerID and agent.AgentID = record.AgentID;