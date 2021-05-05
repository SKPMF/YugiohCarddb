<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="styles/styles.css">
<title>Oops Error</title>
</head>
<body>
	<div class="navbar">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="ListAllServlet">List All</a></li>
			<li><a href="insert.jsp">Insert</a></li>
			<li><a href="update.jsp">Update</a></li>
			<li><a href="delete.jsp">Delete</a></li>
			<li><a href="search.jsp">Search</a></li>
		</ul>
	</div>
	
	<div class="info">
		<label> Operation failed : </label> ${operation}
		<br>
		<label> Reason : </label> ${reason}
	</div>
</body>
</html>