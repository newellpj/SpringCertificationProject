<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap-custom.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/myStyles.css">

<script type="text/javascript" src="./presentationResources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery-ui.js"></script>
<script type="text/javascript" src="./presentationResources/js/reviews.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Reviews</title>
<head>

<script>

$(document).ready(function() {
	if($("#titleTextAdd").val() == '' || $("#authorTextAdd").val() == ''){
			$( '#addBook').prop('disabled', true);
		}
	 
	  $('#authorTextAdd').keyup(function() {
	     if($("#titleTextAdd").val() != '' && $("#authorTextAdd").val() != '') {
	        $('#addBook').prop('disabled', false);
	     }else{
			$( '#addBook').prop('disabled', true);
		 }
	  });
	  
	  $('#titleTextAdd').keyup(function() {
	     if($("#titleTextAdd").val() != '' && $("#authorTextAdd").val() != '') {
	        $('#addBook').prop('disabled', false);
	     }else{
			$('#addBook').prop('disabled', true);
		 }
	  });
	  
	  $('#authorTextAdd').blur(function() {
	     if($("#titleTextAdd").val() != '' && $("#authorTextAdd").val() != '') {
	        $('#addBook').prop('disabled', false);
	     }else{
			$( '#addBook').prop('disabled', true);
		 }
	  });
	  
	  $('#titleTextAdd').blur(function() {
	     if($("#titleTextAdd").val() != '' && $("#authorTextAdd").val() != '') {
	        $('#addBook').prop('disabled', false);
	     }else{
			$('#addBook').prop('disabled', true);
		 }
	  });

});

(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.6";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

</script>

<style>

</style>
</head>

<body background="./presentationResources/images/bgimg.jpg">

<br/>
	<div id="add-book-box">

		<h3>Add a Book to Review</h3>

		<c:if test="${not empty error}">
			<div class="error alert alert-error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>
		
		<p><span style="align-center;">Title and Author are mandatory</span></p>

<br/><br/>
			<form:form id="reviewsForm" action="searchOrAddBook" method="post" commandName="bookReviewsModel">

		<table style="width:100%;">
			
				<tr>
					<td>Title:</td>
					<td><input style="width:250px !important;" id="titleTextAdd" type='text' name='titleTextAdd'><span class="glyphicon glyphicon-book iconspan3"></span></td>
				</tr>
				<tr>
					<td>Author:</td>
					<td><input style="width:250px !important;" id="authorTextAdd" type='text' name='authorTextAdd' /><span class="glyphicon glyphicon-pencil iconspan3"></span></td>
				</tr>
				<tr>
					<td>Publisher:</td>
					<td><input id="publisherTextAdd" style="width:250px !important;" type='text' name='publisherTextAdd' /><span class="glyphicon glyphicon-barcode iconspan2"></span></td>
				</tr>
				<tr>
					<td colspan="1"></td><td colspan='2'><button id="addBook" name="addBook" type="button"
						value="Add a book.." onclick="performAjaxAddBook();" >
						<span class="glyphicon glyphicon-plus-sign"  ></span>Add a Book... </button>
						</td>
				</tr>
				<tr><td><span style="visibility:hidden;">placeholder</span></td></tr>
	
				
			</table>
			
			  <fieldset>
                <h4>Add attributes to enable better searching</h4>
                   <div class="tagSearches"> 
						<table width="100%">
							<tr>
								<td><input id="genre" type="checkbox" name="genre" value="genre" onclick="renderTagList($(this));"/>Genre <br /> </td>
								<td><select style="visibility:hidden;" id="genreSelect"></select></td>
							</tr>
							<tr>
								<td><input id="category" type="checkbox" name="category" value="category" onclick="renderTagList($(this));" />Category<br /></td>
								<td><select style="visibility:hidden;" id="categorySelect"></select></td>
							</tr>
							<tr>
								<td><input id="language" type="checkbox" name="language" value="language" onclick="renderTagList($(this));" />Language<br /></td>
								<td><select style="visibility:hidden;" id="languageSelect"></select></td>
							</tr>
						</table>
					</div>
					
						<ul id="bookTagsList" class="bookTagsList">
						</ul>
           
			</fieldset>
			


		</form:form>
	</div>
<div style="margin-left:300px;" >
	<div id="fb-root" ></div>
<div class="fb-like" data-href="http://www.w3schools.com/" data-layout="standard" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>
</div>
</body>
</html>