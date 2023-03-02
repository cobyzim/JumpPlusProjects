<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdrawal</title>
</head>
<body>
	
	<% 
		int custId = (int) request.getAttribute("custId");
		String error = (String) request.getAttribute("errorMsg");
		if (error != null) {
			%>
			<script>
			alert("<%=error%>");
			</script>
			<% 
		}
	%>
	

	<h3>How Much Would You Like to Withdrawal: </h3>
	
	<form action="withdrawal" method="post">
		<input type="number" step="0.01" min="0.01" name="withdrawal" required><br>
		<input type="hidden" value="<%=custId %>" name="custId">
		<input type="submit" name="submit" value="Withdrawal">
	</form>

</body>
</html>