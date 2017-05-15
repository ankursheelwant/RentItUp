<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
		
<!-- 		<link rel="stylesheet" type="text/css" href="oneShop_Media.css"> -->
<!-- 		<link rel="stylesheet" type="text/css" href="font-awesome.min.css"> -->
<!-- 		<link rel="stylesheet" type="text/css" href="jquery.bootstrap-touchspin.min.css"> -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link href="<c:url value="/resources/CSS/oneShop.css" />" rel="stylesheet">
		<script src="<c:url value="/resources/JS/oneShop.js" />"></script>
		
		<title>RentItUp</title>
		
		
	</head>
	<body ng-app="oneShopApp" ng-controller="employeeCntrl" ng-init="initStore()">
		<div>
			<div class="container-fluid headerBackground">	
				<div class=" container borderToBeRemoved ">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-1 centeredDiv borderToBeRemoved">
<!-- 						<img class="hidden-xs logo" src="images\logo.jpg" alt="logo" /> -->
<!-- 						<img class="hidden-sm hidden-md hidden-lg centeredDiv logo" src="images\logo_small_40x52.jpg" alt="logo" /> -->

						<span class="glyphicon glyphicon-home" ng-click="homeDisplayProduct()"></span>
						<span class="glyphicon glyphicon-upload" ng-show="loggedInUserRole==2" data-toggle="modal" data-target="#upload"></span>
						<span class="glyphicon glyphicon-tasks" ng-show="loggedInUserRole==1" ng-click="pendingProd()"></span>
<!-- 						ng-show="loggedInUserRole==2" -->
						
					</div><!--Logo container div-->
					<div class=" col-lg-9 col-md-9 col-sm-9 col-xs-11 borderToBeRemoved">
						<div class="searchDiv col-lg-9 col-md-9 col-sm-9 col-xs-9 borderToBeRemoved">
							<div class="input-group">
								
								<input type="text" class="form-control" ng-model="searchKey" ng-keyup="searchProduct()" placeholder="What's your choice today?" />
								<div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>
							</div>
						</div>
						<div class="rightDiv col-ld-3 col-md-3 col-sm-3 col-xs-3 borderToBeRemoved">
							<div class="pull-right" ng-click="showCart()">
<!-- 								<span class="glyphicon glyphicon-shopping-cart"></span><span class="cartItem">{{noOfItems}}</span> -->
							</div>
							<div class="pull-right">
								<span class="glyphicon glyphicon-user" data-toggle="modal" data-target="#login"></span>
								<span class="glyphicon glyphicon-ok" ng-if="loginFlag" ng-click="logout()"></span>

							</div>
						</div>
					</div><!--Search and User login container div-->
				</div><!--Header Panel Ends-->
				<div class="modal fade" id="login" role="dialog">
						<div class="modal-dialog">
    
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Let us know who you are!!</h4>
								</div>
								<div class="modal-body">
									<form class="form-signin" ng-submit="checkLogin(user)" ng-show=newUser>
										<h2 class="form-signin-heading">Log in</h2>
										<input type="text" class="form-control" placeholder="Username" ng-model="user.userName" required autofocus>
										<label for="inputPassword" class="sr-only">Password</label>
										<input type="password" id="inputPassword" class="form-control" placeholder="Password" ng-model="user.password" required>
										<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
										
										<h4>New User?: <a href="" ng-click="newUser=!newUser">Click to Sign up</a> </h4>
									</form>
									
									<form class="form-signin" ng-submit="signUp(user)" ng-hide=newUser>
										<h4>Existing User?: <a href="" ng-click="newUser=!newUser">Click to Log In</a> </h4>
										<h2 class="form-signin-heading">Please sign in</h2>
										<select class="form-control" ng-model="user.roleId">
											<option class="form-control" ng-repeat="role in rolesDetails" value="{{role.roleId}}">{{role.roleDescription}}</option>
										</select>
										<label for="inputEmail" class="sr-only">Email address</label>
										<input type="email" id="inputEmail" class="form-control" placeholder="Email address" ng-model="user.email" required>
										<input type="text" class="form-control" placeholder="Username" ng-model="user.userName" required >
										<label for="inputPassword" class="sr-only">Password</label>
										<input type="password" id="inputPassword" class="form-control" placeholder="Password" ng-model="user.password" required>
										<input type="tel"  pattern="\d{10}" class="form-control" placeholder="Contact No" ng-model="user.contactNo" required>
