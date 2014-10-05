/**
 * Created by jethro on 9/20/14.
 */
app.controller("myController", function($scope,$http){
    $scope.agentID = "";
    $scope.agentVerified = false;
    $scope.error_message = "";
    $scope.recordSubmitted = false;

    $scope.verify = function(agent_id){
        ///verification


        $scope.agentID = agent_id;
        $scope.agentVerified = true;
        $scope.error_message = "no error";
    };
    $scope.createRecord = function(attributes){
        $scope.thingsToSubmit = attributes;
        $scope.recordSubmitted = true;
        ///submit
    };
    $scope.creatorReset = function(){
        $scope.recordSubmitted = false;
        $scope.thingsToSubmit = {};
    };



    $scope.test = false;
    $scope.searchComplete = false;
    $scope.updateShow= false;
    $scope.userIDToBeUpdated = "";
    $scope.searchUser = function(user_id){
        $scope.userID = user_id;
        ///unused! $scope.agentID = agent_id || 0;
        ///$scope.agentID = (typeof agent_id === "undefined") ? "0" : agent_id;
        $http.get("http://www.w3schools.com/website/Customers_JSON.php")
            .success(function(response) {$scope.users = response;});
        $scope.searchComplete = true;
    };
    $scope.updateWanted = function(user_id){
        $scope.updateShow= true;
        $scope.userIDToBeUpdated = user_id;

    };
    $scope.updateUser = function(user_id, attributes){
        $scope.newName = attributes.Name;
        $scope.newCountry = attributes.Country;
        $scope.test = true;
    };

    $scope.searcherReset = function(){
        $scope.test = false;
        $scope.searchComplete = false;
        $scope.updateShow= false;
        $scope.userIDToBeUpdated = "";
        $scope.userID = "";
    };
});

