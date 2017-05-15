var app=angular.module('testNg', []);
app.controller('calculator',['$scope','$http', function($scope, $http){
	$scope.result="";
	$scope.check="success!!";
	$scope.productList;
	
	$scope.fetchAllUsers=function() {
        $http.get('http://localhost:8080/rentItUp/ngcall.htm')
        .then(
                function(response){
                    $scope.productList=response.data;
                }, 
                function(errResponse){
                    console.error('Error while fetching users');
                    
                }
        );
}
}]);