<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="java.util.ArrayList" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer</title>
</head>
<body>

	<%
	int custId = (int) request.getAttribute("custId");
	List<String> errList = (ArrayList<String>) request.getAttribute("errList");
	if (errList != null) {
		for (String errors : errList) {
			%>
			<script>
				alert("<%=errors%>");
			</script>
			<%
		}
	}
	%>
	
	<h3>Please Enter an Account Id to Transfer to: </h3>
	
	<form action="transfer" method="post">
		<input type="number" name="id" min="1" required><br>
		<p>How Much Would You Like to Transfer: </p>
		<input type="number" name="trans" step="0.01" min="0.01" required><br>
		<input type="hidden" value="<%=custId %>" name="custId">
		<input type="submit" name="submit" value="Transfer">
	</form>

</body>
</html>