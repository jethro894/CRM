sudo /Library/StartupItems/MySQLCOM/MySQLCOM start
/usr/local/mysql/bin/mysql -u root -p
create database homework2;
use homework2;
create table address (id integer primary key auto_increment, addressline1 varchar(20), addressline2 varchar(20),city varchar(20), state varchar(20), country varchar(20), zip varchar(20));
create table name (id integer primary key auto_increment, firstname varchar(20), middlename varchar(20), lastname varchar(20));
create table contactdata (id integer primary key auto_increment, contactdata varchar(50));
create table contacttype (id integer primary key auto_increment, contacttype varchar(20));
create table phone(id integer primary key auto_increment, phonenumber varchar(20));
create table id(id integer primary key auto_increment, idnumber varchar(20));
create table text(id integer primary key auto_increment, textcontent varchar(200));
create table time(id integer primary key auto_increment, time varchar(20));
create table email(id integer primary key auto_increment, email varchar(20));
create table agent(id integer primary key auto_increment, agentid VARCHAR(20),name VARCHAR(20),email VARCHAR(20),phonenumber VARCHAR(20), address VARCHAR(20));
create table customer(id integer primary key auto_increment, customerid VARCHAR(20), name VARCHAR(20), email VARCHAR(20),phonenumber VARCHAR(20), agentid VARCHAR(20),address VARCHAR(20));
create table record(id integer primary key auto_increment, customerid VARCHAR(20), agentid VARCHAR(20), type VARCHAR(20),data VARCHAR(50), textsummary VARCHAR(200), time VARCHAR(20));


LOAD DATA LOCAL INFILE 'desktop/resource/address.txt' INTO TABLE address LINES TERMINATED BY';';
LOAD DATA LOCAL INFILE 'desktop/resource/name.txt' INTO TABLE name LINES TERMINATED BY';';
LOAD DATA LOCAL INFILE 'desktop/resource/contactdata.txt' INTO TABLE contactdata LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/phonenumber.txt' INTO TABLE phone LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/text.txt' INTO TABLE text LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/contacttype.txt' INTO TABLE contacttype LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/time.txt' INTO TABLE time LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/email.txt' INTO TABLE email LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/id.txt' INTO TABLE id LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/agent.txt' INTO TABLE agent LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/customer.txt' INTO TABLE customer LINES TERMINATED BY'\n';
LOAD DATA LOCAL INFILE 'desktop/resource/record.txt' INTO TABLE record LINES TERMINATED BY'\n';

CREATE VIEW view_agent AS
SELECT agent.id,id.idnumber,name.firstname,name.middlename,name.lastname,address.addressline1,address.addressline2,address.city,address.state,address.country,address.zip,email.email,phone.phonenumber
FROM agent,id,name,address,email,phone
WHERE (agent.agentid = id.id AND agent.name = name.id AND agent.address = address.id AND agent.phonenumber = phone.id AND agent.email = email.id)



CREATE VIEW view_customer AS
SELECT customer.id,id.idnumber,name.firstname,name.middlename,name.lastname,address.addressline1,address.addressline2,address.city,address.state,address.country,address.zip,email.email,phone.phonenumber
FROM customer,id,name,address,email,phone
WHERE (customer.customerid = id.id AND customer.name = name.id AND customer.address = address.id AND customer.phonenumber = phone.id AND customer.email = email.id)

CREATE VIEW view_record AS
SELECT contacttype.contacttype,contactdata.contactdata,text.textcontent,time.time
FROM record, contacttype,contactdata,text,time
WHERE (record.type = contacttype.id  AND record.data = contactdata.id AND record.textsummary = text.id AND record.time = time.id)

CREATE VIEW view_record_full AS
SELECT record.customerid, record.agentid,contacttype.contacttype,contactdata.contactdata,text.textcontent,time.time
FROM record, contacttype,contactdata,text,time
WHERE (record.type = contacttype.id  AND record.data = contactdata.id AND record.textsummary = text.id AND record.time = time.id)

CREATE VIEW view_agent_new AS
SELECT agent.id,id.idnumber,concat(name.firstname,' ',name.middlename,' ',name.lastname) AS name, concat(address.addressline1,',',address.addressline2,',',address.city,',',address.state,',',address.country,',',address.zip) as address,email.email,phone.phonenumber
FROM agent,id,name,address,email,phone
WHERE (agent.agentid = id.id AND agent.name = name.id AND agent.address = address.id AND agent.phonenumber = phone.id AND agent.email = email.id)



grant all on *.* to 'wqf'@'%' identified by 'wangqifei' with grant option;
flush privileges;
GRANT USAGE ON *.* TO 'inta'@'%' IDENTIFIED BY 'inta' WITH GRANT OPTION;