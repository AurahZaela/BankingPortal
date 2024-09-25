<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Form</title>
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
<td><a href="bankTransactionForm?pageNo=0">Bank Transaction Form | </a></td>
<td><a href="roleForm?pageNo=0">Role Form | </a></td>
<td><a href="userForm?pageNo=0">User Form | </a></td>
<td><a href="home">Home | </a></td>

</tr>
</table>
</div>

<div align ="center">
	<h1>Customer Form</h1>
<f:form action="saveCustomer" method="POST" modelAttribute="customer">
<table border="1">

<tr>
<td>Customer Id: </td>
<td>
<f:input type="text" path="customerId" value="${c.getCustomerId()}"/>
</td>
</tr>

<tr>
<td>Customer Name: </td>
<td>
<f:input type="text" path="customerName" value="${c.getCustomerName()}"/>
</td>
<td><f:errors path="customerName"  cssStyle="color:red;"></f:errors></td>
</tr>


<tr>
<td>Customer Gender: </td>
<%-- <td>
<f:radiobuttons path="customerGender" value="${genders}"/>
</td> --%>
<td>
        <c:forEach items="${genders}" var="g">
            <c:choose>
                <c:when test="${customer.customerGender.equals(g)}">
                    <f:radiobutton path="customerGender" value="${g}" label="${g}" checked="true" class="form-check-input"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="customerGender" value="${g}" label="${g}" class="form-check-input"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>

<td><f:errors path="customerGender"  cssStyle="color:red;"></f:errors></td>
</tr>

<tr>
<td>Customer DOB: </td>
<td>
<f:input type="date" path="customerDOB" value="${c.getCustomerDOB()}"/>
</td>
<td><f:errors path="customerDOB"  cssStyle="color:red;"></f:errors></td>
</tr>

<tr>
<td>Customer Mobile: </td>
<td>
<f:input type="text" path="customerMobileNo" value="${c.getCustomerMobileNo}"/>
</td>

<tr>
                <td colspan="3" align="center"><strong>Address</strong></td>
            </tr>

            <tr>
                <td>Address Line1</td>
                <td>
                    <f:input type="text" path="customerAddress.addressLine1" value="${customer.getCustomerAddress().getAddressLine1()}"/>
                </td>
                	<td><f:errors path="customerAddress.addressLine1"  cssStyle="color:red;"></f:errors></td>
            </tr>


            <tr>
                <td>Address Line2</td>
                <td>
                    <f:input type="text" path="customerAddress.addressLine2" value="${customer.getCustomerAddress().getAddressLine2()}"/>
                </td>           
            </tr>

 <tr>
                <td>City</td>
                <td>
                    <f:input type="text" path="customerAddress.city" value="${customer.getCustomerAddress().getCity()}"/>
                </td>
                <td><f:errors path="customerAddress.city"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>State</td>
                <td>
                    <f:input type="text" path="customerAddress.state" value="${customer.getCustomerAddress().getState()}"/>
                </td>
                <td><f:errors path="customerAddress.state"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>Country</td>
                <td>
                    <f:input type="text" path="customerAddress.country" value="${customer.getCustomerAddress().getCountry()}"/>
                </td>
                <td><f:errors path="customerAddress.country"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>ZIP</td>
                <td>
                    <f:input type="text" path="customerAddress.zip" value="${customer.getCustomerAddress().getZip()}"/>
                </td>
                <td><f:errors path="customerAddress.zip"  cssStyle="color:red;"></f:errors></td>
            </tr>


<tr>
<td>Customer SSN: </td>
<td>
<f:input type="text" path="customerSSN" value="${c.getCustomerSSN()}"/>
</td>
<td><f:errors path="customerSSN"  cssStyle="color:red;"></f:errors></td>
</tr>

<tr>
<td>Customer Accounts: </td>
<td>
<f:input path="customerAccounts" value="${c.getCustomerAccounts()}"/>
</td>
<td><f:errors path="customerAccounts"  cssStyle="color:red;"></f:errors></td>
</tr>


<tr>
<td colspan="2" align="center"><input type="submit" value="submit"/></td>
</tr>
</tr>
</table>
</f:form>

<h3>Customers List</h3>

    <table border="1">
        <tr>
            <th>Customer ID</th> <th>Name</th> <th>Mobile</th> <th>Action</th>
        </tr>

        <tr>
            <c:forEach items="${customers}" var="customer">
            <td>${customer.getCustomerId()}</td>
            <td>${customer.getCustomerName()}</td>
            <td>${customer.getCustomerMobileNo()}</td>
            <td>
                <a href="deleteCustomer?customerId=${customer.getCustomerId()}">Delete</a>
                <a href="updateCustomer?customerId=${customer.getCustomerId()}">Update</a>
            </td>
        </tr>
        </c:forEach>

    </table>


</div>
</body>
</html>