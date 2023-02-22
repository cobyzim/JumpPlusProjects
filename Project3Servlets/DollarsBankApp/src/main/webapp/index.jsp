<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home JSP</title>
</head>
<body>
	<% 
	String custName = request.getParameter("custName");
	out.print("Welcome " + custName);
	
	String custAddress = request.getParameter("custAddress");
	out.print("Address: " + custAddress);
	
	String custPhone = request.getParameter("custPhone");
	out.print("Phone: " + custPhone);
	%>
	<p><%=custPhone%></p>




</body>
</html>