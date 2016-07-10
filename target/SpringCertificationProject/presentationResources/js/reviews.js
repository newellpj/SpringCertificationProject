
function jscroller(){
 $('.infinite-scroll').jscroll({
     loadingHtml: "<div class='ajax-loader-2 help-inline pull-right'> Loading...'</div>",
     padding: 20,
     nextSelector: 'a.jscroll-next:last',
     contentSelector: 'li'
 });
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
				
				//var json = $.parseJSON(bookReviewsModel);
				
				
				alert('bookReviewsModel reviewText : '+bookReviewsModel['reviewText']);
				alert('bookReviewsModel : '+JSON.stringify(bookReviewsModel, undefined, 2));
				
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
	
		
		$.ajax({
			url: 'addNewBook',
			dataType: 'JSON',
			data: { 
				titleText: titleTextVal,
				authorText: authorTextVal 
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
						title: 'Could NOT find book!',
						width: ( 300 )
					});

					
					
					var msg = e.errorMessage;
					
					if('undefined' == msg || msg == null){
							msg = "There was an error adding the book";
					}
					
					$(errorDialog).html('<p>'+msg+'</p>');
					
					 $(errorDialog).dialog("open");
			 
		 }
		});    
		
 }
 
 function performAjaxSearch(){
	 
	 //hid error dialog
	
	 
	 var dlg = $("<div></div>").dialog({
			hide: 'fade',
			maxWidth: 300,
			modal: true,
			show: 'fade',
			title: 'Searching Books....',
			width: ( ('__proto__' in {}) ? '300' : 300 )
		});

		$(dlg).parent().find('button').remove();
		
		$(dlg).html("<div class='ajax-loader-2 help-inline pull-right'></div><div><p>Searching on title and author </p></div>");
			
		$(dlg).dialog("open");
		
		var authorTextVal = $('#authorText').val();
		var titleTextVal = $('#titleText').val();
		

		$.ajax({
			url: 'searchForBook',
			dataType: 'JSON',
			data: { 
				titleText: titleTextVal,
				authorText: authorTextVal 
			},
			processData: true,
			contentType: 'application/json; charset=utf-8',
			type: 'GET',
			success:  function(bookReviewsModel) {
			    $(dlg).dialog("close");
				//alert('bookReviewsModel reviewText : '+bookReviewsModel['reviewText']);
				//alert('bookReviewsModel : '+JSON.stringify(bookReviewsModel, undefined, 2));
			    
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
							msg = "There was an error retrieving book";
					}
					
					$(errorDialog).html('<p>'+msg+'</p>');
					
					 $(errorDialog).dialog("open");
			 
		 }
		});    

 }
