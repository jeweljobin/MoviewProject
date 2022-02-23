<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In</title>
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
		
		.signInForm {
			display: flex;
			flex-direction: column;
		}
		
		input {
			width: 400px; 
			height: 45px; 
			border-radius: 10px; 
			border: 1px black solid; 
			padding: 10px;
			margin-bottom: 20px;
		}
		
		.logInBtn {
			background-color: white;
			border: 2px #080A6E solid;
			color: #080A6E;
			padding: 5px;
		}
		.logInBtn:hover {
			background-color: #080A6E;
			color: white;
		}
	</style>
</head>
<body>
	<div class="topNavBar">
		<h2 style="cursor:default; font-weight: 700;">MOVIEW</h2>
		<a href="<%=request.getContextPath()%>/Signin.jsp"
			class="navHeader">LOG IN</a>
		<a href="<%=request.getContextPath()%>/Register.jsp"
			class="navHeader">REGISTER</a>
	</div>

	<div style="margin: 30px 0px 0px 300px;">
		<h1 style="margin-bottom: 20px;">Log In</h1>
		<form action="Signin" method="post" class="signInForm">
			<input type="text" name="username" placeholder="Username"> 
			<input type="password" name="password" placeholder="Password"> 
			<input type="password" name="adminCode" placeholder="Admin Code (Optional)">
			<input class="logInBtn" type="submit" value="Log in" />
		</form>
	</div>

</body>
</html>
