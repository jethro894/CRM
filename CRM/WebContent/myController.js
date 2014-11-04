/**
 * Created by jethro on 9/20/14.
 */
app.controller("myController", function($scope,$http){
/*
    $scope.users=[
        {cid:1, aid:10, fName:'Hege',  lName:'Pege', email: 'dl2856@columbia.edu',tel:'646-388-1809'},
        {cid:2, aid:11, fName:'Kim',   lName:'Pim', email: 'hl2788@columbia.edu',  tel:'389-789-1235'},
        {cid:3, aid:12, fName:'Sal',   lName:'Smith',email: 'wx2799@columbia.edu', tel:'672-213-2342'},
        {cid:4, aid:13, fName:'Jack',  lName:'Jones', email: 'rl2178@columbia.edu', tel:'656-432-9472'},
        {cid:5, aid:14, fName:'John',  lName:'Doe', email: 'qq0987@columbia.edu',tel:'213-876-5148'},
        {cid:6, aid:15, fName:'Peter', lName:'Pan', email: 'pp2176@columbia.edu', tel:'895-213-6826'}
    ];
    $scope.records=[
        {rid:11, cid:1, data:'11min13s', type:'Telephone', text:'Item easy to broken'},
        {rid:12, cid:2, data:'00min23s', type:'Telephone', text:'Not what the customer wants'},
        {rid:13, cid:3, data:'43min00s', type:'Telephone', text:'Bad quality'},
        {rid:14, cid:4, data:'01min37s', type:'Telephone', text:'Customer does not like it'},
        {rid:15, cid:5, data:'34lines', type:'E-mail', text:'Do not need it anymore'},
        {rid:16, cid:6, data:'04min15s', type:'Telephone', text:'Not as described'},
        {rid:17, cid:7, data:'07min21s', type:'Telephone', text:'Arrive too late'},
    ];
*/
//user//
	
	$scope.userId="";
	$scope.userFname="";
	$scope.userLname="";
	$scope.userEmail="";
	$scope.userTel="";
	$scope.users={cid:$scope.userId,fName:$scope.userFname,lName:$scope.userLname,email:$scope.userEmail, tel:$scope.userTel};
    $scope.agentID = "";
    $scope.agentVerified = false;
    $scope.error_message = "";
//  验证agent ID
    $scope.verify = function(agent_id){
        $scope.agentID = agent_id;
        var config={params:{agentID:$scope.agentID}};
        $http.get('/CRM/Agent/AgentLogin',config);
        response.success(function(data, status, headers, config) {
        	$scope.agentVerified = true;
        	$scope.error_message = "";
        });
        response.success(function(data, status, headers, config) {
        	$scope.agentVerified = false;
        	$scope.error_message = "no such agent";
        });


        // 用post方法返回agent_id给server, 通过authentication进行验证
        // 返回值(true/false)传给agentVerified
        // error message 传给error_message
    };
// record management
    $scope.recordID="";
    $scope.testRecord=false;
    $scope.searchRecordComplete=false;
    $scope.updateRecordShow= false;
    $scope.recordIDToBeUpdated = "";
    $scope.recordIDToBeSearched = "";
    $scope.thingsToSubmit="";

    $scope.resetSearchRecord=function(){
        $scope.testRecord=false;
        $scope.searchRecordComplete=false;
        $scope.updateRecordShow= false;
        $scope.recordIDToBeUpdated = "";
        $scope.RecordIDToBeSearched = "";
    }
    $scope.searchRecord=function(record_id){
        // code goes here




        // 把record id post 过去, 取回来一个record值
        $scope.recordIDToBeSearched=record_id;
        $scope.searchRecordComplete=true;
    }
    $scope.updateRecordWanted = function(record_id){
        $scope.updateRecordShow= true;
        $scope.recordIDToBeUpdated = record_id;
    };
    $scope.createRecordWanted=function(){
    	var config={params:{agentID:agent_id}};
    	var response=$http.get("/CRM/Customer/CreateRecord",config);
    	response.success(function(data, status, headers, config) {
        	$scope.recordID=data;
        });
    }
    
    
    
    $scope.createRecord = function(record_id, attributes){
    	var data={recordId:record_id,customerAttribute:attributes};
    	var response=$http.post("/CRM/Customer/UpdateRecord",data);
    	response.success(function(data, status, headers, config) {
    		$scope.createRecordMessage="Create Successfully"
        });
    	response.error(function(data, status, headers, config) {
    		$scope.createRecordMessage="Create wrong"
        });



        $scope.thingsToSubmit = attributes;
        $scope.testRecord=true;
    };

// customer management
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
    }
    
    $scope.userIDToBeSearched = user_id;
   
    $scope.customerSearchError="There is no such customer";
	 $scope.customerSearchErrorHide=true;
	
    $scope.searchUser = function(user_id){
    	var config={params:{userID:user_id}};
    	var response=$http.get("/CRM/Customer/RetrieveCustomer",config);
        response.success(function(data, status, headers, config) {
        	var customer=data;//????????????????????????????????
        	$user[0].cid=customer.customerID;
        	$user[0].aid=customer.agentID;
        	$user[0].fName=customer.firstName;
        	$user[0].lName=customer.lastName;
        	$user[0].email=customer.customerEmail;
        	$user[0].tel=customer.customerPhone;
        });
    	
    	response.error(function(data,status,headers,config){
    		var status =status;
    		 $scope.customerSearchErrorHide=false;
    		 
    	});
        $scope.searchComplete = true;
    };
    $scope.updateCustomerWanted = function(user_id){
        $scope.updateCustomerShow= true;
        $scope.userIDToBeUpdated = user_id;
    };
    $scope.createCustomerWanted=function(agent_id){
    	var config={params:{ID:agent_id}};
    	var response=$http.get("/CRM/Customer/CreateCustomer",config);
    	response.success(function(data, status, headers, config) {
        	$scope.userIDToBeCreated=data;
        });




     
        
    }
    $scope.customerUpdateStatus="";
    $scope.updateUser = function(agent_id, user_id, attributes){
    	var data={customerID:user_id,agentId:agent_id,customerAttribute:attribute};
    	var response=$http.post("/CRM/Customer/UpdateCustomer",data);
    	response.success(function(data, status, headers, config) {
    		$scope.customerUpdateStatus="Update Success";
        });
    	response.error(function(data, status, headers, config) {
    		if(status==401)
    			$scope.customerUpdateStatus="Update Error, Can not found such user";
    		else if(status==402)
    			$scope.customerUpdateStatus="this agent doesn't have the permission to update this user";
    		
        });
        $scope.CustomerToSubmit=attributes;
        $scope.testCustomer = true;
    };

});

