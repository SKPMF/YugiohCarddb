<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="styles/styles.css">
<title>Index Page</title>
</head>
<body>
	<div class="navbar">
		<ul>
			<li class="active"><a href="index.jsp">Home</a></li>
			<li><a href="ListAllServlet">List All</a></li>
			<li><a href="insert.jsp">Insert</a></li>
			<li><a href="update.jsp">Update</a></li>
			<li><a href="delete.jsp">Delete</a></li>
			<li><a href="search.jsp">Search</a></li>
		</ul>
	</div>
	
	<div class="info">
		Welcome to the card database.
		<br>
		Using Apache Tomcat 9.0, MYSQL 8.0.4, java jdk 1.16.0
		<br>
		Coded with Eclipse IDE
		<br>
		Technologies : Java, Servlets, JSP, JSTL, HTML and CSS
	</div>
</body>
</html>