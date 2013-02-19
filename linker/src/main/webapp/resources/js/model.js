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
			var current=new link(jq('#address').val(), jq('#title').val())
			var completed=0;
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("/linker/add",
							{ 	urll:  current.getAddress(),
						  		title:  current.getTitle()},
//						  		readed: current.getRead()},
//								function(data){
//									// data contains the result
//									// Assign result to the sum id
//									completed=data;
							});
			});
				myLink.push(current);
				new linkItem(jq('#link-display'), myLink[myLink.length-1]);
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
	linkDisplayDiv.append('<div id="linkItem"></div>');
	$('#linkItem').attr('id', 'linkItem'+ myLink.length);
	$('#linkItem'+myLink.length).append('<a id="reference" class="ass" target=blank>' + link.getTitle() + '</a>');
	$('#linkItem'+myLink.length).addClass('item');
	$('#reference').attr('id', 'reference'+ myLink.length);
	$('#reference'+ myLink.length).attr('href', link.getAddress());
	$('#linkItem'+myLink.length).append('<button class="removeItemBtn">delete</button>');
	$('.removeItemBtn').click(function(){
		var title = $(this).prev('.ass').text();

		for(i = 0; i < myLink.length; i++){
			if(title === myLink[i].getTitle()){
				myLink.splice(i, 1);
			}
		}
		
		$(this).parent().remove();	
	});
}
