<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="styles/styles.css">
<title>Insert Card</title>
</head>
<body>
	<div class="navbar">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="ListAllServlet">List All</a></li>
			<li class="active"><a href="insert.jsp">Insert</a></li>
			<li><a href="update.jsp">Update</a></li>
			<li><a href="delete.jsp">Delete</a></li>
			<li><a href="search.jsp">Search</a></li>
		</ul>
	</div>

	<div class="info">
		Insert your Card. Enter unique ID
	</div>
	
	<div class="mainbody">
		<form class="formstyle" action="InsertServlet" method="post">
			<label>ID : </label>  : <input type="number" name="id"/><br>
			
			<label>Card Name : </label>  : <input type="text" name="name"/><br>
			
			<label>Attack Points : </label>  : <input type="number" name="attack"/><br>
			
			<label>Defense Points : </label>  : <input type="number" name="defense"/><br>
			
			<label>CardType : </label>  : <input type="text" name="cardtype"/><br>
			
			<input type="submit" value="Insert"/><br>
		</form>
	</div>
	
</body>
</html>