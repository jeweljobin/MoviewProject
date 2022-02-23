<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" 
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
</head>
<body>
<a href="<%=request.getContextPath()%>/MovieServlet/dashboard"
class="btn btn-success" style = "position:relative; left:50px; top:40px">Back to Movies</a>
<div class="row">
<div class="container">
<h3 class="text-center">List of Movie Reviews</h3>
<hr>


<table class="table">
<thead>
<tr>
<th>Movie</th>
<th>Rating</th>
<th>Feedback</th>
<th>Actions</th>
</tr>
</thead>

<tbody>
<c:forEach var="review" items="${listReview}">

<tr>
<td>
<c:out value="${review.movie}" />
</td>
<td>
<c:out value="${review.rating}" />
</td>
<td>
<c:out value="${review.feedback}" />
</td>

<td>
<a href="edit?id=<c:out value='${review.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="delete?id=<c:out value='${review.id}' />">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>