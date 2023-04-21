<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Top Hot Businesses</title>
</head>
<body>
	<form action="findbusinesses" method="get">
		<h1>Get Top 5 Businesses By Review Count</h1>
		<p>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
        	<span id="errorMessage" style="color:red"><b>${messages.error}</b></span>
		</p>
	</form>
	
	<h1>Hot Businesses</h1>
        <table id="businessTable" border="1">
            <tr>
                <th>BusinessId</th>
				<th>BusinessName</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
				<th>PostalCode</th>
<!-- 				<th>Categories</th> -->
				<th>Latitude</th>
				<th>Longitude</th>
				<th>ReviewCount</th>
				<th>IsOpen</th>
<!-- 				<th>BusinessOwner</th> -->
            </tr>
            <c:forEach items="${businesses}" var="business" >
                <tr>
					<td><c:out value="${business.getBusinessId()}" default="N/A"/></td>
					<td><c:out value="${business.getBusinessName()}" default="N/A"/></td>
					<td><c:out value="${business.getAddress()}" default="N/A"/></td>
					<td><c:out value="${business.getCity()}" default="N/A"/></td>
					<td><c:out value="${business.getState()}" default="N/A"/></td>
					<td><c:out value="${business.getPostalCode()}" default="N/A"/></td>
	<%-- 				<td><c:out value="${business.getCategories()}" default="N/A"/></td> --%>
					<td><c:out value="${business.getLatitude()}" default="N/A"/></td>
					<td><c:out value="${business.getLongitude()}" default="N/A"/></td>
					<td><c:out value="${business.getReviewCount()}" default="N/A"/></td>
					<td><c:out value="${business.isOpen()}" default="N/A"/></td>
<%-- 					<td><c:out value="${business.getBusinessOwner().getUserId()}" default="N/A"/></td> --%>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
