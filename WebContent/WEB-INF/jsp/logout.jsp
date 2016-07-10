<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>


<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap-custom.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/myStyles.css">

<title>Logout Page</title>
<style>



</style>
</head>
<body background="./presentationResources/images/bgimg.jpg" onload='document.loginForm.username.focus();'>

	

	<div id="login-box">

		<h3>PJs Book Reviews Login Page</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>
		
		<p>Click login to return to the login page </p>
<br/><br/>


			
<input style="margin-left:200px" name="login" type="button"	value="Login" onclick="window.location.href='login'" />

	
	</div>

</body>
</html>