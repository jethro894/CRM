/**
 * Created by jethro on 9/20/14.
 */
app.controller("namesController", function($scope){
    $scope.names = [
        {name:'Jani',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}
    ];
});

app.controller("customersController", function($scope,$http){
    $http.get("http://www.w3schools.com/website/Customers_JSON.php")
        .success(function(response) {$scope.names = response;});
});

app.controller("searcherController", function($scope,$http){
    $scope.test = false;
    $scope.searchComplete = false;
    $scope.updateShow= false;
    $scope.userIDToBeUpdated = "";
    $scope.searchUser = function(user_id){
        $scope.userID = user_id;
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
});