<!-- 										pattern="[\+]\d{2}[\(]\d{2}[\)]\d{4}[\-]\d{4}" -->
										<input type="text"  class="form-control" placeholder="Address" ng-model="user.address" required>			
<!-- 										<div class="checkbox"> -->
<!-- 										  <label> -->
<!-- 											<input type="checkbox" value="remember-me"> Remember me -->
<!-- 										  </label> -->
<!-- 										</div> -->
										<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
									</form>
									
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>
						  
						</div>
				  </div>
				  
<!-- 				  UPLOAD PRODUCT POP-UP -->
				  <div class="modal fade" id="upload" role="dialog">
						<div class="modal-dialog">
    
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Upload your product!!</h4>
								</div>
								<div class="modal-body">
									<form class="form-signin" ng-submit="upload(product)">
										
										<h2 class="form-signin-heading">Upload Product</h2>
										<select class="form-control" ng-model="product.category" autofocus>
											<option class="form-control" ng-repeat="cate in productCategory" value="{{cate}}">{{cate}}</option>
										</select>
										
										<input type="text" id="prodName" class="form-control" placeholder="Product Name" ng-model="product.productName" required>
										<input type="text" class="form-control" placeholder="Product Description" ng-model="product.productDescription" required >
										<input type="text" pattern="[0-9]*\.?[0-9]*" class="form-control" placeholder="Price" ng-model="product.price" required>
										
										<button class="btn btn-lg btn-primary btn-block" type="submit">Upload<i class="glyphicon glyphicon-cloud-upload"></i></button>
									</form>
									
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>
						  
						</div>
				  </div>
			</div>
			<div class="container">
				<div class="contentMargin" ng-if="displayProduct==1"> <!-- show only if displayProduct is 1, show cart if 0-->
					
						<div class="hidden-xs col-lg-3 col-md-3 col-sm-3">
							<ul class="nav nav-pills nav-stacked">
								<li ng-click="getProductsOfType()"><a href="#" class="h4">Show All: ${sessionScope.user.userName}</a></li>
								<li ng-repeat="prod in productCatalog |unique:'category'" ng-click="getProductsOfType(prod)"><a href="#" class="h4">{{prod.category}}</a></li>
							</ul>
						</div>
						<div class="hidden-lg hidden-md hidden-sm col-xs-12">
							<ul class="nav nav-pills">
								<li ng-click="getProductsOfType()"><a href="#" class="h4">Show All</a></li>
								<li ng-repeat="prod in productCatalog |unique:'category'" ng-click="getProductsOfType(prod)"><a href="#" class="h4" >{{prod.category}}</a></li>
							</ul>
						</div>
					<!--Vertical tabs div ends-->
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
					<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class=" col-lg-4 col-md-4 col-sm-6 col-xs-12 containerBorder" ng-repeat="prod in filteredProduct">
							<div class="card cardBorder" >
							
