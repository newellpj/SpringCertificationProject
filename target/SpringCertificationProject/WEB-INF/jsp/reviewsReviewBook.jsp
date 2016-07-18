<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<%@ page language="java"  session="true" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap-custom.css">
<link rel="stylesheet" type="text/css" href="./presentationResources/css/myStyles.css">

<script type="text/javascript" src="./presentationResources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery-ui.js"></script>
<script type="text/javascript" src="./presentationResources/js/reviews.js"></script>
<script type="text/javascript" src="./presentationResources/js/reviews.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery.jscroll.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery.jscroll.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Reviews</title>
<head>
<script>
$(document).ready(function() {
	
	
		
	  var html = $(".bookRevList").html();
		  
	  if(html != 'undefined' && html != null){
	
		  $('.resultsSection').jscroll({		  
			loadingHtml: "<center><div class='ajax-loader-2'> </div></center>"     
		  });
		  
		  document.getElementById("resultsSection").style.visibility = "visible";
	   }
	 
	  
	  if($("#reviewText").val().trim() == ''){
		$( '#addReview').prop('disabled', true);
	  }
	  
	 
	  
	  if($("#bookTitleReview").val() == null || $("#bookTitleReview").val().trim() == ''){
		  $("#reviewText").prop('disabled', true); 
		  noBookToReview();
	  }
    
     $('#reviewText').keyup(function() {
        if($("#reviewText").val().trim() != '') {
           $('#addReview').prop('disabled', false);
        }else{
			 $('#addReview').prop('disabled', true); 
		  }
     });
	 
	 $('#reviewText').blur(function() {
        if($("#reviewText").val().trim() != '') {
           $('#addReview').prop('disabled', false);
        }else{
			 $('#addReview').prop('disabled', true); 
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
	<div id="add-reviews-box" class="add-reviews-box">

		<h3>Add a Book Review</h3>

		<c:if test="${not empty error}">
			<div class="error alert alert-error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>

<br/>
			<form:form id="reviewsForm"  method="post" commandName="bookReviewsModel">

		<table style="width:100%;">

				<tr>
					<td>Title:</td>
					<td><input type='text'  style="width:250px !important;" id="bookTitleReview" name='bookTitleReview' disabled="true" 
					value='<%=session.getAttribute("bookTitleFound")%>'><span class="glyphicon glyphicon-book iconspan4"></span></td>
				</tr>
				<tr>
					<td>Author:</td>
					<td><input type='text' style="width:250px !important;" id="bookAuthorReview" name='bookAuthorReview' 
					disabled="true" value='<%=session.getAttribute("bookAuthorFound")%>' /><span class="glyphicon glyphicon-pencil iconspan4"></span></td>
				</tr>
				<tr>
					<td>Review:</td>
					<td><textarea style="resize: none; width:523px !important; height:212px !important;" id="reviewText" name="reviewText" rows="10" cols="70" > </textarea> <span class="glyphicon glyphicon-comment iconspan5"></span></td>
				</tr>
				<tr>
					<td colspan="1"></td><td colspan='2'><button id="addReview" name="addReview" type="button"
						value="Add Review.." onclick="performAjaxAddReview();"> <span class="glyphicon glyphicon-star glyphicon-star-empty"></span> Add Review..  </button> </td>
				</tr>
			</table>
			
		</form:form>
	</div>
			
	<div id="resultsSection" class="resultsSection">
		
				<c:if test="${not empty reviewLists}">
					<h4>Book Reviews for <%=session.getAttribute("bookTitleFound")%></h4>
					<ul id="bookRevList" class="bookRevList">
						<c:forEach var="listValue" items="${reviewLists}">
							<li>${listValue}</li>
						</c:forEach>
					</ul>
					
				 <div class="next"><a href="retrieveNextReviewsSegment">next</a> </div>
					
			
				</c:if>
		
		</div>


	

	<div style="margin-left:300px;" >
	<div id="fb-root" ></div>
<div class="fb-like" data-href="http://www.w3schools.com/" data-layout="standard" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>
</div>

</body>
</html>