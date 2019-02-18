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
	href="${pageContext.servletContext.contextPath}/resources/css/registrationstyle.css" />
<title>Cтраница регистрации</title>
</head>
<body
	background="${pageContext.servletContext.contextPath}/resources/images/main3.jpg">
	
	<div id="header">
		<h1>Cтраница регистрации</h1>
	</div>
	<div class="main">
	<form method="post" action="registration">
		<div class="field">
			Логин<font color="red">*</font>: <input type="text" size="20"
				name="login" value="${login}" required>
		</div>
		<div class="field">
			Пароль<font color="red">*</font>: <input type="password" size="20"
				maxlength="20" name="password" value="${password}" required>
		</div>
		<div class="field">
			Повторите пароль<font color="red">*</font>: <input type="password"
				size="20" maxlength="20" name="password2" value="${password2}"
				required>
		</div>
		<div class="field">
			E-mail<font color="red">*</font>: <input type="email" size="20"
				name="email" value="${email}" required>
		</div>
		<div class="field">
			Запомнить меня? <input type="checkbox" name="rememberMe" value=true />
		</div>
		<div class="field">
			<input type="submit" value="Зарегистрироваться" name="submit">
		</div>
		</form>
	</div>
	<div id="info">
		<%
			if (request.getAttribute(Constants.REGISTRATION_ERRORE) != null) {
				out.println("<FONT color=\"red\">" + request.getAttribute(Constants.REGISTRATION_ERRORE) + "</FONT>");
			}
		%>
		<p>
			<a href=" ${pageContext.request.contextPath} ">Домашняя
						страница</a>
		</p>

	</div>
	<div id="footer">
		<p>
			Простой блог на java: <a href="https://github.com/sanek72/jBlog">Jblog</a>
		</p>
	</div>
</body>
</html>