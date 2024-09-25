<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Form</title>
</head>
<body>
<div align="center">
<h1>Withdraw Form</h1>

<f:form action="saveWithdrawal" method="post" modelAttribute="bankTransaction">  <!-- modelAttribute is name of class starting with lower case -->
<table>
    
    <tr>
    <td> <b>ID (auto-generated if not specify):</b> </td>
    <td> <f:input path="bankTransactionId" value="${retrievedBankTransaction.bankTransactionId}"/> </td>
    <td> <f:errors path="bankTransactionId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Select an Account to Withdraw:</b> </td>
    <td>
    <f:select path="bankTransactionFromAccount">
        <c:forEach items="${accounts}" var="a">
            <c:choose>
                <c:when test="${retrievedBankTransaction.bankTransactionFromAccount.equals(a.accountId)}">  <!-- bankTransactionFromAccount is an integer, so checking equality with id -->
                    <f:option value="${a.accountId}" label="${a.accountId} ${a.accountHolder} ${a.accountType}" selected="true"></f:option>
                </c:when>
                <c:otherwise>
                    <f:option value="${a.accountId}" label="${a.accountId} ${a.accountHolder} ${a.accountType}"></f:option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="bankTransactionFromAccount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Transaction Amount:</b> </td>
    <td> <f:input path="bankTransactionAmount" value="${retrievedBankTransaction.bankTransactionAmount}"/> </td>
    <td> <f:errors path="bankTransactionAmount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Transaction Type:</b> </td>
    <td> <f:input path="banktransactionType" value="${banktransactionType}" readonly="true"/> </td>
    <td> <f:errors path="banktransactionType" cssClass="error"/> </td>
    </tr>
    
    
    <tr>
    <td> <b>Transaction Date:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="datetime-local" path="bankTransactionDateTime" value="<%=java.time.LocalDateTime.now()%>"/> </td>
    <td> <f:errors path="bankTransactionDateTime" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Comments:</b> </td>
    <td> <f:input path="comments" value="${retrievedBankTransaction.comments}"/> </td>
    <td> <f:errors path="comments" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<h3>All Transactions</h3>
<c:if test="${not empty bankTransactions}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Account From</td> <td>Account To</td> <td>Amount</td> <td>Type</td>
            <td>Date</td> <td>Comments</td> 
        </tr></thead>

        <c:forEach items="${bankTransactions}" var="t">
            <tr>
            <td>${t.bankTransactionId}</td> <td>${t.bankTransactionFromAccount}</td> <td>${t.bankTransactionToAccount}</td> <td>${t.bankTransactionAmount}</td> <td>${t.banktransactionType}</td>
            <td>${t.bankTransactionDateTime}</td> <td>${t.comments}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>
</div>

</div>
</body>
</html>