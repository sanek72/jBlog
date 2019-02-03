<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="core.utils.Constants"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Java site</title>
</head>

<body>
	<table border="1" background="" bgcolor="" cellpadding="10"
		style="width: 100%; border-radius: 5px;">
		<tr>
			<th>
				<h1>My blog</h1>
				<h3></h3>
			</th>
		</tr>
	</table>
	<table border="1" bgcolor="" cellpadding="10"
		style="width: 100%; border-radius: 5px;">
		<tr>
			<td rowspan="2" style="width: 80%">
				<h2>Главная</h2>
				<p style="text-indent: 20px">Здравствуйте</p>

				<p style="text-indent: 20px">test</p>

			</td>

			<!--САЙДБАР-->
			<td bgcolor="">
				<h3>Меню</h3>

				<p>

					<%
						if ((Boolean) request.getAttribute(Constants.IS_AUTHORIZED_USER)) {
							out.println("Welcome " + request.getAttribute(Constants.AUTHORIZED_USER) + "<br>");
							out.println("<a href=\"exit\"> <span style=\"margin-left:5px;\">Exit</span;></a>");
						} else {
							out.println("<a href=\"login\"> <span style=\"margin-left:5px;\">Login</span;></a><br>");
							out.println("<a href=\"registration\"> <span style=\"margin-left:5px;\">New user</span;></a>");
						}
					%>


				</p>

			</td>
		</tr>

		<tr>
			<td bgcolor="">
				<h3>Навигация</h3>
				<p>null</p>
			</td>
		</tr>
	</table>

	<table border="1" bgcolor="" height="100" cellpadding="10"
		style="width: 100%; border-radius: 5px;">
		<!--Создаём строку.-->
		<tr>
			<!--Создаём столбец-->
			<th>
				<h3>Подвал</h3>

			</th>
		</tr>
	</table>


</body>

</html>


