<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="core.utils.Constants"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta name="description" content="java web blog">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Cтраница авторизации</title>
</head>
<body>
	<form method="post" action="login">

		<h2>Cтраница авторизации.</h2>

		<table>
			<tr>
				<td><label for="login">Логин:</label></td>
				<td><input id="login" type="text" name="login"></td>
			</tr>
			<tr>
				<td><label for="password">Пароль:</label></td>
				<td><input id="password" type="password" name="password"></td>
			</tr>
			<tr>
				<td>Запомнить меня</td>
				<td><input type="checkbox" name="rememberMe" value="true" /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="submit"
					value="Войти"></td>
			</tr>
		</table>
		
		<%
			if (((Boolean) request.getAttribute(Constants.NO_AUTHORIZED_USER) == null ? false : true))
				out.println("<FONT color=\"red\">Ошибка авторизации: \"Неверный логин или пароль.\"</FONT>");
		%>
		<br>
		<a href=" ${pageContext.request.contextPath} ">Main page</a>


	</form>
</body>
</html>