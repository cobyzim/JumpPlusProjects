<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="java.util.ArrayList" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
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
		<h1>Please Enter Your Information!</h1>
	</div>
	
	<form action="createAccount" method="post">
		<table>
			<tr>
				<th scope="row">Name: </th>
				<td><input type="text" name="name" pattern="^[A-Z]\w+\s[A-Z]\w+"
				title="Please Match Requested Format i.e. Big Bubba" required></td>
			</tr>
			<tr>
				<th scope="row">Address: </th>
				<td><input type="text" name="address" pattern="\d+\s+([a-zA-Z]+|[a-zA-Z]+\s[a-zA-Z]+)"
				title="Please Matche Requested Format i.e. 123 Street" required></td>
			</tr>
			<tr>
				<th scope="row">Phone Number: </th>
				<td><input type="text" name="phone" pattern="[0-9]{10}"
				title="Please Enter a 10 Digit Phone Number" required></td>
			</tr>
			<tr>
				<th scope="row">User Id: </th>
				<td><input type="number" name="id" min="1" required></td>
			</tr>
			<tr>
				<th scope="row">Password: </th>
				<td><input type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8}$"
				title="Password Must Have Upper, Lower, Number, and Special Character" required></td>
			</tr>
			<tr>
				<th scope="row">Initial Deposit: </th>
				<td><input type="number" step="0.01" min="0.01" name="initialdeposit" required></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Create Account"></td>
				<td><a href="login.jsp">Go Back To Login</a></td>
			</tr>
		</table>
	</form>

</body>
</html>