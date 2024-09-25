<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

<div  align="center">
<%-- <br>principal.username: <sec:authentication property="principal.username"/>
<br>principal.authorities: <sec:authentication property="principal.authorities"/> --%>
<table>
<tr>
<td><a href="accountForm?pageNo=0">Account Form | </a></td>
<td><a href="branchForm?pageNo=0">Branches | </a></td>
<td><a href="bankTransactionForm?pageNo=0">Bank Transaction Form | </a></td>
<td><a href="customerForm?pageNo=0">Customer Form | </a></td>
<td><a href="roleForm?pageNo=0">Role Form | </a></td>
<td><a href="userForm?pageNo=0">User Form | </a></td>
<td><a href="home">Home | </a></td>

<sec:authorize access="isAuthenticated">
<td><a href="logout">Logout</a></td>
</sec:authorize>
</tr>
</table>
</div>

</body>
</html>