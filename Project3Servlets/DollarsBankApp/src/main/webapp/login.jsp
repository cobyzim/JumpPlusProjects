<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="java.util.ArrayList" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%
		List<String> errList = (ArrayList<String>) request.getAttribute("errList");
		if (errList != null) {
			for (String error : errList) {
				%>
				<script>
					alert("<%=error%>");
				</script>
				<%
			}
		}
	%>

	<div>
		<h1>Welcome to DollarsBank!</h1>
	</div>
	
	<form action="login" method="post">
		<table>
			<tr>
				<th scope="row">User Id: </th>
				<td><input type="number" name="id"></td>
			</tr>
			<tr>
				<th scope="row">Password: </th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Login"></td>
				<td><a href="createAccount.jsp">Create Account</a></td>
			</tr>
		</table>
	</form>




</body>
</html>