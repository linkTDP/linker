$(document).ready(function(){
	var $emailLogin = $('#username');
	var $hintLogin = $('#hintlogin');
	var $emailSignUp = $('#emailsignup');
	var $hintSignUp = $('#hintsignup');
	var $username = $('#usernamesignup');
	var $signBtn = $('#signBtn');
	var $existUsr = $('#hintExistUsername');
	var $existEmail = $('#hintExistEmail');
	var $passSignUp = $('#passwordsignup');
	
	$signBtn.attr('disabled','disabled');
	
	$username.on('blur', function(){
		$(function() {
			// Call a URL and pass two arguments
			// Also pass a call back function
			// See http://api.jquery.com/jQuery.post/
			// See http://api.jquery.com/jQuery.ajax/
			// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
			// See http://bugs.jquery.com/ticket/7535
			$.post("existUsername",
						{ 	username: $username.val()},
							function(data){
								// data contains the result
								// Assign result to the sum id
					  			// It's works ;)
					  			if(data){
					  				console.log('username già esistente');
					  				var error = "username already used";
					  				$existUsr.html(error);
					  				
					  				}
				  				else {
					  				$signBtn.removeAttr('disabled');
				  				}
						}
					  	, 'json');
		});
	});
	
	$emailLogin.on('blur', function(){
		$hintLogin.css('display', 'none');
		$(this).mailcheck({
			suggested: function(element, suggestion){
				if(!$hintLogin.html()) {
		        // First error - fill in/show entire hint element
		        var suggestion = "Did you mean <span class='suggestion'>" +
		                          "<span class='address'>" + suggestion.address + "</span>"
		                          + "@<a href='#' class='domain'>" + suggestion.domain + 
		                          "</a></span>?";
		                          
		        $hintLogin.html(suggestion).fadeIn(150);
		      	} 
		      	else {
			        // Subsequent errors
			        $(".address").html(suggestion.address);
			        $(".domain").html(suggestion.domain);
			        
		      	}
			}
		});
		$hintLogin.on('click', '.domain', function() {
		  // On click, fill in the field with the suggestion and remove the hint
		  $emailLogin.val($(".suggestion").text());
		  $hintLogin.fadeOut(200, function() {
		    $(this).empty();
		  });
		  return false;
		});
	});
	$emailSignUp.on('blur', function(){
		
		$(function() {
			// Call a URL and pass two arguments
			// Also pass a call back function
			// See http://api.jquery.com/jQuery.post/
			// See http://api.jquery.com/jQuery.ajax/
			// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
			// See http://bugs.jquery.com/ticket/7535
			$.post("existMail",
						{ 	mail: $emailSignUp.val()},
							function(data){
								// data contains the result
								// Assign result to the sum id
					  			// It's works ;)
					  			if(data){
					  				console.log('email già esistente');
					  				var error = "email already used";
					  				$existEmail.html(error);
					  				
					  				}
				  				else {
					  				$signBtn.removeAttr('disabled');
				  				}
						}
					  	, 'json');
		});
		
		$hintSignUp.css('display', 'none');
		$(this).mailcheck({
			suggested: function(element, suggestion){
				if(!$hintSignUp.html()) {
		        // First error - fill in/show entire hint element
		        var suggestion = "Did you mean <span class='suggestion'>" +
		                          "<span class='address'>" + suggestion.address + "</span>"
		                          + "@<a href='#' class='domain'>" + suggestion.domain + 
		                          "</a></span>?";
		                          
		        $hintSignUp.html(suggestion).fadeIn(150);
		      	} 
		      	else {
			        // Subsequent errors
			        $(".address").html(suggestion.address);
			        $(".domain").html(suggestion.domain);
			        
		      	}
			}
		});
		$hintSignUp.on('click', '.domain', function() {
		  // On click, fill in the field with the suggestion and remove the hint
		  $emailSignUp.val($(".suggestion").text());
		  $hintSignUp.fadeOut(200, function() {
		    $(this).empty();
		  });
		  return false;
		});
	});
	
	$signBtn.on('click', function(){
		$(function() {
			$.post("newUser",
						{ 	username: $username.val(),
							email: $emailSignUp.val(),
							password: $passSignUp.val()},
							function(data){
								$('#toLogin')[0].click();								
								var n = noty({
									text: 'The user '+$username.val()+' created',
									type:'success',
									layout: 'bottomRight',
							  		theme: 'defaultTheme'
									});
						}
					  	, 'json');
		});
	});
});

