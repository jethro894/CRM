/**
 * Created by jethro on 9/20/14.
 */
function namesController($scope) {
    $scope.names = [
        {name:'Jani',country:'Norway'},
        {name:'Hege',country:'Sweden'},
        {name:'Kai',country:'Denmark'}
    ];
}

function customersController($scope,$http) {
    $http.get("http://www.w3schools.com/website/Customers_JSON.php")
        .success(function(response) {$scope.names = response;});
}