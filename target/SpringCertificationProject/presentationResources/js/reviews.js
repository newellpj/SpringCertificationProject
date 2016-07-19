
function jscroller(){
 $('.infinite-scroll').jscroll({
     loadingHtml: "<div class='ajax-loader-2 help-inline pull-right'> Loading...'</div>",
     padding: 20,
     nextSelector: 'a.jscroll-next:last',
     contentSelector: 'li'
 });
}

function checkAllFields(){
	if($("#authorText").val() == '' && $("#titleText").val() == '' && $("#publisherText").val() == '' &&
		  ($("#genreSelect").val() == '' || $("#genreSelect").val() == null) && 
		  ($("#categorySelect").val() == '' || $("#categorySelect").val() == null) && 
		   ($("#languageSelect").val() == '' || $("#languageSelect").val() == null)){
			   $( '#searchBook').prop('disabled', true);
		   }
}



function searchPageReadyInit(){
	   
	   
	   
	   if($("#authorText").val() == '' && $("#titleText").val() == '' && $("#publisherText").val() == '' &&
		  ($("#genreSelect").val() == '' || $("#genreSelect").val() == null) && 
		  ($("#categorySelect").val() == '' || $("#categorySelect").val() == null) && 
		   ($("#languageSelect").val() == '' || $("#languageSelect").val() == null)){
			$( '#searchBook').prop('disabled', true);
		}
		
		$('#genreSelect').change(function() {
			 
	         if($("#genreSelect").val() != '' && $("#genreSelect").val() != null) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				checkAllFields();
			 }
	     });
		 
		 $('#genre').change(function() {
			 
	        if($("#genreSelect").val() != '' && $("#genreSelect").val() != null && document.getElementById("genre").checked) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				 checkAllFields();
			 }
	     });
		 
		 
		 $('#categorySelect').change(function() {
			 
	         if($("#categorySelect").val() != '' && $("#categorySelect").val() != null ) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				 checkAllFields();
			 }
	     });
		 
		 $('#category').change(function() {
			 
	         if($("#categorySelect").val() != '' && $("#categorySelect").val() != null && document.getElementById("category").checked) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				 checkAllFields();
			 }
	     });
		 
		 
		 $('#languageSelect').change(function() {
			
	        if($("#languageSelect").val() != '' && $("#languageSelect").val() != null) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				 checkAllFields();
			 }
	     });
		 
		 $('#language').change(function() {
			 
	        if($("#languageSelect").val() != '' && $("#languageSelect").val() != null && document.getElementById("language").checked) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				checkAllFields();
			 }
	     });
		
	    
	     $('#authorText').keyup(function() {
			 
	        if($("#authorText").val() != '' ) {
	           $('#searchBook').prop('disabled', false);
	        }else{
				checkAllFields();
			 }
	     });
	
		  $('#titleText').keyup(function() {
		         if($("#titleText").val() != '') {
		            $('#searchBook').prop('disabled', false);
		         }else{
					checkAllFields();
				 }
		   });
		   
		   $('#titleText').blur(function() {
		         if($("#titleText").val() != '' ) {
		            $('#searchBook').prop('disabled', false);
		         }else{
					checkAllFields();
				 }
		   });
		   
		    $('#authorText').blur(function() {
		         if($("#authorText").val() != '' ) {
		            $('#searchBook').prop('disabled', false);
		         }else{
					 checkAllFields();
				 }
		   });
		   
		    $('#publisherText').keyup(function() {
		         if($("#publisherText").val() != '') {
		            $('#searchBook').prop('disabled', false);
		         }else{
					 checkAllFields();
				 }
		   });
		   
		    $('#publisherText').blur(function() {
		         if($("#publisherText").val() != '') {
		            $('#searchBook').prop('disabled', false);
		         }else{
					checkAllFields();
				 }
		   });
		   
}


function noBookToReview(){
	
	var errorDialog = $("<div></div>").dialog({
				hide: 'fade',
				maxWidth: 300,
				modal: true,
				show: 'fade',
				open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
					buttons: [
				{
					'class': 'btn btn-primary',
					click: function(e) {
						$(this).dialog("close");
						
					
					},
					text: 'OK'
				}
			
			],	
				title: 'Please search or add book ',
				width: ( 300 )
			});
		
		var msg = "You need to search or add a book to review";

		$(errorDialog).html('<p>'+msg+'</p>');
		 $(errorDialog).dialog("open");

}


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

