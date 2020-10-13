<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit An Existing List</title>
</head>
<body>
<form action="editTripListDetailsServlet" method="post">
<!-- This portion creates the display of which list, creation date, and tourist name -->
<input type="hidden" name="id" value="${listToEdit.id}">
List Name: <input type="text" name="listName" value="${listToEdit.listName}"><br />

Trip date: <input type="text" name="month" placeholder="mm" size="4" value="${month}"> 
<input type="text" name="day" placeholder="dd" size="4" value="${date}">, 
<input type="text" name="year" placeholder="yyyy" size="4" value="${year}">

Tourist Name: <input type="text" name="touristName" value="${listToEdit.tourist.touristName}"><br />

Available Trips:<br />
<!-- This creates the list of all possible trips to add to the trip list -->
<select name="allTripsToAdd" multiple size="6">
<c:forEach items="${requestScope.allTrips}" var="currentitem">
<option value = "${currentitem.id}">${currentitem.location} |
${currentitem.state} | ${currentitem.attraction}</option>
</c:forEach>
</select>
<br />
<input type="submit" value="Edit List and Add Trips">
</form>
<a href="index.html">Go add new trips instead.</a>
</body>
</html>