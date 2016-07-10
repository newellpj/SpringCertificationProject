<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="./presentationResources/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="./presentationResources/css/myStyles.css">

<script type="text/javascript" src="./presentationResources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery-ui.js"></script>
<script type="text/javascript" src="./presentationResources/js/reviews.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery.jscroll.js"></script>
<script type="text/javascript" src="./presentationResources/js/jquery.jscroll.min.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Reviews</title>
<head>
<script language="javascript" type="text/javascript">
  function resizeIframe(obj) {
    obj.style.height = obj.contentWindow.document.body.scrollHeight + 'px';
  }

  $(document).ready(function(){
	  
	  document.getElementById("activeSel").style.backgroundColor="#f6f6f6";
	  
	    $("myMenu li a").click(function(e) {
	        e.preventDefault();
			
			
	        $("#theI_Frame").attr("src", $(this).attr("href"));
	    })

	});

	$("#theI_Frame").jscroll();
	
	
	function switchActive(obj){
		var id = $(obj).attr("id");	
		document.getElementById(id).style.backgroundColor="#f6f6f6";
		
        $('#myMenu ul li').each(function(){
			var idFound = $(this).attr("id");
			
			if(idFound != id){
				document.getElementById(idFound).style.backgroundColor="#e9e9e9";
			}  
        });
    }
	
	$('.scroll').jscroll();



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
	
	<div id="myMenu" >
		
		<ul>
		
		  <li id="activeSel"  onclick="switchActive($(this));"><a href="reviewsSearchBook" target="theI_Frame">Search book</a></li>
		  <li id="activeSel2" onclick="switchActive($(this));"><a href="reviewsAddBook" target="theI_Frame">Add book</a></li>
		  <li id="activeSel3" onclick="switchActive($(this));"><a href="reviewsReviewBook" target="theI_Frame">Review book</a></li>
		  <li id="logout"><a href="logout">Logout</a></li>
		 </ul>			 
		 	</div>
		 
	
	
		
		
<br/>
<div style="margin-left:200px !important;">
  <iframe name="theI_Frame" id="theI_Frame" src="reviewsSearchBook" frameborder="0" scrolling="yes" onload="resizeIframe(this)">
  </iframe>
</div>



</body>
</html> 