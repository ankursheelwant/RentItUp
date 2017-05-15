<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<script src="<c:url value="/resources/JS/index.js" />"></script>
<link href="<c:url value="/resources/CSS/index.css" />" rel="stylesheet">
<title>Welcome page</title>
</head>
<body ng-app="testNg" ng-controller="calculator" ng-init="fetchAllUsers()">
<div class="container-fluid blueBackground">
	<h2>RentItUp</h2>
</div>
<div class="container">
	<div class="row">
		<div class="col-lg-2">
			<div> Check JS/Angular {{check}}</div>
		</div>
		<div class="col-lg-10">
		<div class="col-lg-4" ng-repeat="prods in productList">
					<h2>{{check}} :: {{prods.productName}}</h2>
					<h3>{{prods.productDescription}}</h3>
					<h3>{{prods.price}}</h3>
					<h4>{{prods.isAvailable}}</h4>
					<input type="button" class="btn btn-primary" value="${prod.productId}">
				</div>
		
			<c:forEach var="prod" items="${productList}">
				<div class="col-lg-4">
					<h2>${prod.productName}</h2>
					<h3>${prod.productDescription}</h3>
					<h3>${prod.price}</h3>
					<h4>${prod.isAvailable}</h4>
					<input type="button" class="btn btn-primary" value="${prod.productId}">
				</div>
	                
	       	</c:forEach>
		</div>
	</div>
</div>
</body>
</html>