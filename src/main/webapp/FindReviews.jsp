<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Reviews</title>
</head>
<body>
	<form action="findreviews" method="post">
		<h1>Search for reviews by businessName</h1>
		<p>
			<label for="businessName">BusinessName</label>
			<input id="businessName" name="businessName" value="${fn:escapeXml(param.businessName)}">
		</p>
		<p>
			<input type="submit">	
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<form action="findtopreviews" method="post">
  		<p>
			<button type="submit">Get Most Useful Reviews</button>
			<input id="businessName" name="businessName" value="${fn:escapeXml(param.businessName)}">
		</p>
		<p>
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<br/>
	<div id="reviewCreate"><a href="reviewcreate">Create Review</a></div>
	<br/>
	<h1>Matching Reviews</h1>
        <table id="reviewTable" border="1">
            <tr>
                <th>ReviewId</th>
				<th>Stars</th>
				<th>UsefulCount</th>
				<th>FunnyCount</th>
				<th>CoolCount</th>
				<th>ReviewContent</th>
				<th>ReviewDate</th>
				<th>Business</th>
				<th>User</th>
            </tr>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                    <td><c:out value="${review.getReviewId()}" /></td>
					<td><c:out value="${review.getStars()}" /></td>
					<td><c:out value="${review.getUsefulCount()}" /></td>
					<td><c:out value="${review.getFunnyCount()}" /></td>
					<td><c:out value="${review.getCoolCount()}" /></td>
					<td><c:out value="${review.getReviewContent()}" /></td>
					<td><c:out value="${review.getReviewDate()}" /></td>
					<td><c:out value="${review.getBusiness().getBusinessName()}" /></td>
					<td><c:out value="${review.getUser().getPersonName()}" /></td>
					<td><a href="reviewupdate?reviewid=<c:out value="${review.getReviewId()}"/>">Update</a></td>
					<td><a href="reviewdelete?reviewid=<c:out value="${review.getReviewId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
