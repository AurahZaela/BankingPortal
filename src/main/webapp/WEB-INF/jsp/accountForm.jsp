<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Form</title>

</head>
<body>
<div  align="center">

<table>
<tr>
<td><a href="branchForm?pageNo=0">Branch Form | </a></td>
<td><a href="bankTransactionForm?pageNo=0">Bank Transaction Form | </a></td>
<td><a href="customerForm?pageNo=0">Customer Form | </a></td>
<td><a href="roleForm?pageNo=0">Role Form | </a></td>
<td><a href="userForm?pageNo=0">User Form | </a></td>
<td><a href="home">Home | </a></td>

<%-- <sec:authorize access="isAuthenticated"> --%>
<!-- <td><a href="logout">Logout</a></td> -->
<%-- </sec:authorize> --%>
</tr>
</table>
</div>

<br>

<div align="center">
    <h2>Account Form</h2>
    <f:form action="saveAccount" method="POST" modelAttribute="account">
        <table border="1">
            <tr>
                <td>Account ID</td><td><f:input type="text" path="accountId" value="${account.getAccountId()}"/> </td>
                <td><f:errors path="accountId" cssStyle="color:red;" ></f:errors></td>
            </tr>
            <tr>
                <td>Account Holder</td><td><f:input type="text" path="accountHolder" value="${account.getAccountHolder()}"/> </td>
                <td><f:errors path="accountHolder" cssStyle="color:red;" ></f:errors></td>
            </tr>
            <tr>
                <td>Account Date Opened</td>  
                <td> <f:input type="datetime-local" path="accountDateOpened" value="<%=java.time.LocalDateTime.now()%>"/> </td>
                <td><f:errors path="accountDateOpened" cssStyle="color:red;" ></f:errors></td>
            </tr>
            <tr>
                <td>Balance</td><td><f:input type="text" path="accountBalance" value="${account.getAccountBalance()}"/> </td>
                <td><f:errors path="accountBalance" cssStyle="color:red;" ></f:errors></td>
            </tr>
            <tr>
                <td>Account Types:</td>
                <td>
                    <c:forEach items="${accountTypes}" var="accountType">
                        <c:if test="${retrievedAccountTypes.contains(accountType)}">
                            <f:checkbox path="accountType" value="${accountType}" label="${accountType}" checked="true"/>
                        </c:if>
                        <c:if test="${!retrievedAccountTypes.contains(accountType)}">
                            <f:checkbox path="accountType" value="${accountType}" label="${accountType}" />
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
                <td>Branch:</td>
                <td>
                    <select>
                        <c:forEach items="${branches}" var="branch">
                            <option value="${branch.getBranchName()}">
                                    ${branch.getBranchName()}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                    <tr>
    <td> <b>Select a Customer:</b> </td>
    <td>
    <f:select path="accountCustomer">
        <c:forEach items="${customers}" var="c">
                <c:choose>
                    <c:when test="${selectedCustomer.equals(c)}">
                        <f:option value="${c.customerId}" label="${c.customerName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${c.customerId}" label="${c.customerName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="accountCustomer" cssClass="error"/> </td>
    </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="submit"/></td>
            </tr>
        </table>
    </f:form>
    
    
        <h3>Account List</h3>
    <table border="1">
        <tr>
            <th>Account ID</th><th>Account Name</th>
        </tr>
        <tr>
            <c:forEach items="${accounts}" var="account">
                <td>${account.getAccountId()}</td>
                <td>${account.getAccountHolder()}</td>
            
        </tr>
        </c:forEach>
    </table>
    
<br>
<br>
<p>Total Pages: ${totalPages} </p>

<c:set var="totalPages" value="${totalPages}"></c:set>
<c:set var="sortedBy" value="${sortedBy}"></c:set>
<c:set var="pageSize" value="${pageSize} "></c:set>

<%
for(int i=0; i< (int)pageContext.getAttribute("totalPages"); i++){
	out.println("<a href=\"accountForm?pageNo="+i
	+"\">"+ i +"</a>");
}
%>
</div>
</body>
</html>