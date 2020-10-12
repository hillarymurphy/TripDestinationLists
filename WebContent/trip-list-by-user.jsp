<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trip Lists</title>
</head>
<body>
<form method = "post" action="tripListNavigationServlet">
<table>
<c:forEach items="${requestScope.allTrips}" var="currenttrip">
<tr>
	<td><input type="radio" name="id" value="${currenttrip.id}"></td>
	<td><h2>${currenttrip.listName}</h2></td></tr>
<tr><td colspan="3">Trip Date:${currenttrip.tripDate}</td></tr>
<tr><td colspan="3">Tourist:${currenttrip.tourist.touristName}</td></tr>
<c:forEach var = "listVal" items = "${currenttrip.listOfTrips}">
            <tr><td colspan="3"> Trip:
                ${listVal.location}, ${listVal.state}, ${listVal.attraction}
                </td>
            </tr>
  </c:forEach>
</c:forEach>
</table>
<input type="submit" value="edit" name="doThisToList">
<input type="submit" value="delete" name="doThisToList">
<input type="submit" value="add" name="doThisToList">
</form>
<a href="addTripsForListServlet">Create a new List</a>
<a href="index.html">Insert a new trip</a>
</body>
</html>