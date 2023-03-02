<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>
</head>
<body>
	<h3>Your Transactions: </h3>
	
	<%
		int custId = (int) request.getAttribute("custId");
		List<String> transList = (ArrayList<String>) request.getAttribute("transList");
		for (String trans : transList) {
			%>
			<p><%=trans %></p>
			<%
		}
	%>

</body>
</html>