<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/Prefengine/UserController" name="addUser">
		<h2>Prefengine Registration </h2>
		<table>
			<tr>
				<td> <label>Enter Preferred Email id:</label></td>
				<td> <input type="text" name="email"  ></td>
			</tr>
			<tr>
				<td> <label>Re-enter Email id:</label></td>
				<td> <input type="text"  name="email" ></td>
			</tr>
			<tr>
				<td> <label>Enter Username :</label></td>
				<td> <input type="text"  name = "userName" ></td>
			</tr>
			<tr>
				<td> <label>Enter Password :</label></td>
				<td> <input type="password" name="password" ></td>
			</tr>
			<tr>
				<td> <label>Confirm Password :</label></td>
				<td> <input type="password" name="pass" ></td>
			</tr>
			<tr>
				<td> <label>Security Question :</label></td>
				<td> <input type="text" name="s_que"></td>
			</tr>
			<tr>
				<td> <label>Answer :</label></td>
				<td> <input type="text" name="s_ans"></td>
			</tr>
		</table>
		<input type="submit" name="Register" value="Submit">
	</form>
</body>
</html>