<!doctype html>
<%@ page import="core.utils.Constants"%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta name="description" content="java web blog">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/homestyle.css" />
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Oswald:400,300"
	type="text/css">

<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<title>Домашння страница</title>
</head>

<body>
	<!--<body background="${pageContext.servletContext.contextPath}/resources/images/main3.jpg">
	-->

	<div id="wrapper">

		<div id="header">
		
		<div id="logo">
		!MyBlog!
		</div>

			<form name="search" action="#" method="get">
				<input type="text" name="q" placeholder="Search">
				<button type="submit">Go</button>
			</form>

		</div>
		
		<div id="navigation">
	<ul>
		<li><a href="${pageContext.servletContext.contextPath}/">HOME</a></li>
		<li><a href="/services/">test</a></li>
	</ul>		
		</div>
		
			<div id="sidebar">
			<h3>Записи</h3>
	<ul>
		<li><a href="#">test1</a></li>
		<li><a href="#">test2</a></li>
	</ul>			
			</div>
			<div id="content">
  
  <ul>
				<%@ page import="core.bean.*"%>

				<%
					Catalogue cataloque = (Catalogue) request.getAttribute("catalogue");

					for (String a : cataloque.getTypes()) {
						out.println("<li>");
						out.println(a);
						out.println("<ul>");
						for (String b : cataloque.getSubTypes(a)) {
							out.println("<li>" + b + "</li>");
							out.println("<ul>");
							for (String c : cataloque.getName(a, b)) {
								out.println("<p><a href=\"" + request.getContextPath() + "?entry=" + c + "\">" + c + "</a></p>");
							}
							out.println("</ul>");
						}
						out.println("</ul>");
						out.println("</li>");
					}
				%>
			</ul>

			</div>		

	</div>
	
			<div id="footer">
			<p>
				Простой блог на java: <a href="https://github.com/sanek72/jBlog">Jblog</a>
			</p>

		</div>

</body>

</html>


