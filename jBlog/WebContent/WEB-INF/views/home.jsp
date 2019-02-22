<!doctype html>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="core.model.*"%>
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

			<div id="logo">!MyBlog!</div>

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
				<c:forEach items="${listPost}" var="blogPosts">
					<li>${blogPosts.key}
						<ul>
							<c:forEach items="${blogPosts.value.posts}" var="subcategory">
								<li>${subcategory.key}</li>
								<ul>
									<c:forEach items="${subcategory.value}" var="post">
										<p><a href="${pageContext.servletContext.contextPath}/?entry=${post.id}">${post.name}</a></p>
									</c:forEach>
								</ul>
							</c:forEach>
						</ul>
					</li>

				</c:forEach>
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


