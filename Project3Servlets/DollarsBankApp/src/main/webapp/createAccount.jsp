<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>
	<div>
		<h1>Please Enter Your Information!</h1>
	</div>
	
	<form action="createAccount" method="post">
		<table>
			<tr>
				<th scope="row">Name: </th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th scope="row">Address: </th>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<th scope="row">Phone Number: </th>
				<td><input type="number" name="phone"></td>
			</tr>
			<tr>
				<th scope="row">User Id: </th>
				<td><input type="number" name="id"></td>
			</tr>
			<tr>
				<th scope="row">Password: </th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<th scope="row">Initial Deposit: </th>
				<td><input type="number" step="0.01" name="initialdeposit"></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Create Account"></td>
				<td><a href="login.jsp">Go Back To Login</a></td>
			</tr>
		</table>
	</form>

</body>
</html>