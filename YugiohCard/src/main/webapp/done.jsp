<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="styles/styles.css">
<title>Cards</title>
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
		<label> Operation completed : </label> ${operation}
		<br>
	</div>
	
	<div class="mainbody">
	<!-- 
		ID : ${id }
		Name : ${name }
		Attack : ${attack }
		Defense : ${defense }
		Type : ${type }
	-->
		<table class="tablestyle">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>attack</th>
			<th>defense</th>
			<th>cardtype</th>
		</tr>
		<c:forEach items = "${cards}" var = "card">
			<tr>
				<td>${card.id }</td>
				<td>${card.name }</td>
				<td>${card.attack }</td>
				<td>${card.defense }</td>
				<td>${card.cardtype }</td>
			</tr>
		</c:forEach> 
		</table>
	</div>
	
</body>
</html>