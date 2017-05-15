
var app = angular.module('oneShopApp', []);
app.controller('employeeCntrl',['$scope', '$http', '$log', 
	function ($scope, $http, $log) {
	$scope.$log=$log;
	$scope.message = 'Hello World!';
	$scope.productCatalog = [];	
		
	
	$scope.filteredProduct;
//	$scope.filteredProduct.productId;
//	$scope.filteredProduct.companyName;
//	$scope.filteredProduct.productType;
//	$scope.filteredProduct.productName;
//	$scope.filteredProduct.price;
//	$scope.filteredProduct.color;
	
	$scope.firstProductType;
	$scope.searchKey;
	
	$scope.cartItems=[];
	$scope.cartProduct={productId:null, productName:'', productDescription:'', price:null, category:'', isApproved:true, isAvailable:true, quantity:null};
	$scope.cartP={product:[$scope.cartProduct]};
	$scope.cartP1={productName:'Test'};
	//$scope.cartProduct.product;
	//$scope.cartProduct.quantity;
	
	$scope.noOfItems;
	
	$scope.displayProduct;
	
	$scope.loginFlag=false;
	$scope.user;
	$scope.password;
	$scope.newUser=true;
	
	$scope.user={userId:null,userName:'',email:'', contactNo:null, password:'', address:'', roleId:null, roleDescription:''};
	
	$scope.product={productId:null, productName:'', productDescription:'', price:null, category:'', isAvailable:true};
	$scope.productCategory=['Vehicle', 'Mobile', 'Laptop', 'Television', 'Other'];
	
	$scope.check="Success Angular"
	
	$scope.initStore=function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/rentItUp/ngcall.htm'
			}).then(function successCallback(response) {
				// this callback will be called asynchronously
				// when the response is available
				
				$scope.productCatalog=response.data;
				//$scope.rolesDetails=response.data.roles;
				$scope.filteredProduct=$scope.productCatalog;
				
				$scope.fetchRoles();
				
				$scope.checkSession();
				},
				function errorCallback(response) {
					
					$log.log(response);
				// called asynchronously if an error occurs
				// or server returns response with an error status.
			  });
		$scope.displayProduct=1;
	}
	$scope.fetchRoles=function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/rentItUp/getRoles.htm'
			}).then(function successCallback(response) {
				// this callback will be called asynchronously
				// when the response is available
				
				
				$scope.rolesDetails=response.data;
				
				},
				function errorCallback(response) {
					
					$log.log(response);
				// called asynchronously if an error occurs
				// or server returns response with an error status.
			  });
	}
	$scope.checkSession=function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/rentItUp/checkSession.htm'
			}).then(function successCallback(response) {
				
				if(response.status==200)
					{
					$scope.loginFlag=true;
					$scope.loggedInUserRole=response.data.roleId;
					}
				
				},
				function errorCallback(response) {
					
					$log.log(response);
				
			  });
	}
	
	$scope.upload=function(product)
	{
		if($scope.loginFlag && $scope.loggedInUserRole==2)
			{
			$http.post('http://localhost:8080/rentItUp/upload.htm', product)
			.then(function successCallback(response) {
					// this callback will be called asynchronously
					// when the response is available
				
					if(response.status==200)
						{
						alert("Product uploaded successfully!!");
						}
					
					},
					function errorCallback(response) {
						if(response.status==409)
						{
						alert("Same user exists!!");
						}
						if(response.status==400)
						{
						alert("All fields are required!!");
						}
						//$log.log(response);
					// called asynchronously if an error occurs
					// or server returns response with an error status.
				  });
			}
		else
			{
			alert("Please log in first!!");
			}
	}
	$scope.getProductsOfType=function(prod)
	{
		$http({
			method: 'GET',
			url: 'ProductCatalog.json'
			}).then(function successCallback(response) {
				$scope.productCatalog=response.data;
				});
					
		if(angular.isUndefined(prod))
		{
			$scope.filteredProduct=$scope.productCatalog;
		}
		else
		{
			$scope.filteredProductResult=[];
			angular.forEach($scope.productCatalog, function(item,i){
				if(item.category==prod.category)
				{
					$scope.filteredProductResult.push(item);
				}
			});
			$scope.filteredProduct=$scope.filteredProductResult;
		}
	}
	
	$scope.searchProduct=function()
	{
		$http({
			method: 'GET',
			url: 'ProductCatalog.json'
			}).then(function successCallback(response) {
				$scope.productCatalog=response.data;
				});
				
		$scope.filteredProductResult=[];
		if(!angular.isUndefined($scope.searchKey))
		{
			angular.forEach($scope.productCatalog, function(item,i){
				if(item.productName.toLowerCase().indexOf($scope.searchKey.toLowerCase())>=0 || item.productName.toUpperCase().indexOf($scope.searchKey.toUpperCase())>=0)
				{
					$scope.filteredProductResult.push(item);
				}
			});
			$scope.filteredProduct=$scope.filteredProductResult;
		}
		
	}
	
	$scope.addToCart=function(prod, quant)
	{
		//alert("In add to cart function!!");
		if($scope.loginFlag && $scope.loggedInUserRole==3)
			{
				//alert("Login done and User authorised");
				var flagAvail=0;
				if(angular.isUndefined(quant))
				{
					quant=1;
				}
				
				//check if product is already available in cart...just update the quantity
				if($scope.cartProduct.length>0)
					{
						angular.forEach($scope.cartProduct, function(item,i){
							if(item.product.productId==prod.productId)
							{
								var tempQ=item.q;
								tempQ += quant;
								item.q=tempQ;
								flagAvail=1;
							}
						});
					}
				//Add product to cart with specified quantity
				if(flagAvail==0)
				{	
					//$scope.cartProduct.push({product: prod, quantity:quant});
					
					$scope.cartProduct={productId: prod.productId,productName:prod.productName, productDescription:prod.productDescription, price:prod.price, isAvailable:prod.isAvailable, category:prod.category, approve:prod.approved, quantity:quant};
					var arr={productId: prod.productId,productName:prod.productName, productDescription:prod.productDescription, price:prod.price, isAvailable:prod.isAvailable, category:prod.category, approve:prod.approved, quantity:quant};
					$scope.cartP.product.push(arr);
					$scope.cartP1=$scope.filteredProduct;
//					$scope.cartProduct.productId= prod.productId;
//					$scope.cartProduct.productName=prod.productName;
//					$scope.cartProduct.productDescription=prod.productDescription;
//					$scope.cartProduct.price=prod.price;
//					$scope.cartProduct.isAvailable=prod.isAvailable;
//					$scope.cartProduct.category=prod.category;
//					//$scope.cartProduct.available=prod.available;
//					$scope.cartProduct.approved=prod.approved;
//					
//					$scope.cartProduct.quantity=quant;
//					
					$http.post('http://localhost:8080/rentItUp/addtocart.htm', $scope.cartProduct)
					.then(function successCallback(response) {
							// this callback will be called asynchronously
							// when the response is available
						
							if(response.status==200)
								{
								alert("Product borrowed successfully!!");
								}
							
							},
							function errorCallback(response) {
								if(response.status==409)
								{
								alert("Same user exists!!");
								}
								if(response.status==400)
								{
								alert("All fields are required to borrow!!");
								}
								//$log.log(response);
							// called asynchronously if an error occurs
							// or server returns response with an error status.
						  });
				}
			}
		//alert("Unauthorised user!!");
		$scope.noOfItems=$scope.cartProduct.length;

	}
	
	$scope.showCart=function()
	{
		$scope.displayProduct=0;
	}
	$scope.homeDisplayProduct=function()
	{
		$scope.displayProduct=1;
	}
	$scope.deleteCartItem=function(index)
	{
		$scope.cartProduct.splice(index,1);
		$scope.noOfItems=$scope.cartProduct.length;
	}
	$scope.checkCart=function()
	{
		if($scope.noOfItems<1 || angular.isUndefined($scope.noOfItems))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	$scope.signUp=function(user)
	{
		$http.post('http://localhost:8080/rentItUp/signup.htm', user)
		.then(function successCallback(response) {
				// this callback will be called asynchronously
				// when the response is available
			
				$scope.loginFlag=true;
				$scope.loggedInUserRole=response.data;
				$scope.user={userId:null,userName:'',email:'', contactNo:null, password:'', address:'', roleId:null};
				
				//$scope.productCatalog=response.data;
				//$scope.filteredProduct=$scope.productCatalog;
				
				},
				function errorCallback(response) {
					if(response.status==409)
					{
					alert("Same user exists!!");
					}
					if(response.status==400)
					{
					alert("All fields are required!!");
					}
					//$log.log(response);
				// called asynchronously if an error occurs
				// or server returns response with an error status.
			  });		
	}
	$scope.checkLogin=function(user)
	{
		$http.post('http://localhost:8080/rentItUp/checkLogin.htm', user)
		.then(function success(response){
			//alert("Login Successful: "+response.status);
			if(response.status==200)
				{
				
				$scope.loginDone(response.data.userId);
				$scope.user={userId:null,userName:'',email:'', contactNo:null, password:'', address:''};
				}
		},
		function error(response)
		{
			alert("Login failed!!");
		});
	}
	$scope.loginDone=function(userId)
	{
		//$scope.initStore();
		$scope.loginFlag=true;
		$http.post('http://localhost:8080/rentItUp/fetchRole.htm', userId)
		.then(function success(response){
			//alert("Login Successful: "+response.status);
			if(response.status==200)
			{
				$scope.loggedInUserRole=response.data;
			}
		},
		function error(response)
		{
			//alert("Role fetching failed");
		});
	
	}
	$scope.logout=function(){
		
		
		$http.post('http://localhost:8080/rentItUp/logout.htm')
		.then(function success(response){
			//alert("Login Successful: "+response.status);
			if(response.status==200)
			{
				$scope.loginFlag=false;
				$scope.loggedInUserRole=null;
			}
		},
		function error(response)
		{
			//alert("Role fetching failed");
		});
		
	}
	$scope.pendingProd=function(){
		$scope.displayProduct=2;
	}
	$scope.placeOrder=function()
	{
		var index=0;
		angular.forEach($scope.cartProduct, function(item,i){
			$scope.cartProduct.splice(index,1);
			
			//index=index+1;
		});
		$scope.cartProduct.splice(index,1);
		$scope.noOfItems=$scope.cartProduct.length;
	}
	
}]);
app.filter('unique', function () {
    return function (collection, keyname) {
        var output = [], keys = [];

        angular.forEach(collection, function (item) {
            var key = item[keyname];
            if (keys.indexOf(key) === -1) {
                keys.push(key);
                output.push(item);
            }
        });

        return output;
    };
});