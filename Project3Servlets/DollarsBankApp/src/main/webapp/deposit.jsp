<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
		 int custId = (int) request.getAttribute("custId");
	%>

	<h3>How Much Would You Like to Deposit: </h3>
	
	<form action="deposit" method="post">
		<input type="number" step="0.01" min="0.01" name="newdeposit" required><br>
		<input type="hidden" value="<%=custId %>" name="custId">
		<input type="submit" name="submit" value="Deposit">
	</form>

</body>
</html>