var jq = jQuery.noConflict();
jq(document).ready(function(){
	var $add = jq('#addBtn');
	var $home = jq('#home');
	
	jq(function() {
		// Call a URL and pass two arguments
		// Also pass a call back function
		// See http://api.jquery.com/jQuery.post/
		// See http://api.jquery.com/jQuery.ajax/
		// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
		// See http://bugs.jquery.com/ticket/7535
		jq.post("/linker/getAll",
					{},
						function(data){
							// data contains the result
							// Assign result to the sum id
				  			console.log(data); // It's works ;)
				  			console.log(data.length);
				  			console.log(data[0]);
//				  			var obj=jQuery.parseJSON(data);
//				  			var obj=jq.parseJSON(data);
//				  			console.log(obj.resultCount);	
					}
				  	, 'json');
	});
	
	$add.click(function(){
		new model($add, $home);
	});
});