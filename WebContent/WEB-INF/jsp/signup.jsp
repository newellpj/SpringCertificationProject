<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@page session="true"%>
<html>
<head>
<title>Sign-up to PJs Book Reviews</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #0F13F5;
	background-color: #f2dede;
	border-color: #ebccd1;
}

#signup-box {
	width: 320px;
	padding: 20px;
	margin: 60px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	

	<div id="signup-box">

		<h3>PJs Book Reviews Sign Up Page</h3>

		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>

<br/><br/>
	
		<form:form  action="signupSubmitted" method="post" commandName="usersModel">
			<table>
				<tr>
					<td>Choose a username:</td>
					<td><form:input path="username" name="username" type="text" /></td>
				</tr>
				<tr>
					<td>Choose a Password:</td>
					<td><form:input path="password" name="password"  type="password"/><br/></td>
				</tr>
				<tr>
				<td><span style="padding:70px; visibility:hidden;"></span></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Sign up!" /></td>
				</tr>
			</table>


		</form:form>
	</div>

</body>
</html>