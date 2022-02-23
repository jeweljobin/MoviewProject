<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
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
		
		.signUpForm {
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
		
		.registerBtn {
			background-color: white;
			border: 2px #080A6E solid;
			color: #080A6E;
			padding: 5px;
		}
		.registerBtn:hover {
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
	<h1 style="margin-bottom: 20px;">Register</h1>
	<form action="Signup" method="post" class="signUpForm">
		<input type="text" name="username" placeholder="Username"> 
		<input type="password" name="password" placeholder="Password"> 
		<input type="email" name="email" placeholder="Email"> 
		<input type="tel" pattern="[0-9]{8}" minlength="8" maxlength="8" name="contact" placeholder="Contact">
		<input type="text" name="address" placeholder="Address">
		<input class="registerBtn" type="submit" value="Register"  />
	</form>
</div>

</body>
</html>
