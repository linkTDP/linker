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
		jq.post("/linker/home/getAll",
					{},
						function(data){
							// data contains the result
							// Assign result to the sum id
				  			console.log(data); // It's works ;)
				 
				  			console.log(data.length);
				  			for(var i = 0; i < data.length; i++) {
				  				var linkTMP = new link(data[i].url, data[i].title, data[i].pid, data[i].read, data[i].date);
				  				myLink.push(linkTMP);
				  				new linkItem(jq('#link-display'), myLink[myLink.length-1]);
				  			}
				  			console.log('dimensione myLink = '+myLink.length);
					}
				  	, 'json');
	});
	
	console.log('dimensione myLink= '+myLink.length);
	
	$add.click(function(){
		new model($add, $home);
	});
});