<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="styles/styles.css">
<title>Update : Enter ID</title>
</head>
<body>
	<div class="navbar">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="ListAllServlet">List All</a></li>
			<li><a href="insert.jsp">Insert</a></li>
			<li class="active"><a href="update.jsp">Update</a></li>
			<li><a href="delete.jsp">Delete</a></li>
			<li><a href="search.jsp">Search</a></li>
		</ul>
	</div>

	<div class="info">Enter the ID for updating. To update via name, use search by
		name to get ID</div>

	<div class="mainbody">
		<form class="formstyle" action="VerifyidServlet" method="post">
			Enter ID : <input type="number" name="id"/> <br>
			<input type="submit" value="Update"/><br>
		</form>
	</div>

</body>
</html>