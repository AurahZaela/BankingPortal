<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.myCss{
  color:blue;
  font-style:italic;
}
</style>
</head>
<body>
<div  align="center">
<table>
<tr>
<td><a href="accountForm?pageNo=0">Account Form | </a></td>
<td><a href="branchForm?pageNo=0">Branch Form | </a></td>
<td><a href="customerForm?pageNo=0">Customer Form | </a></td>
<td><a href="roleForm?pageNo=0">Role Form | </a></td>
<td><a href="userForm?pageNo=0">User Form | </a></td>
<td><a href="home">Home | </a></td>

</tr>
</table>
</div>

<br>

<div align="center">
<h1>Bank Transaction Management Form</h1>

<f:form action="saveBankTransaction" method="POST" modelAttribute="bankTransaction">  
<table border="1">
    
    <tr>
    <td> ID: </td>
    <td> <f:input path="bankTransactionId" value="${retrievedBankTransaction.bankTransactionId}"/> </td>
    <td> <f:errors path="bankTransactionId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> Select an Account From: </td>
    <td>
    <f:select path="bankTransactionFromAccount">
        <c:forEach items="${accounts}" var="a">
            <c:choose>
                <c:when test="${retrievedBankTransaction.bankTransactionFromAccount.equals(a.accountId)}">  <!-- bankTransactionFromAccount is an integer, so checking equality with id -->
                    <f:option value="${a.accountId}" label="${a.accountId}" selected="true"></f:option>
                </c:when>
                <c:otherwise>
                    <f:option value="${a.accountId}" label="${a.accountId}"></f:option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="bankTransactionFromAccount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> Select an Account To: </td>
    <td>
    <f:select path="bankTransactionToAccount">
        <c:forEach items="${accounts}" var="a">
            <c:choose>
                <c:when test="${retrievedBankTransaction.bankTransactionToAccount.equals(a.accountId)}">  <!-- bankTransactionToAccount is an integer, so checking equality with id -->
                    <f:option value="${a.accountId}" label="${a.accountId}" selected="true"></f:option>
                </c:when>
                <c:otherwise>
                    <f:option value="${a.accountId}" label="${a.accountId}"></f:option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="bankTransactionToAccount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> Transaction Amount: </td>
    <td> <f:input path="bankTransactionAmount" value="${retrievedBankTransaction.bankTransactionToAccount}"/> </td>
    <td> <f:errors path="bankTransactionAmount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> Transaction Type:</td>
    <td>
        <c:forEach items="${banktransactionType}" var="t">
            <c:choose>
                <c:when test="${retrievedBankTransaction.banktransactionType.equals(t)}">
                    <f:radiobutton path="banktransactionType" value="${t}" label="${t}" checked="true" class="form-check-input"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="banktransactionType" value="${t}" label="${t}" class="form-check-input"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="banktransactionType" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> Transaction Date: </td>  <!-- A Calendar -->
    <td> <f:input type="datetime-local" path="bankTransactionDateTime" value="<%=java.time.LocalDateTime.now()%>"/> </td>
    <td> <f:errors path="bankTransactionDateTime" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> Comments: </td>
    <td> <f:input path="comments" value="${retrievedBankTransaction.comments}"/> </td>
    <td> <f:errors path="comments" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Account From</td> <td>Account To</td> <td>Amount</td> <td>Type</td>
            <td>Date</td> <td>Comments</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${bankTransactions}" var="t">
            <tr>
            <td>${t.bankTransactionId}</td> <td>${t.bankTransactionFromAccount}</td> <td>${t.bankTransactionToAccount}</td> <td>${t.bankTransactionAmount}</td> <td>${t.banktransactionType}</td>
            <td>${t.bankTransactionDateTime}</td> <td>${t.comments}</td>
            <td> <a href="updateBankTransaction?bankTransactionId=${t.bankTransactionId}"> Update </a> | <a href="deleteBankTransaction?bankTransactionId=${t.bankTransactionId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>

<p/>
<p/>
<p>Total Pages: ${totalPages} </p>

<c:set var="totalPages" value="${totalPages}"></c:set>
<c:set var="sortedBy" value="${sortedBy}"></c:set>
<c:set var="pageSize" value="${pageSize} "></c:set>

<%
for(int i=0; i< (int)pageContext.getAttribute("totalPages"); i++){
	out.println("<a href=\"bankTransactionForm?pageNo="+i
	+"\">"+ i +"</a>");
}
%>

</div>

</div>
</body>
</html>