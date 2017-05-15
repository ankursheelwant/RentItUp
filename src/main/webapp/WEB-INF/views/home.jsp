<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <%@ page session="false" %> --%>
<html>
<head>
	<title>Login</title>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"></script>
<script src="resources/index.js"></script>
</head>
<body>

<h2>Register a New User</h2>

<form:form action="adduser.htm" commandName="user" method="post">

<table>
<tr>
    <td>User Name:</td>
    <td><form:input path="userName" size="30" /> <font color="red"><form:errors path="userName"/></font></td>
</tr>

<tr>
    <td>Email:</td>
    <td><form:input path="email" size="30" /> <font color="red"><form:errors path="email"/></font></td>
</tr>


<tr>
    <td>Contact No:</td>
    <td><form:input path="contactNo" size="30" /> <font color="red"><form:errors path="contactNo"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
</tr>

 <tr>
    <td>Address:</td>
    <td><form:input path="address" size="30" /> <font color="red"><form:errors path="address"/></font></td>
</tr> 

<tr>
    <td colspan="2"><input type="submit" value="Create User" /></td>
</tr>
</table>

</form:form>

<div ng-app="testNg" ng-controller="calculator"> Check JS/Angular {{check}}</div>
</body>
</html>
