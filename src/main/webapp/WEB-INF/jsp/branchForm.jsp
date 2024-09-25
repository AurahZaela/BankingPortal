<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Branch Form</title>
</head>
<body>

<div  align="center">
<table>
<tr>
<td><a href="accountForm?pageNo=0">Account Form | </a></td>
<td><a href="bankTransactionForm?pageNo=0">Bank Transaction Form | </a></td>
<td><a href="customerForm?pageNo=0">Customer Form | </a></td>
<td><a href="roleForm?pageNo=0">Role Form | </a></td>
<td><a href="userForm?pageNo=0">User Form | </a></td>
<td><a href="home">Home | </a></td>

</tr>
</table>
</div>

<div align ="center">

	<h1>Branch Form</h1>
 <f:form action="saveBranch" method="POST" modelAttribute="branch">
        <table border="1">
            <tr>
                <td>Branch ID</td><td><f:input path="branchId" value="${branch.getBranchId()}" readonly="true"/> </td>
                <td><f:errors path="branchId"  cssStyle="color:red;"></f:errors></td>
            </tr>
            <tr>
                <td>Branch Name</td><td><f:input path="branchName" value="${branch.getBranchName()}" readonly="false"/> </td>
                <td><f:errors path="branchName"  cssStyle="color:red;"></f:errors></td>
            </tr>
            <tr>
                <td colspan="3" align="center"><strong>Address</strong></td>
            </tr>
            <tr>
                <td>Address Line1</td>
                <td>
                    <f:input type="text" path="branchAddress.addressLine1" value="${branch.getBranchAddress().getAddressLine1()}"/>
                </td>
                <td><f:errors path="branchAddress.addressLine1"  cssStyle="color:red;"></f:errors></td>
            </tr>


            <tr>
                <td>Address Line2</td>
                <td>
                    <f:input type="text" path="branchAddress.addressLine2" value="${branch.getBranchAddress().getAddressLine2()}"/>
                </td>
                <td><f:errors path="branchAddress.addressLine2"  cssStyle="color:red;"></f:errors></td>
            </tr>


            <tr>
                <td>City</td>
                <td>
                    <f:input type="text" path="branchAddress.city" value="${branch.getBranchAddress().getCity()}"/>
                </td>
                <td><f:errors path="branchAddress.city"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>State</td>
                <td>
                    <f:input type="text" path="branchAddress.state" value="${branch.getBranchAddress().getState()}"/>
                </td>
                <td><f:errors path="branchAddress.state"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>Country</td>
                <td>
                    <f:input type="text" path="branchAddress.country" value="${branch.getBranchAddress().getCountry()}"/>
                </td>
                <td><f:errors path="branchAddress.country"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td>ZIP</td>
                <td>
                    <f:input type="text" path="branchAddress.zip" value="${branch.getBranchAddress().getZip()}"/>
                </td>
                <td><f:errors path="branchAddress.zip"  cssStyle="color:red;"></f:errors></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="submit"/></td>
            </tr>
        </table>
    </f:form>
    <h3>Branch List</h3>
    <table border="1">
        <tr>
            <th>Branch ID</th><th>Branch Name</th><th>Actions</th>
        </tr>
        <tr>
            <c:forEach items="${branches}" var="branch">
                <td>${branch.getBranchId()}</td>
                <td>${branch.getBranchName()}</td>
                <td>
                    <a href="/branch/deleteBranch?branchId=${branch.getBranchId()}">Delete</a>
                    <a href="/branch/updateBranch?branchId=${branch.getBranchId()}">Update</a>
                </td>
            
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
	out.println("<a href=\"branchForm?pageNo="+i
	+"\">"+ i +"</a>");
}
%>
    
</div>
</body>
</html>