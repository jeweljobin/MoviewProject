<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Movies</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

<style type="text/css">
	input {
		width: 400px; 
		height: 45px; 
		border-radius: 10px; 
		border: 1px black solid; 
		padding: 10px;
		margin-bottom: 20px;
	}
	.submitBtn {
		background-color: white;
		border: 2px #080A6E solid;
		color: #080A6E;
		padding: 5px;
	}
	.submitBtn:hover {
		background-color: #080A6E;
		color: white;
	}
	
	.buttons {
		width: 160px;
		height: 30px;
		border-radius: 10px; 
		margin-bottom: 20px;
		background-color: white;
		border: 2px #080A6E solid;
		color: #080A6E;
		padding: 10px;
	}
	
	.buttons:hover {
		background-color: #080A6E;
		color: white;
		text-decoration: none;
	}
</style>
</head>
<body>
	<a href="<%=request.getContextPath()%>/MovieServlet/dashboard" class="buttons" style = "position:relative; left:50px; top:40px" >Back to Dashboard</a>
	<div style="display:flex; flex-direction: column; align-items: center;">
		<h1 style="font-weight: 700; margin-bottom: 50px;">Add Movies!</h1>
		<div>
			<form action="AddMovieServlet" method="post">
				<input type="text"name="movieName" placeholder="Movie Name">
				<br>
				<input type="text" name="movieImage" placeholder="Movie Poster"> 
				<br>
				<input type="text" name="genre" placeholder="Genre">
				<br>
				<input type="text" name="description" placeholder="Description">		
				<br>
				<input type="text" name="ageRating" placeholder="Age Rating">		
				<br>
				<input type="text" name="trailer" placeholder="Trailer"> 		
				<br>
				<input class="submitBtn" type="submit" value="Submit" />
			</form>
		</div>
	</div>
</body>
</html>