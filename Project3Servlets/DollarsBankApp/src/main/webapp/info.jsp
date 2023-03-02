<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Info</title>
</head>
<body>

	<h3>Your Customer Info</h3>
	
	<%
		int custId = (int) request.getAttribute("custId");
		String custName = (String) request.getAttribute("custName");
		String custAddress = (String) request.getAttribute("custAddress");
		int phoneNumber = (int) request.getAttribute("phoneNumber");
		double balance = (double) request.getAttribute("balance");
	%>
	
	<h4>Name:</h4>
	<p><%=custName %></p>
	
	<h4>Address:</h4>
	<p><%=custAddress %></p>
	
	<h4>Phone Number:</h4>
	<p><%=phoneNumber %></p>
	
	<h4>Balance:</h4>
	<p><%=balance %></p>

</body>
</html>