function renderTagList(obj){
	
	var ID = $(obj).attr('id');

	if(document.getElementById(ID).checked){
	
		var myOptions = ""
	
		if(ID == 'genre'){
			myOptions = [{ text: 'Please select..', value: ''}, { text: 'Thriller', value: 'Thriller'}, {text : 'Crime', value: 'Crime'},
							 {text : 'Biography', value: 'Biography'}, {text : 'Philosophy', value: 'Philosophy'},
							 {text : 'Romance', value: 'Romance'}];		
		}else if(ID == 'category'){
			myOptions= [{ text: 'Please select..', value: ''}, { text: 'Fiction', value: 'Fiction'}, {text : 'Non-fiction', value: 'Non-fiction'}];	 
		}else{
			myOptions= [{ text: 'Please select..', value: ''}, { text: 'English', value: 'English'}, {text : 'French', value: 'French'},
			 {text : 'Mandarin', value: 'Mandarin'}, {text : 'Hindi', value: 'Hindi'}, {text : 'Latin', value: 'Latin'},  {text : 'Spanish', value: 'Spanish'}];
		}
		
		 $('#'+ID+'Select').html('');
		
		$.each(myOptions, function(i, el) { 
		      
			   $('#'+ID+'Select').append( new Option(el.text,el.value) );
		});			
			
		 document.getElementById(ID+'Select').style.visibility = 'visible';
		document.getElementById(ID+'Select').style.display = 'inline';
							 
	}else{
		document.getElementById(ID+'Select').selectedIndex = 0;
		document.getElementById(ID+'Select').style.display = 'none';
		document.getElementById(ID+'Select').style.visibility = 'hidden';
	}
	
	
	
}



  function performAjaxAddReview(){
	
	 
	 	 var dlg = $("<div></div>").dialog({
			hide: 'fade',
			maxWidth: 300,
			modal: true,
			show: 'fade',
			title: 'Submitting Review....',
			width: ( ('__proto__' in {}) ? '300' : 300 )
		});

		$(dlg).parent().find('button').remove();
		$(dlg).html("<div class='ajax-loader-2 help-inline pull-right'></div><div><p>Adding a book review </p></div>");	
		$(dlg).dialog("open");
		
		
		var authorTextVal = $('#bookAuthorReview').val();
		var titleTextVal = $('#bookTitleReview').val(); 
		var reviewTextVal = $('#reviewText').val();
		
		
		
		$.ajax({
			url: 'addBookReview',
			dataType: 'JSON',
			data: { 
				titleText: titleTextVal,
				authorText: authorTextVal, 
				reviewText: reviewTextVal
			},
			processData: true,
			contentType: 'application/json; charset=utf-8',
			type: 'GET',
			success:  function(bookReviewsModel) {
			    $(dlg).dialog("close");
				window.location.href = 'reviewsReviewBook';
				
			 },

		 error: function(e){

	
				$(dlg).dialog("close");

				var errorDialog = $("<div></div>").dialog({
						hide: 'fade',
						maxWidth: 300,
						modal: true,
						show: 'fade',
						open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
							buttons: [
						{
							'class': 'btn btn-primary',
							click: function(e) {
								$(this).dialog("close");
								
							
							},
							text: 'OK'
						}
					
					],	
						title: 'Could NOT add book review!',
						width: ( 300 )
					});

					
					
					var msg = e.errorMessage;
					
					if('undefined' == msg || msg == null){
							msg = "There was an error adding the book review";
					}
					
					$(errorDialog).html('<p>'+msg+'</p>');
					
					 $(errorDialog).dialog("open");
			         window.parent.location.href = 'logout'; 
		 }
		});  
		
  }
 
 function performAjaxAddBook(){
	
	 
	 	 var dlg = $("<div></div>").dialog({
			hide: 'fade',
			maxWidth: 300,
			modal: true,
			show: 'fade',
			title: 'Submitting Book....',
			width: ( ('__proto__' in {}) ? '300' : 300 )
		});

		$(dlg).parent().find('button').remove();
		
		$(dlg).html("<div class='ajax-loader-2 help-inline pull-right'></div><div><p>Submitting book title and author </p></div>");
		$(dlg).dialog("open");
		
		var authorTextVal = $('#authorTextAdd').val();
		var titleTextVal = $('#titleTextAdd').val(); 
		var publisherTextVal = $('#publisherTextAdd').val();   
		var genreTextVal = $('#genreSelect').val();  
		var catTextVal = $('#categorySelect').val();
		var langTextVal = $('#languageSelect').val();
		
		$.ajax({
			url: 'addNewBook',
			dataType: 'JSON',
			data: { 
				titleText: titleTextVal,
				authorText: authorTextVal, 
				publisherText: publisherTextVal,
				genreText: genreTextVal,
				catText: catTextVal,
				langText: langTextVal
			},
			processData: true,
			contentType: 'application/json; charset=utf-8',
			type: 'GET',
			success:  function(bookReviewsModel) {
			    $(dlg).dialog("close");
			    $('#activeSel3', parent.document).click();
				window.location.href = 'reviewsReviewBook';
				
			 },

		 error: function(e){

	
				$(dlg).dialog("close");

				var errorDialog = $("<div></div>").dialog({
						hide: 'fade',
						maxWidth: 300,
						modal: true,
						show: 'fade',
						open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
							buttons: [
						{
							'class': 'btn btn-primary',
							click: function(e) {
								$(this).dialog("close");
							},
							text: 'OK'
						}
					
					],	
						title: 'Could NOT find book!',
						width: ( 300 )
					});

					
					
					var msg = e.errorMessage;
					
					if('undefined' == msg || msg == null){
							msg = "There was an error adding the book";
					}
					
					$(errorDialog).html('<p>'+msg+'</p>');
					
					 $(errorDialog).dialog("open");
			         window.parent.location.href = 'logout'; 
		 }
		});    
		
 }
 
 function resetTheSearch(){
	 window.location.href = 'reviewsSearchBook';
	
	//resetSearches();
 }
 function resetSearches(){
	 
	 $('.bookRevList').html("");
	 
	 document.getElementById("search").style.display = "none";
	  $("select").hide();
	//document.getElementById(ID+'Select').style.visibility = 'hidden';
	
	$('#reviewsForm').trigger("reset");
	 

 } 
 
 function performAjaxSearch(){
	// $('.searchResults').trigger("reset");
	
	var html = document.getElementById("bookRevList").html;
	var innerHTML = document.getElementById("bookRevList").innerHTML;
	
	document.getElementById("resultsSection").style.visibility = "visible";	
	document.getElementById("bookRevList").innerHTML = "";
	
	if(document.getElementById("bookRevList2") != null && document.getElementById("bookRevList2") != 'undefined'){
		
		document.getElementById("bookRevList2").innerHTML = "";
		
		 $( ".bookRevList2" ).each(function( ) {
				this.innerHTML = "";
		  });
		
		
	}
	
	//alert("html : "+html);
	//alert("innerHTML : "+innerHTML);
	 
	 var dlg = $("<div></div>").dialog({
			hide: 'fade',
			maxWidth: 300,
			modal: true,
			show: 'fade',
			title: 'Searching Books....',
			width: ( ('__proto__' in {}) ? '300' : 300 )
		});

		$(dlg).parent().find('button').remove();
		
		$(dlg).html("<div class='ajax-loader-2 help-inline pull-right'></div><div><p>Searching books...</p></div>");
			
		$(dlg).dialog("open");
		
		var authorTextVal = $('#authorText').val();
		var titleTextVal = $('#titleText').val();
		var publisherTextVal = $('#publisherText').val();   
		var genreTextVal = $('#genreSelect').val();  
		var catTextVal = $('#categorySelect').val();
		var langTextVal = $('#languageSelect').val();
		
		//alert("catTextVal : "+catTextVal);
		//alert("langTextVal : "+langTextVal);
		
		$.ajax({
			url: 'searchForBook',
			dataType: "JSON",
			data: { 
				titleText: titleTextVal,
				authorText: authorTextVal, 
				publisherText: publisherTextVal,
				genreText: genreTextVal,
				catText: catTextVal,
				langText: langTextVal
			},
			processData: true,
			contentType: 'application/json; charset=utf-8',
			type: 'GET',
			success:  function(bookReviewsModel) {
			  
				//alert('bookReviewsModel reviewText : '+bookReviewsModel['reviewText']);
			//	alert('bookReviewsModel : '+JSON.stringify(bookReviewsModel, undefined, 2));
			     //$('#activeSel3', parent.document).click();
			    //$('#'+ID+'Select').append( new Option(el.text,el.value) );
			    
				document.getElementById("search").style.display = "inline";
		
				
				for(var i = 0; i < bookReviewsModel['booksList'].length ;i++){
					
					$('.bookRevList').append("<div>");
					$('.bookRevList').append(bookReviewsModel['booksList'][i]);
					var bookDetails = bookReviewsModel['booksList'][i]
					
					if("No books found" != bookDetails){
						
						bookDetails = encodeURI(bookDetails);//bookDetails.replace(/ /g, "-");	
						
						$('.bookRevList').append("&nbsp; <a style='font-style:italic !important;' href='reviewsReviewBook?titleAuthorText="+bookDetails+"'"+"> Review this");				
						$('.bookRevList').append("</a>");
					}
					
					$('.bookRevList').append("</div>");
				}
				
				
				$(".search").append("<div class='next'><a href='retrieveNextSearchSegment'>"+""+"</a> </div>");
				
				$('.resultsSection').jscroll({		  
					loadingHtml: "<center><div class='ajax-loader-2'> </div></center>"     
				});
				
				$(dlg).dialog("close");
				
				 
			 },

		 error: function(e){

	            
				$(dlg).dialog("close");

				var errorDialog = $("<div></div>").dialog({
						hide: 'fade',
						maxWidth: 300,
						modal: true,
						show: 'fade',
						open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
							buttons: [
						{
							'class': 'btn btn-primary',
							click: function(e) {
								$(this).dialog("close");
							},
							text: 'OK'
						}
					
					],	
						title: 'Could NOT find book!',
						width: ( 300 )
					});

					
					
					var msg = e.errorMessage;
					
					if('undefined' == msg || msg == null){
							msg = "There was an error retrieving book";
					}
					
					$(errorDialog).html('<p>'+msg+'</p>');
					
					 $(errorDialog).dialog("open");
					 
					window.parent.location.href = 'logout'; 
			 
		 }
		});    

 }