<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Info</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<style>
	.topNavBar {
		display: flex;
		flex-direction: row;
		padding-top: 30px;
		padding-bottom: 30px;
		padding-left: 50px;
		border-bottom: 1px black solid
	}
	
	.navHeader {
		font-size: 28px;
		margin-left: 50px;
		color: black;
	}
</style>
</head>
<body>
<c:forEach var="accounts" items="${listUsers}">
	<div class="topNavBar">
			<h2 style="cursor:default; font-weight: 700;">MOVIEW</h2>
			<a
				href="<%=request.getContextPath()%>/HomeServlet?username=<c:out value="${accounts.username}" />&password=
				<c:out value="${accounts.password}" />"
				class="navHeader">MOVIES</a>
			<a href="<%=request.getContextPath()%>/Signin.jsp"
				class="navHeader">LOG OUT</a>
	</div>
	
</c:forEach>

	<div class="row">
		<div class="container">
			<h3 class="text-center">Account Settings</h3>
			<br>
			<!-- Create a table to list out one user information -->
			<table class="table">
				<thead>
					<c:forEach var="accounts" items="${listUsers}">
						<tr>
							<th>Name:</th>
							<td><c:out value="${accounts.username}"/></td>
						</tr>
						<th>Password:</th>
						<td><c:out value="${accounts.password}" /></td>
						<tr>
							<th>Email:</th>
							<td><c:out value="${accounts.email}" /></td>
						</tr>
						<tr>
							<th>Contact:</th>
							<td><c:out value="${accounts.contact}" /></td>
						</tr>
						<tr>
							<th>Address:</th>
							<td><c:out value="${accounts.address}" /></td>
						</tr>
						<tr>
							<th>Actions</th>
							<td>
							<a href="/Moview/AccountServlet/edit?username=<c:out value='${accounts.username}'/>&password=<c:out value='${accounts.password}'/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="/Moview/AccountServlet/delete?username=<c:out value='${accounts.username}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>
