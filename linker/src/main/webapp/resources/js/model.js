function model(button, div){
	var jq = jQuery.noConflict();
	button.prop('disabled', true);
	div.addClass('blackOverlay');
	jq('body').append('<div id="model"></div>');
	jq('model').append('<div class="span12"></div>');
	jq('#model').append('<input [name=linkAddress] type="text" id="address" autofocus>');
	jq('#model').append('<input type="text" id="title">');
	jq('#model').append('<button class="close" id="closeBtn">&times</button>');
	jq('#model').append('<button class="btn" id="add">add</button>');
	 
	jq('#add').click(function(){
		if(jq('#address').val() === "" || jq('#title').val() === "") {
			jq('#model').append('<div class="alert"></div>');
			jq('.alert').append('<button type="button" class="close" data-dismiss="alert">&times;</button>');
  			jq('.alert').append('<strong>Warning!</strong> Best check yo self, you re not looking too good.');				
		}
		else{
			var current=new link(jq('#address').val(), jq('#title').val());
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("/linker/add",
							{ 	urll:  current.getAddress(),
						  		title:  current.getTitle(),
						  		readed: current.getRead()},
								function(data){
									// data contains the result
									// Assign result to the sum id
						  			console.log(data); // It's works ;)
//						  			completed=data;
						  			if(data){
						  				myLink.push(current);
										new linkItem(jq('#link-display'), myLink[myLink.length-1]);
						  			}	
							}
						  	, 'json');
			});
//			console.log(completed);
//			if(completed){
//				myLink.push(current);
//				new linkItem(jq('#link-display'), myLink[myLink.length-1]);
//			}
			div.removeClass('blackOverlay');
			jq('#model').remove();
			button.prop('disabled', false);
			
		}
	});
	
	jq('#closeBtn').click(function(){
		div.removeClass('blackOverlay');
		jq('#model').remove();
		button.prop('disabled', false);
	});
}

function linkItem(linkDisplayDiv, link) {
	var jq = jQuery.noConflict();
	linkDisplayDiv.append('<div id="linkItem"></div>');
	jq('#linkItem').attr('id', 'linkItem'+ myLink.length);
	jq('#linkItem'+myLink.length).append('<a id="reference" class="ass" target=blank>' + link.getTitle() + '</a>');
	jq('#linkItem'+myLink.length).addClass('item');
	jq('#reference').attr('id', 'reference'+ myLink.length);
	jq('#reference'+ myLink.length).attr('href', link.getAddress());
	jq('#linkItem'+myLink.length).append('<button class="removeItemBtn">delete</button>');
	jq('.removeItemBtn').click(function(){
		var title = jq(this).prev('.ass').text();

		for(i = 0; i < myLink.length; i++){
			if(title === myLink[i].getTitle()){
				myLink.splice(i, 1);
			}
		}
		
		jq(this).parent().remove();	
	});
}
