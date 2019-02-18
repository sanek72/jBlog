<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Вы успешно зарегистрированны!</title>
</head>
<body
	background="${pageContext.servletContext.contextPath}/resources/images/main3.jpg">
	<h3>${login} спасибо за регистрацию. Вы будете перенаправлены на домашнюю страницу.</h3>
	<meta http-equiv="refresh" content="5;${pageContext.request.contextPath}">
	<br>
	<h3><a href=" ${pageContext.request.contextPath} ">Домашняя страница</a></h3>
</body>
</html>