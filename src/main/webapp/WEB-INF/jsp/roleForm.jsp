<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role Form</title>
</head>
<body>

<div  align="center">
<table>
<tr>
<td><a href="accountForm?pageNo=0">Account Form | </a></td>
<td><a href="branchForm?pageNo=0">Branch Form | </a></td>
<td><a href="bankTransactionForm?pageNo=0">Bank Transaction Form | </a></td>
<td><a href="customerForm?pageNo=0">Customer Form | </a></td>
<td><a href="userForm?pageNo=0">User Form | </a></td>
<td><a href="home">Home | </a></td>

</tr>
</table>
</div>

<br>

<div align="center">
<h2>Role Form</h2>
<f:form action="saveRole" method="POST" modelAttribute="role">
        <table border="1">
            <tr>
                <td>Role Id: </td> <td><f:input path="roleId" value="${r.getRoleId()}"/></td>
                <td><f:errors path="roleId" cssStyle="color:red;" ></f:errors></td>
            </tr>

            <tr>
                <td>Role Name:</td>
                <td><f:input  path="roleName" value="${r.getRoleName()}"/></td>
                <td><f:errors path="roleName"  cssStyle="color:red;"></f:errors></td>
            </tr>
            
           <tr>
                <td colspan="2" align="center"><input type="submit" value="submit"/></td>
            </tr>
        </table>

</f:form>

    <p/>
    <h1>List of Roles</h1>

    <table border="1">
        <tr>
            <th>Role ID</th> <th>Role Name</th>
        </tr>

        <tr>
            <c:forEach items="${roles}" var="role">
            <td>${role.getRoleId()}</td>
            <td>${role.getRoleName()}</td>
           
            <td>
                <a href="deleteRole?roleId=${role.getRoleId()}">Delete</a>
                <a href="updateRole?roleId=${role.getRoleId()}">Update</a>
            </td>
        </tr>
        </c:forEach>

    </table>

</div>
</body>
</html>