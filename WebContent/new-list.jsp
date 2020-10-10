<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "createNewListServlet" method="post">
List Name: <input type ="text" name = "listName"><br />
Trip date: <input type ="text" name = "month" placeholder="mm" size="4"> <input type ="text" name = "day" placeholder="dd" size="4">, <input type ="text" name = "year" placeholder="yyyy" size="4">
Tourist Name: <input type = "text" name = "touristName"><br />

Available Items:<br />

<select name="allTripsToAdd" multiple size="6">
<c:forEach items="${requestScope.allTrips}" var="currentTrip">
   <option value = "${currentTrip.id}">${currentTrip.location} | ${currentTrip.state} | ${currentTrip.attraction}</option>
</c:forEach>
</select>
<br />
<input type = "submit" value="Create List and Add trips">
</form>
<a href = "index.html">Go add new trips instead.</a>
</body>
</html>