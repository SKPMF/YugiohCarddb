<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="styles/styles.css">
<title>Search Card</title>
</head>
<body>
	<div class="navbar">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="ListAllServlet">List All</a></li>
			<li><a href="insert.jsp">Insert</a></li>
			<li><a href="update.jsp">Update</a></li>
			<li><a href="delete.jsp">Delete</a></li>
			<li class="active"><a href="search.jsp">Search</a></li>
		</ul>
	</div>

	<div class="info">Search for a card : Choose any of the options</div>

	<div class="mainbody">
		<form class="formstyle" action="SearchServlet" method="post">
			Category : <select name="category">
				<option value="id">id</option>
				<option value="name">name</option>
				<option value="attack">attack</option>
				<option value="defense">defense</option>
				<option value="cardtype">cardtype</option>
			</select><br>
			
			Value : <input type="text" name="value"/><br>
			
			<input type="submit" value="Insert"/><br>
		</form>
	</div>

</body>
</html>