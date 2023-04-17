<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Review</title>
</head>
<body>
	<h1>Create Review</h1>
	<form action="reviewcreate" method="post">
		<p>
			<label for="reviewid">ReviewId</label>
			<input id="reviewid" name="reviewid" value="">
		</p>
		<p>
			<label for="stars">Stars</label>
			<input id="stars" name="stars" value="">
		</p>
		<p>
			<label for="usefulcount">UsefulCount</label>
			<input id="usefulcount" name="usefulcount" value="">
		</p>
		<p>
			<label for="funnycount">FunnyCount</label>
			<input id="funnycount" name="funnycount" value="">
		</p>
		<p>
			<label for="coolcount">CoolCount</label>
			<input id="coolcount" name="coolcount" value="">
		</p>
		<p>
			<label for="reviewcontent">ReviewContent</label>
			<input id="reviewcontent" name="reviewcontent" value="">
		</p>	
		<p>
			<label for="reviewdate">ReviewDate (yyyy-mm-dd)</label>
			<input id="reviewdate" name="reviewdate" value="">
		</p>
		<p>
			<label for="businessid">BusinessId</label>
			<input id="businessid" name="businessid" value="">
		</p>
		<p>
			<label for="userid">UserId</label>
			<input id="userid" name="userid" value="">
		</p>			
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>