<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page import="core.utils.Constants"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta name="description" content="java web blog">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/loginstyle.css" />
<title>Cтраница авторизации</title>
</head>
<body
	background="${pageContext.servletContext.contextPath}/resources/images/main3.jpg">

	<div id="page">

		<div id="header">
			<h1>Cтраница авторизации</h1>
		</div>

		<div id="login_block">

			<form method="post" action="login">

				<h2>
					<label for="login">Логин:</label>
				</h2>
				<input id="login" type="text" name="login" value="" maxlength="20" required>
				<h2>
					<label for="password">Пароль:</label>
				</h2>
				<input id="password" type="password" name="password" value="" maxlength="20"
					required>
				
				
					<h2><small><br>Запомнить меня: <input type="checkbox" name="rememberMe" value=true/></small></h2>
				

				<p style="text-align: right">
					<input type="submit" value="Войти"/>
				</p>
			</form>
			<a href="#">Забыл пароль?</a>
		</div>

		<div id="info">
			<%
				if (((Boolean) request.getAttribute(Constants.NO_AUTHORIZED_USER) == null ? false : true))
					out.println(
							"<p><FONT size=\"4\" color=\"red\">Ошибка авторизации: \"Неверный логин или пароль.\"</FONT></p>");
			%>
			<p>
				<br>
				<strong><a href="registration">Зарегистрироваться</a></strong>
			</p>
			<p>
				<br>
				<strong><a href=" ${pageContext.request.contextPath}">Домашняя
						страница</a></strong>
			</p>
		</div>

	</div>

	<div id="footer">
	<br>
		<p>
			Простой блог на java: <a href="https://github.com/sanek72/jBlog">Jblog</a>
		</p>
		<br>
	</div>


</body>
</html>