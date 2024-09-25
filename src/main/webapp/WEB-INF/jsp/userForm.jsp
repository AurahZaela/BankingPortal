<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Form</title>
</head>
<body>

<div  align="center">
<table>
<tr>
<td><a href="accountForm?pageNo=0">Account Form | </a></td>
<td><a href="branchForm?pageNo=0">Branch Form | </a></td>
<td><a href="bankTransactionForm?pageNo=0">Bank Transaction Form | </a></td>
<td><a href="customerForm?pageNo=0">Customer Form | </a></td>
<td><a href="roleForm?pageNo=0">Role Form | </a></td>
<td><a href="home">Home | </a></td>

</tr>
</table>
</div>

<div align="center">

    <h1>User Form</h1>
    <f:form action="saveUser" modelAttribute="user">
        <table border="1">
            <tr>
                <td>User Id: </td>
                <td>
                    <f:input path="userId" value="${u.getUserId()}"/>
                </td>
                <td><f:errors path="userId"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>Name:</td>
                <td><f:input path="username" value="${u.getUsername()}"/></td>
                <td><f:errors path="username"  cssStyle="color:red;"></f:errors></td>
            </tr>


            <tr>
                <td>Password:</td>
                <td><f:input path="password" value=""/></td>
                <td><f:errors path="password"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>Email:</td>
                <td><f:input path="email" value="${u.getEmail()}"/></td>
                <td><f:errors path="email"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>Roles:</td>
                <td>
                    <c:forEach items="${roles}" var="role">
                        <c:if test="${retriedRoles.contains(role)}">
                            <f:checkbox path="roles" label="${role.getRoleName() }" value="${role.getRoleId() }" checked="true"/>
                        </c:if>

                        <c:if test="${! retriedRoles.contains(role)}">
                            <f:checkbox path="roles" label="${role.getRoleName() }" value="${role.getRoleId() }"/>
                        </c:if>
                    </c:forEach>

                </td>
                <td><f:errors path="roles"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="submit"/></td>
            </tr>
        </table>

    </f:form>

    <p/>
    <h1>List of Users</h1>

    <table border="1">
        <tr>
            <th>User id</th> <th>Name</th> <th>Password</th> <th>Email</th> <th>Roles</th>
        </tr>

        <tr>
            <c:forEach items="${users}" var="user">
            <td>${user.getUserId()}</td>
            <td>${user.getUsername()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getEmail()}</td>
            <td>
                <c:forEach items="${user.getRoles()}" var="role">
                    ${role.getRoleName() }
                </c:forEach>
            </td>
            <td>
                <a href="deleteUser?userId=${user.getUserId()}">Delete</a>
                <a href="updateUser?userId=${user.getUserId()}">Update</a>
            </td>
            
        </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>