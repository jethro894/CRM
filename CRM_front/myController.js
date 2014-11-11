/**
 * Created by jethro on 9/20/14.
 */
app.controller("myController", function($scope,$http){

    /*global variables*/
    $scope.agentID = "";
    $scope.agentVerified = false;
    $scope.error_message = "";

    /*returned info*/
    $scope.users={};
    $scope.records={};

    /*customer to  customer*/
    /*search customer*/
    $scope.errorMessage="";
    $scope.errorShow=false;
    $scope.userSearchUser=function(user_id){
    	//$scope.users={};
        var config={params:{userID:user_id}};
        var response=$http.get('/CRMbyJAX-RS/CRM/Customer/RetrieveCustomer', config);
        response.success(function(data,statuc,headers,config){
        	$scope.users=data;
            if(data.customerID==null){
                $scope.errorMessage="No such customer...";
                $scope.errorShow=true;
            }else{
                $scope.errorShow=false;
                $scope.errorMessage="";
                $scope.users=data;
                $scope.userSearchComplete = true;
            }
        });
    };
    $scope.u2uUpdateShow=false;
    /*pre-update customer */
    $scope.userUpdateUserWanted=function(user_id, agent_id){
        $scope.u2uUserID=user_id;
        $scope.u2uAgentID=agent_id;
        $scope.u2uUpdateShow=true;
    };
    
    /*update customer*/
    $scope.userUpdateUser=function(user_id, agent_id, attribute){
    	$scope.data1={};
        $scope.data1.customerID=user_id;
        $scope.data1.agentID=agent_id;
        $scope.data1.fName=attribute.fName;
        $scope.data1.lName=attribute.lName;
        $scope.data1.email=attribute.email;
        $scope.data1.tel=attribute.tel;
        $scope.data1.addressLine1=attribute.addressLine1;
        $scope.data1.addressLine2=attribute.addressLine2;
        $scope.data1.city=attribute.city;
        $scope.data1.state=attribute.state;
        $scope.data1.country=attribute.country;
        $scope.data1.zipCode=attribute.zipCode;
        var data=$scope.data1;
        var response=$http.post("/CRMbyJAX-RS/CRM/Customer/UpdateCustomer",data);
        response.success(function(data, status, headers, config) {
            if(data.success==true){
                $scope.Message="Update succeeded!";
                $scope.testCustomer=true;
            }else if(data.success==false){
                $scope.Message="Update failed, please try again...";
            }
            else{
                $scope.Message="Update failed, please try again...";
            }
        });
    };
    /*agent to agent*/
    /*verify agentID*/
    $scope.verify = function(agent_id){
        $scope.agentID = agent_id;
        var config={params:{agentID:$scope.agentID}};
        var response=$http.get('/CRMbyJAX-RS/CRM/Agent/AgentLogin',config);
        response.success(function(data, status, headers, config) {
        	var ifexist=data;
            if(ifexist.success){$scope.error_message = "";}
            else{$scope.error_message="Agent ID does not exist, please try again...";}
            $scope.agentVerified = data;
        });
        /*response.error(function(data, status, headers, config) {
            $scope.agentVerified = false;
            $scope.error_message = "no such agent";
        });*/
    };
    /*agent to customer*/
    /*record management*/
    $scope.recordID="";
    $scope.testRecord=false;
    $scope.searchRecordComplete=false;
    $scope.updateRecordShow= false;
    $scope.recordIDToBeUpdated = "";
    $scope.recordIDToBeSearched = "";
    //$scope.thingsToSubmit="";
    $scope.resetMessage=function(){
    	$scope.errorMessage="";
    };
    
    $scope.resetSearchRecord=function(){
        $scope.testRecord=false;
        $scope.searchRecordComplete=false;
        $scope.updateRecordShow= false;
        $scope.recordIDToBeUpdated = "";
        $scope.RecordIDToBeSearched = "";
    };

    /*create record*/

    $scope.recordInfo={};
    $scope.createRecordWanted=function(){
        $scope.recordID="";
    };
    $scope.createRecord = function(agent_id, attributes){
        //$scope.errorMessage="";
        $scope.recordInfo.agentID=agent_id;
        $scope.recordInfo.customerID=attributes.customerID;
        $scope.recordInfo.contactType=attributes.contactType;
        $scope.recordInfo.contactData=attributes.contactData;
        $scope.recordInfo.TextSummary=attributes.TextSummary;
        var data = $scope.recordInfo;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/CreateRecord",data);
        response.success(function(data, status, headers, config) {
        	$scope.records=data;
            if(data.RecordID==null){
                $scope.errorMessage="Create Failed! Customer ID or Agent ID does not exist!";
            }else{
                $scope.errorMessage="Create Successfully";
                //$scope.RecordID=data.RecordID;
                //$scope.RecordTime=data.RecordID;
            }
            $scope.testRecord=true;
        });
    };
    /*search record*/
    $scope.searchRecord=function(record_id){
    	$scope.searchRecordComplete=true;
        $scope.errorMessage="";
        $scope.recordIDToBeSearched=record_id;
        var config={params:{recordID:record_id}};
        var response=$http.get("/CRMbyJAX-RS/CRM/Agent/RetrieveRecord", config);
        response.success(function(data,status,headers,config){
            if(data.RecordID==null){
                $scope.searchRecordComplete=false;
                $scope.errorMessage="No such record...";
            }else{
                $scope.searchRecordComplete=true;
                $scope.errorMessage="";
                $scope.records=data; 
            }
        });
    };
    /*update record*/
    $scope.updateRecordWanted = function(record_id){
        $scope.updateRecordShow= true;
        $scope.recordIDToBeUpdated = record_id;
    };
    $scope.updateRecord=function(record_id, agent_id, attributes){
        $scope.errorMessage="";
        $scope.recordInfo={};
        $scope.recordInfo.RecordID=record_id;
        $scope.recordInfo.customerID=attributes.customerID;
        $scope.recordInfo.agentID=agent_id;
        $scope.recordInfo.contactType=attributes.contactType;
        $scope.recordInfo.contactData=attributes.contactData;
        $scope.recordInfo.TextSummary=attributes.TextSummary;
        var data=$scope.recordInfo;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/UpdateRecord",data);
        response.success(function(data, status, headers, config) {
        	$scope.myRecord=data;
            if(data.RecordID==null){
                $scope.errorMessage="Create Failed! Customer ID or Agent ID does not exist!";
            }else{
                $scope.errorMessage="Create Successfully";
            }
            $scope.testRecord=true;
        });
    };
    /*delete record*/
    $scope.deleteRecord=function(record_id){
        $scope.errorMessage="";
        var config={params:{recordID:record_id}};
        var response=$http.get("/CRMbyJAX-RS/CRM/Agent/DeleteRecord", config);
        response.success(function(data,status,headers,config){
            var r=data;
        	if(r.success==false){
                $scope.errorMessage="No such record...";
            }else if(r.success==true){
            	$scope.errorMessage="Delete succeeded!";
                //$scope.errorMessage="";
            }else{
            	$scope.errorMessage="ERROR";
            }
        });
    };

    /*customer management*/
    $scope.testCustomer = false;
    $scope.searchComplete = false;
    $scope.updateCustomerShow= false;
    $scope.createShow=false;
    $scope.userIDToBeUpdated = "";

    $scope.resetSearchCustomer=function(){
        $scope.testCustomer = false;
        $scope.searchComplete = false;
        $scope.updateCustomerShow= false;
        $scope.userIDToBeUpdated = "";
        $scope.userIDToBeSearched = "";
    };    
    $scope.searchUser = function(user_id){
    	//alert(user_id);
        $scope.errorMessage="";
        $scope.userIDToBeSearched = user_id;
        var config={params:{userID:user_id}};
        //var data=user_id;
        var response=$http.get("/CRMbyJAX-RS/CRM/Agent/RetrieveCustomer",config);
        //var response=$http.post("CRM/Agent/RetrieveCustomer",data);
        //alert("test1");
        response.success(function(data,status,headers,config){
            $scope.users=data;
            $scope.address=data.customerAddress;
            if(data.customerID==null){
                $scope.errorMessage="No such user, please try again...";
            }else{
                $scope.errorMessage="";
                $scope.searchComplete = true;
            }
        });
    };
    $scope.updateCustomerWanted = function(user_id){
        $scope.updateCustomerShow= true;
        $scope.userIDToBeUpdated = user_id;
    };
    $scope.updateUser = function(agent_id, user_id, attributes){
        $scope.errorMessage="";
        $scope.newCustomerInfo={};
        $scope.newCustomerInfo.agentID=agent_id;
        $scope.newCustomerInfo.customerID=user_id;
        $scope.newCustomerInfo.fName=attributes.fName;
        $scope.newCustomerInfo.lName=attributes.lName;
        $scope.newCustomerInfo.email=attributes.email;
        $scope.newCustomerInfo.tel=attributes.tel;
        $scope.newCustomerInfo.addressLine1=attributes.addressLine1;
        $scope.newCustomerInfo.addressLine2=attributes.addressLine2;
        $scope.newCustomerInfo.city=attributes.city;
        $scope.newCustomerInfo.state=attributes.state;
        $scope.newCustomerInfo.country=attributes.country;
        $scope.newCustomerInfo.zipCode=attributes.zipCode;
        var data=$scope.newCustomerInfo;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/UpdateCustomer",data);
        response.success(function(data,status,headers,config){
           if(data.success==true){
               $scope.errorMessage3="";
               $scope.testCustomer = true;
           }else if(data.success==false){
               $scope.errorMessage3="Sorry, update failed, please try again...";
           }
           else{
               $scope.errorMessage3="Sorry, update failed, please try again...";
           }
        });
    };
    $scope.createCustomerWanted=function(){
        $scope.userIDToBeCreated= "";
    };
    $scope.createUser=function(agent_id, attributes){
        $scope.errorMessage="";
        $scope.customerInfo={};
        $scope.customerInfo.agentID=agent_id;
        $scope.customerInfo.fName=attributes.fName;
        $scope.customerInfo.lName=attributes.lName;
        $scope.customerInfo.email=attributes.email;
        $scope.customerInfo.tel=attributes.tel;
        $scope.customerInfo.addressLine1=attributes.addressLine1;
        $scope.customerInfo.addressLine2=attributes.addressLine2;
        $scope.customerInfo.city=attributes.city;
        $scope.customerInfo.state=attributes.state;
        $scope.customerInfo.country=attributes.country;
        $scope.customerInfo.zipCode=attributes.zipCode;
        var data=$scope.customerInfo;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/CreateCustomer",data);
        response.success(function(data, status, headers, config) {
            $scope.userIDToBeCreated=data;
            if(data.customerID=null){
                $scope.errorMessage="Creation failed, please try again...";
            }else{
                $scope.errorMessage="Creation Successfully";
            }
            $scope.testCustomer2=true;
        });
    };
    $scope.deleteCustomer=function(user_id){
        $scope.errorMessage="";
        var config={params:{userID:user_id}};
        var response=$http.get("/CRMbyJAX-RS/CRM/Agent/DeleteCustomer", config);
        response.success(function(data,status,headers,config){
        	var r=data;
        	alert(data.success);
            if(r.success==false){
                $scope.errorMessage="Deletion failed, please try it again...";
            }else if(r.success==true){
                $scope.errorMessage="Deletion succeeded!";
            }else{
            	$scope.errorMessage=data.success;
            }
        });
    };
    $scope.createSub=function(attributes){
        $scope.errorMessage="";
        $scope.subscriptionInfo={};
        $scope.subscriptionInfo.agentID=attributes.agentID;
        $scope.subscriptionInfo.zipCode=attributes.zipCode;
        $scope.subscriptionInfo.notificationType=attributes.notificationType;
        var data=$scope.subscriptionInfo;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/CreateSubscription", data);
        response.success(function(data,status,headers,config){
            if(data==null){
                $scope.errorMessage="sorry, creation failed, please try again...";
            }else{
                $scope.errorMessage="";
                $scope.subscriptionID = data;
                $scope.createSubscriptionShow=true;
            }
        });
    };
    $scope.searchSub=function(attributes){
//        $scope.subscriptionSet=[];
        $scope.errorMessage="";
        $scope.subscriptionInfo2={};
        $scope.subscriptionInfo2.subscriptionID=attributes.subscriptionID;
        $scope.subscriptionInfo2.agentID=attributes.agentID;
        $scope.subscriptionInfo2.zipCode=attributes.zipCode;
        $scope.subscriptionInfo2.notificationType=attributes.notificationType;
        var data=$scope.subscriptionInfo2;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/RetrieveSubscription",data);
        response.success(function(data,status,headers,config){
            if(data.length==0){
                $scope.errorMessage="No such record, please try again...";
            }else{
                $scope.errorMessage="";
            }
        });
    };
    $scope.updateSubPre=function(s_id){
        $scope.sIDUpdated=s_id;
    };
//    $scope.deleteSubPre=function(s_id){
//        $scope.sIDDeleted=s_id;
//    };
    $scope.updateSub=function(attributes){
        $scope.subInfo3={};
        $scope.subInfo3.subscriptionID=$scope.sIDUpdated;
        $scope.subInfo3.agentID=attributes.agentID;
        $scope.subInfo3.zipCode=attributes.zipCode;
        $scope.subInfo3.notificationType=attributes.notificationType;
        var data=$scope.subInfo3;
        var response=$http.post("/CRMbyJAX-RS/CRM/Agent/UpdateSubscription",data);
        response.success(function(data,status,headers,config){
            if(data.success==true){
                $scope.errorMessage="update succeeded!";
            }else if(data.success==false){
                $scope.errorMessage="update failed";
            }else{
                $scope.errorMessage="ERROR";
            }
        });
    };
    $scope.deleteSub=function(s_id){
        $scope.errorMessage="";
        var config={params:{subscriptionID:s_id}};
        var response=$http.get("/CRMbyJAX-RS/CRM/Agent/DeleteSubscription", config);
        response.success(function(data,status,headers,config){
            //var r=data;
            //alert(data.success);
            if(data.success==false){
                $scope.errorMessage="Deletion failed, please try it again...";
            }else if(data.success==true){
                $scope.errorMessage="Deletion succeeded!";
            }else{
                //$scope.errorMessage=data.success;
                $scope.errorMessage="ERROR";
            }
        });
    };
});