<%-- 								<img class="card-img-top" ng-src="<c:url value="/resources/images/BMW.jpg" />" height="250" width="250" alt="{{prod.productId}}"/> --%>
								<div class="card-block">
									<h4 class="card-title ">{{prod.productName}}</h4>
									<p class="card-text">{{prod.productDescription}}</p>
									<p class="card-text"><span>Price:{{prod.price}}<i class="glyphicon glyphicon-tag"></i></span></p>
									<div class="">
										<span>
										<label>Select Quantity</label>	
										<input type="number" id="spinner-1" min="1" value="1" placeholder="1" ng-model="prod.quantity" />
										
										</span>
										
										<a href="#" class="btn btn-primary" ng-click="addToCart(prod, prod.quantity)">Borrow</a>	
									</div>
								</div>
							</div>
						</div>
						
					</div>
					</div>
					</div><!--CArd layout container div-->
					
					
					
				</div>

				
				
				<div class="contentMargin" ng-if="displayProduct==0">
					<div class="amountDiv"><a href="#" class="btn btn-primary" ng-disabled="checkCart()" data-toggle="modal" data-target="#placeOrder" ng-click="placeOrder()">Place Order</a>
					</div>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" ng-repeat="prod in cartP">
					
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
<%-- 							<img ng-src="<c:url value="/resources/images/BMW.jpg" />" height="250" width="250" alt="{{prod.productId}}"/> --%>
						</div><!--image-->
						<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
							<div class="card-block">
								<h4 class="card-title ">{{prod.product.productName}}</h4>
								<p class="card-text">Product by {{prod.productName}}</p>
								<p class="card-text"><span>Price:{{prod.product.price}}<i class="glyphicon glyphicon-tag"></i></span> <span> Color:{{prod.p.color}}</span></p>
								<div class="">
									<span>
									<label>Selected Quantity: {{prod.q}}</label><br />
									
									<label>Add Quantity<input type="number" id="spinner-1" min="1" value="1" placeholder="1" ng-model="quant" /></label>
									<a href="#" class="btn btn-primary" ng-click="addToCart(prod.p, quant)">Update</a>	<br />
									
									<label>Total Amount: {{prod.p.price*prod.q}}</label>
									</span>
									
									<div class="amountDiv">
									
<!-- 									<div ng-click="deleteCartItem($index)"><span class="glyphicon glyphicon-trash"></span></div> -->
									
									</div>
								</div>
							</div>
						</div><!--content-->
						
				</div><!--List div-->
				
					<div class="modal fade" id="placeOrder" role="dialog">
						<div class="modal-dialog">
    
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Order Confirmation!!</h4>
								</div>
								<div class="modal-body">
									<p>Order has been placed successfully</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>
						  
						</div>
				  </div>
				  
				  
				</div>
				<div class="contentMargin" ng-if="displayProduct==2">
						<div class="hidden-xs col-lg-3 col-md-3 col-sm-3">
							<ul class="nav nav-pills nav-stacked">
								<li ng-click="getProductsOfType()"><a href="#" class="h4">Show All: ${sessionScope.user.userName}</a></li>
								<li ng-repeat="prod in productCatalog |unique:'category'" ng-click="getProductsOfType(prod)"><a href="#" class="h4">{{prod.category}}</a></li>
							</ul>
						</div>
						<div class="hidden-lg hidden-md hidden-sm col-xs-12">
							<ul class="nav nav-pills">
								<li ng-click="getProductsOfType()"><a href="#" class="h4">Show All</a></li>
								<li ng-repeat="prod in productCatalog |unique:'category'" ng-click="getProductsOfType(prod)"><a href="#" class="h4" >{{prod.category}}</a></li>
							</ul>
						</div>
					<!--Vertical tabs div ends-->
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
					<div class="card-deck-wrapper">
					<div class="card-deck">
						<div class=" col-lg-4 col-md-4 col-sm-6 col-xs-12 containerBorder" ng-repeat="prod in filteredProduct">
							<div class="card cardBorder" >
							
								<img class="card-img-top" ng-src="<c:url value="/resources/images/BMW.jpg" />" height="250" width="250" alt="{{prod.productId}}"/>
								<div class="card-block">
									<h4 class="card-title ">{{prod.productName}}</h4>
									<p class="card-text">{{prod.productDescription}}</p>
									<p class="card-text"><span>Price:{{prod.price}}<i class="glyphicon glyphicon-tag"></i></span></p>
									<div class="">
										
										
										<a href="#" class="btn btn-primary" ng-click="approve(prod)">Approve Product</a>	
									</div>
								</div>
							</div>
						</div>
						
					</div>
					</div>
					</div><!--CArd layout container div-->
				</div>
			</div><!--Body panel ends-->
		</div> <!--Main Container Ends-->
		
		
<!-- 		<script src="jquery.bootstrap-touchspin.min.js"></script> -->
		
	</body>
</html>