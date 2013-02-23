$(document).ready(function(){
	var $emailLogin = $('#username');
	var $hintLogin = $('#hintlogin');
	var $emailSignUp = $('#emailsignup');
	var $hintSignUp = $('#hintsignup');

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
})

