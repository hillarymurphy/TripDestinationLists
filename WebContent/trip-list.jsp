<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Trips</title>
</head>
<body>
<form method="post" action="navigationServlet">
<table>
<c:forEach items="${requestScope.allTrips}" var="currenttrip">
<tr>
	<td><input type="radio" name="id" value="${currenttrip.id}"></td>
	<td>${currenttrip.location}</td>
	<td>${currenttrip.state}</td>
	<td>${currenttrip.attraction}</td>
</tr>
</c:forEach>
</table>
<input type="submit" value="edit" name="doThisToTrip">
<input type="submit" value="delete" name="doThisToTrip">
<input type="submit" value="add" name="doThisToTrip">
</form>
</body>
</html>