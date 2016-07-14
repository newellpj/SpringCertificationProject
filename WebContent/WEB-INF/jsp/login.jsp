<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap-custom.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/myStyles.css">

<title>Login Page</title>
<style>



</style>
</head>
<body background="./presentationResources/images/bgimg.jpg" >

	

	<div id="login-box">

		<h3>PJs Book Reviews Login Page</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>
		
		<p>Sign in already a user otherwise <a href="signup">Sign up</a></p>
<br/><br/>
		<form 
			action="<c:url value='/j_spring_security_check' />" method='POST'>

			<table style="width:100%;">
				<tr>
					<td>Username:</td>
					<td><input class="usernameInput" style="width:205px !important;" type='text' name='username'><span class="glyphicon glyphicon-user iconspan"></span></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' style="width:205px !important;" />  <span class="glyphicon glyphicon-lock iconspan"></span></td>
				</tr>
				<tr>
					<td colspan='1'></td></td><td colspan='1'><button name="submit" type="submit" style="margin-left:150px !important;"
						value="Login" >	 <span class="glyphicon glyphicon-log-in"></span> &nbsp;Login </button></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>