create database dbtest;
use dbtest;
create table Agent(AgentID integer,PhoneNo VARCHAR(20),Email VARCHAR(20), PRIMARY KEY (AgentID));
create table Customer(CustomerID integer,PhoneNo VARCHAR(20),Email VARCHAR(20), PRIMARY KEY (CustomerID));
create table Customer_Address(AddressLine1 VARCHAR(20),AddressLine2 VARCHAR(20), City VARCHAR(20),
								State VARCHAR(20), Country VARCHAR(20), Zip VARCHAR(20), CustomerID integer, CAID integer,
								PRIMARY KEY (CustomerID,CAID), FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE);

create table Agent_Address(AddressLine1 VARCHAR(20),AddressLine2 VARCHAR(20), City VARCHAR(20),
								State VARCHAR(20), Country VARCHAR(20), Zip VARCHAR(20), AgentID integer, AAID integer,
								PRIMARY KEY (AgentID,AAID), FOREIGN KEY(AgentID) REFERENCES Agent(AgentID) ON DELETE CASCADE);

create table Customer_Name(firstname VARCHAR(20), middlename VARCHAR(20), lastname VARCHAR(20),CNID integer, CustomerID integer,
							PRIMARY KEY (CustomerID,CNID),FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE);

create table Agent_Name(firstname VARCHAR(20), middlename VARCHAR(20), lastname VARCHAR(20),ANID integer, AgentID integer,
							PRIMARY KEY (AgentID,ANID),FOREIGN KEY(AgentID) REFERENCES Agent(AgentID) ON DELETE CASCADE);

create table Record(RecordID integer,TextSummary VARCHAR(20),Data VARCHAR(20),
					Type VARCHAR(20),Time VARCHAR(20),AgentID integer,CustomerID integer,PRIMARY KEY (CustomerID),
					FOREIGN KEY(AgentID) REFERENCES Agent(AgentID),FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID));

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