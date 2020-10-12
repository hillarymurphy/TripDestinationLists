<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Trip</title>
</head>
<body>
<form action = "editTripServlet" method = "post">
Location: <input type = "text" name = "location" 
value ="${tripToEdit.location}">
State: <input type = "text" name = "state" 
value ="${tripToEdit.state}">
Attraction: <input type = "text" name = "attraction" 
value ="${tripToEdit.attraction}">
<input type = "hidden" name = "id" value = "${tripToEdit.id}">
<input type = "submit" value = "Save Edited Trip">
</form>
</body>
</html>