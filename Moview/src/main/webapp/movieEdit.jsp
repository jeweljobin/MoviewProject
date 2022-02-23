<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Edit Movie</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Edit Movie</title>

<style>
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

<a href="<%=request.getContextPath()%>/MovieServlet/dashboard"
class="buttons" style = "position:relative; left:50px; top:40px">Back to Dashboard</a>


<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${movie != null}">
<form action="update" method="post">
</c:if>
<c:if test="${movie == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${movie != null}">
Edit Movie
</c:if>
<c:if test="${movie == null}">
Add New Movie
</c:if>
</h2>
</caption>
<c:if test="${movie != null}">
<input type="hidden" name="oriId" value="<c:out
value='${movie.id}' />" />
</c:if>
<fieldset class="form-group">
<label>Movie Id</label> <input type="text" value="<c:out
value='${movie.id}' />" class="form-control" name="id">
</fieldset>
<fieldset class="form-group">
<label>Movie Name</label> <input type="text" value="<c:out
value='${movie.movieName}' />" class="form-control" name="movieName">
</fieldset>
<fieldset class="form-group">
<label>Poster</label> <input type="text" value="<c:out
value='${movie.movieImage}' />" class="form-control" name="movieImage">
</fieldset>
<fieldset class="form-group">
<label>Genre</label> <input type="text" value="<c:out
value='${movie.genre}' />" class="form-control" name="genre">
</fieldset>
<fieldset class="form-group">
<label> Description</label> <input type="text" value="<c:out
value='${movie.description}' />" class="form-control" name="description">
</fieldset>
<fieldset class="form-group">
<label> Age Rating</label> <input type="text" value="<c:out
value='${movie.ageRating}' />" class="form-control" name="ageRating">
</fieldset>
<fieldset class="form-group">
<label> Trailer</label> <input type="text" value="<c:out
value='${movie.trailer}' />" class="form-control" name="trailer">
</fieldset>
<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</html>