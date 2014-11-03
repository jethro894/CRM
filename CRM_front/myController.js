/**
 * Created by jethro on 9/20/14.
 */
app.controller("myController", function($scope,$http){

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




    $scope.agentID = "";
    $scope.agentVerified = false;
    $scope.error_message = "";
//  验证agent ID
    $scope.verify = function(agent_id){
        $scope.agentID = agent_id;



        // 用post方法返回agent_id给server, 通过authentication进行验证
        // 返回值(true/false)传给agentVerified
        // error message 传给error_message
        $scope.agentVerified = true;
        $scope.error_message = "no error";
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
        // code goes here




        // 需要从服务器取回一个分配的recordID
        // 不需要传递agentID
        $scope.recordID="456";
    }
// 创建record
    $scope.createRecord = function(record_id, attributes){
        // code goes here




        //需要传递 attribute和record_id
        $scope.thingsToSubmit = attributes;
        // 需要把create 的record的值用post 传给server
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
    $scope.searchUser = function(user_id){
        $scope.userIDToBeSearched = user_id;
        // code goes here






        // 要将agentID和userIDToBeSearched一起回传，确定有权限的情况下才能够返回信息，否则
        // 报error
        // 返回来的信息存在users里
        ///unused! $scope.agentID = agent_id || 0;
        ///$scope.agentID = (typeof agent_id === "undefined") ? "0" : agent_id;
        // 需要用get方法返回customer的数据, 传入user中
//        $http.get("http://www.w3schools.com/website/Customers_JSON.php")
//            .success(function(response) {$scope.users = response;});
        $scope.searchComplete = true;
    };
    $scope.updateCustomerWanted = function(user_id){
        $scope.updateCustomerShow= true;
        $scope.userIDToBeUpdated = user_id;
    };
    $scope.createCustomerWanted=function(){
        // code goes here...




        // 此处需要将 agentID 传回至服务器， 与userID 共同存入表中
        $scope.userIDToBeCreated= 123;
        //        此处需要向服务器发回一个请求取回userID
        //        $scope.createCustomerShow=true;
    }
    $scope.updateUser = function(agent_id, user_id, attributes){
        // code goes here...





        // agent_id, user_id也要传过去
        // userIDToBeUpdate就是user_id
        $scope.CustomerToSubmit=attributes;
        // 把update的record的值用post传给server
//        $scope.newName = attributes.Name;
//        $scope.newCountry = attributes.Country;
        $scope.testCustomer = true;
    };

});

