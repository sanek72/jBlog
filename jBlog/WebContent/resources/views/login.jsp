<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="core.utils.Constants"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login page</title>
</head>
<body>
	<form method="post" action="login">
		<table>
			<tr>
				<td>
					<h2>Login page</h2>
				</td>
			</tr>

			<tr>
				<td><label for="login">Логин</label></td>
				<td><input id="login" type="text" name="login"></td>
			</tr>
			<tr>
				<td><label for="password">Пароль</label></td>
				<td><input id="password" type="password" name="password"></td>
			</tr>
			<tr>
				<td>Remember me</td>
				<td><input type="checkbox" name="rememberMe" value="true" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="submit"
					value="Войти"></td>
			</tr>
			<tr>
				<td>
					<%
						if (((Boolean) request.getAttribute(Constants.NO_AUTHORIZED_USER) == null ? false : true))
							out.println("<FONT color=\"red\">Invalid login or password</FONT>");
					%>
				</td>
			</tr>
			<tr>
				<td><a href=" ${pageContext.request.contextPath} ">Main
						page</a></td>
			</tr>
		</table>
	</form>
</body>
</html>