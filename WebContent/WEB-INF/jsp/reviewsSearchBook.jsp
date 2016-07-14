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
<script type="text/javascript" src="./presentationResources/js/jquery.jscroll.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery.jscroll.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Reviews</title>
<head>

<script>
	$(document).ready(function() {

	
var html = $(".bookRevList").html();
		 
	//  if(html != 'undefined' && html != null){
	//		
		//  $('.search-box').jscroll({		  
		//	loadingHtml: "<center><div class='ajax-loader-2'> </div></center>"     
		//  });
	//	  
		 // $('.search').jscroll();
	//	  
	//   }

	
	
		searchPageReadyInit();

		
		   
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
	<div id="search-box" class="search-box">

		<h3>PJs Book Reviews Page</h3>

		<c:if test="${not empty error}">
			<div class="error alert alert-error">${error}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>
		
		<p><span style="align-center;">Please type in author and title to find a book to review.</span></p>

<br/><br/>
			<form:form id="reviewsForm"  commandName="bookReviewsModel">
	
		<table style="width:100%;">
			<tr><td colspan="3">Search a Book to Review</td></tr>		
				<tr>
					<td>Title:</td>
					<td><input id="titleText" style="width:262px !important;" type='text' name='titleText'><span class="glyphicon glyphicon-book iconspan2"></span></td>
				</tr>
				<tr>
					<td>Author:</td>
					<td><input id="authorText" style="width:262px !important;" type='text' name='authorText' /><span class="glyphicon glyphicon-pencil iconspan2"></span></td>
				</tr>
				<tr>
					<td>Publisher:</td>
					<td><input id="publisherText" style="width:262px !important;" type='text' name='publisherText' /><span class="glyphicon glyphicon-barcode iconspan2"></span></td>
				</tr>
				
						
			
			</table>
			
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
							<tr>
								<td colspan='1'></td><td> <input id="searchBook" name="searchBook" type="button" onclick="performAjaxSearch();"  value="Search.." />
								<input id="resetSearch" name="resetSearch" type="button" onclick="resetSearches();"  value="Reset" /></td>
							</tr>
					
						</table>
					</div>
					
	

			<br/>
			
		
			<div id="search" class="search" style="display:none;">
			
					<ul id="bookRevList" class="bookRevList" >
						
					</ul>

			</div>
					
			
		</form:form>
		
			

	</div>

<div style="margin-left:300px;" >
	<div id="fb-root" ></div>
<div class="fb-like" data-href="http://www.w3schools.com/" data-layout="standard" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>
</div>
</body>
</html>