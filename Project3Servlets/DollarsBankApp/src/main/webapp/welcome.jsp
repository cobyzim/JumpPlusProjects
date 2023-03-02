<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>

	<% 
		 int custId = (int) request.getAttribute("custId");
	%>

	<h1>Welcome!!!</h1>
	<h3>Please Choose an Option Below:</h3>

	<form action="customerChoices" method="post">
		<input type="radio" value="deposit" name="radios">Make Deposit<br>
		<input type="radio" value="withdrawal" name="radios">Make Withdrawal<br> 
		<input type="radio" value="transfer" name="radios">Transfer Funds<br> 
		<input type="radio" value="transactions" name="radios">View Recent Transactions<br> 
		<input type="radio" value="info" name="radios">Display Customer Info<br>
		<input type="radio" value="exit" name="radios">Exit<br> 
		
		<input type="hidden" value="<%=custId %>" name="custId">
		
		<input type="submit" name="submit" value="Confirm">
	</form>



</body>
</html>