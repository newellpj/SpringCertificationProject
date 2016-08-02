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
<script type="text/javascript" src="./presentationResources/js/jquery.ellipsis.js"></script>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<script>


$(document).ready(function() {
//	  $('.add-reviews-box').jscroll({
//		 loadingHtml: "<div class='ajax-loader-2'> Loading...</div>"  	  
//	  });
	  
     //		alert('hello doc read');	     
});

window.onerror = function(msg, url, line, col, error) {
	
	$('.ui-dialog').remove();
};



</script>

</head>
<body>


<div class="add-reviews-box">
	<div id="reviews" class="reviews">
	<ul id="bookRevList2" class="bookRevList2" >				
				
				<c:if test="${not empty booksLists2}">

					
						<c:forEach var="listValue2" items="${booksLists2}">
					
						
							<div class="docsSearchSegment"> ${listValue2} &nbsp;  </div>
						</c:forEach>
						
					
						</ul>
				 <div class="next"><a href="retrieveNextSearchDocsSegment">next</a> </div>
					
			
				</c:if>
			</div>
</div>
</body>
</html>