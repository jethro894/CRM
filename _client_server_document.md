##Agent to Customer
###1.create:/CRM/Agent/CreateCustomer, post
|client side|server side|
|-----------|-----------|
|data(agentID,fName, lName, email, tel, addressLine1, addressLine2, city, state, country, zipCode)| data(String: if success: userID; else null)|
###2. update: /CRM/Agent/UpdateCustomer, post
|client side|server side|
|-----------|-----------|
|data(agentID,customerID, fName, lName, email, tel, addressLine1, addressLine2, city, state, country, zipCode)|data(boolean: true/false)|
###3. search: /CRM/Agent/RetrieveCustomer, get
|client side|server side|
|-----------|-----------|
|config(userID)|data(if success{customerID, agentID, firstName, lastName, customerEmail, customerPhone,customerAddress(AddressLine1, AddressLine2, City,Country,State,ZipCode)}else{null})|
###4. delete: /CRM/Agent/DeleteCustomer, get
|client side|server side|
|-----------|-----------|
|config(userID)|data(boolean: true/false)|
##Customer to Customer
###1. update: /CRM/Customer/UpdateCustomer, post
|client side|server side|
|-----------|-----------|
|data(agentID,customerID, fName, lName, email, tel, addressLine1, addressLine2, city, state, country, zipCode)|data(boolean: true/false)|
###2. search: /CRM/Customer/RetrieveCustomer, get
|client side|server side|
|-----------|-----------|
|data: userID|data(if success{customerID, agentID, firstName, lastName, customerEmail, customerPhone,customerAddress(AddressLine1, AddressLine2, City,Country,State,ZipCode)}else{null})|
##Agent to Record
###1. create: /CRM/Agent/CreateRecord, post
|client side|server side|
|-----------|-----------|
|data(customerID, agentID, contactType, contactData, TextSummary)|data(if success{data(customerID, agentID, contactType, contactData, TextSummary, RecordID, RecordTime)}else{null})|
###2. update: /CRM/Agent/UpdateRecord, post
|client side|server side|
|-----------|-----------|
|data(RecordID, customerID, agentID, contactType, contactData, TextSummary)|data((if success{data(customerID, agentID, contactType, contactData, TextSummary, RecordID, RecordTime)}else{null})|
###3. search: /CRM/Agent/UpdateRecord, get
|client side|server side|
|-----------|-----------|
|config: recordID|data((if success{data(customerID, agentID, contactType, contactData, TextSummary, RecordID, RecordTime)}else{null})|
###4. delete: /CRM/Agent/DeleteCustomer, get
|client side|server side|
|-----------|-----------|
|config(recordID)|data(boolean: true/false)|
##Agent to Agent:
###1.verify
|client side|server side|
|-----------|-----------|
|config: agentID|data(boolean: true/false)|


