create database dbtest;
use dbtest;

create table Agent(AgentID varchar(150),PhoneNo VARCHAR(20),Email VARCHAR(20), PRIMARY KEY (AgentID));
create table Customer(CustomerID varchar(150),PhoneNo VARCHAR(20),Email VARCHAR(20), PRIMARY KEY (CustomerID));
create table Customer_Address(AddressLine1 VARCHAR(20),AddressLine2 VARCHAR(20), City VARCHAR(20),State VARCHAR(20), Country VARCHAR(20), Zip VARCHAR(20), CustomerID varchar(150), CAID varchar(150),PRIMARY KEY (CustomerID,CAID), FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE);

create table Agent_Address(AddressLine1 VARCHAR(20),AddressLine2 VARCHAR(20), City VARCHAR(20),State VARCHAR(20), Country VARCHAR(20), Zip VARCHAR(20), AgentID varchar(150), AAID varchar(150),PRIMARY KEY (AgentID,AAID), FOREIGN KEY(AgentID) REFERENCES Agent(AgentID) ON DELETE CASCADE);

create table Customer_Name(firstname VARCHAR(20), middlename VARCHAR(20), lastname VARCHAR(20),CNID varchar(150), CustomerID varchar(150),PRIMARY KEY (CustomerID,CNID),FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE);

create table Agent_Name(firstname VARCHAR(20), middlename VARCHAR(20), lastname VARCHAR(20),ANID varchar(150), AgentID varchar(150),PRIMARY KEY (AgentID,ANID),FOREIGN KEY(AgentID) REFERENCES Agent(AgentID) ON DELETE CASCADE);

create table Record(RecordID varchar(150),TextSummary VARCHAR(20),Data VARCHAR(20),Type VARCHAR(20),Time VARCHAR(150),AgentID varchar(150),CustomerID varchar(150),PRIMARY KEY (CustomerID),FOREIGN KEY(AgentID) REFERENCES Agent(AgentID) ON DELETE CASCADE, FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE);
>>>>>>> yuechenqin

load data local infile '~/Desktop/agent.txt' into table Agent lines terminated by '\n';
load data local infile '~/Desktop/customer.txt' into table Customer lines terminated by '\n';
load data local infile '~/Desktop/customer_address.txt' into table Customer_Address lines terminated by '\n';
load data local infile '~/Desktop/agent_address.txt' into table Agent_Address lines terminated by '\n';
load data local infile '~/Desktop/customer_name.txt' into table Customer_Name lines terminated by '\n';
load data local infile '~/Desktop/agent_name.txt' into table Agent_Name lines terminated by '\n';
load data local infile '~/Desktop/record.txt' into table Record lines terminated by '\n';



create view view_agent as 
select agent.AgentID,aname.firstname,aname.middlename,aname.lastname,agent.Email,agent.PhoneNo,agentadd.AddressLine1,agentadd.AddressLine2,agentadd.City,agentadd.State,agentadd.Country,agentadd.Zip
from Agent agent, Agent_Name aname, Agent_Address agentadd
where agent.AgentID = aname.AgentID and agent.AgentID = agentadd.AgentID;

create view view_customer as 
select customer.CustomerID,cname.firstname,cname.middlename,cname.lastname,customer.Email,customer.PhoneNo,record.AgentID,custadd.AddressLine1,custadd.AddressLine2,custadd.City,custadd.State,custadd.Country,custadd.Zip
from Customer customer, Customer_Name cname, Customer_Address custadd, Record record
where customer.CustomerID = cname.CustomerID and customer.CustomerID = custadd.CustomerID and record.CustomerID = customer.CustomerID;

create view view_record as 
select record.CustomerID,record.AgentID,record.Type, record.Data, record.TextSummary, record.RecordID,record.Time
from Record record,Agent agent, Customer customer

where customer.CustomerID = record.CustomerID and agent.AgentID = record.AgentID;



update Agent a, Agent_Address aa, Agent_Name an
set a.PhoneNo = '12345789',a.Email = 'wqfb@yahoo.com', aa.AddressLine1 = '30', aa.AddressLine2 = 'bradway', aa.City = 'BC',aa.State = 'PAA',aa.Country = 'PAR', aa.zip = '12345', aa.AAID = '34556',an.firstname='SB01',an.middlename='MidSB',an.lastname='LastSB',an.anid = 'DB2e343'
where a.agentid = '1' and aa.agentid = a.agentid and an.agentid = a.agentid;

update Customer a, Customer_Address aa, Customer_Name an
set a.PhoneNo = '12345789',a.Email = 'wqfb@yahoo.com', aa.AddressLine1 = '30', aa.AddressLine2 = 'bradway', aa.City = 'BC',aa.State = 'PAA',aa.Country = 'PAR', aa.zip = '12345', aa.cAID = '34556',an.firstname='SB01',an.middlename='MidSB',an.lastname='LastSB',an.cnid = 'DB2e343'
where a.customerid = '10' and aa.customerid = a.customerid and an.customerid = a.customerid;

update Record r
set r.TextSummary = 'Yinshenzuidiao',r.Data = 'data15',r.Type = 'voice', r.Time = '2015/1/1', r.agentId = '6'
where r.RecordID = '3';
>>>>>>> yuechenqin
