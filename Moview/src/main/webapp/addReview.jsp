<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback on the movie</title>
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
</style>
</head>
<body>
    <a href="<%=request.getContextPath()%>/ReviewMovies" class="btn btn-success" style = "position:relative; left:50px; top:40px" >Back to Reviews</a>
	<div style="display:flex; flex-direction: column; align-items: center;">
		<h1 style="font-weight: 700; margin-bottom: 50px;">Post a review about a movie</h1>
		<div>
			<form action="AddReviewServlet" method="post">
				<input type="text"name="movie" placeholder="Movie Name">
				<br>
				<input type="text" name="rating" placeholder="Rating"> 
				<br>
				<input type="text" name="feedback" placeholder="Feedback">		
				<br>
				<input class="submitBtn" type="submit" value="Post" />
			</form>
		</div>
	</div>
</body>
</html>