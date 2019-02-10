<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="core.utils.Constants"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<title>Registration page</title>


</head>
<body>
	<form action="registration" method="POST">

		<table>
			<tr>
				<td>
					<h2>Registration</h2>
				</td>
			</tr>
			<tr>
				<td>Login<font color="red">*</font>:
				</td>
				<td><input type="text" size="20" name="login" value="${login}" required></td>
				<td></td>
			<tr>
				<td>Password<font color="red">*</font>:
				</td>
				<td><input type="password" size="20" maxlength="20"
					name="password" value="${password}" required></td>
			</tr>
			<tr>
				<td>Confirm password<font color="red">*</font>:
				</td>
				<td><input type="password" size="20" maxlength="20"
					name="password2" value="${password2}" required></td>
			</tr>
			<tr>
				<td>E-mail<font color="red">*</font>:</td>
				<td><input type="email" size="20" name="email" value="${email}" required></td>
			</tr>
			<tr>
				<td>Remember me</td>
				<td><input type="checkbox" name="rememberMe" value=true /></td>
			</tr>				
		</table>


		<br> <input type="submit" value="Register" name="submit">
		<br>
		<%
					if (request.getAttribute(Constants.REGISTRATION_ERRORE) != null){
						out.println("<FONT color=\"red\">" + request.getAttribute(Constants.REGISTRATION_ERRORE) + "</FONT>");
					} 				
					%>
		<br> <a href=" ${pageContext.request.contextPath} ">Main page</a>

	</form>
</body>
</html>