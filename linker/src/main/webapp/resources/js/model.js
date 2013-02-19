function model(button, div){
	button.prop('disabled', true);
	div.addClass('blackOverlay');
	$('body').append('<div id="model"></div>');
	$('model').append('<div class="span12"></div>');
	$('#model').append('<input [name=linkAddress] type="text" id="address" autofocus>');
	$('#model').append('<input type="text" id="title">');
	$('#model').append('<button class="close" id="closeBtn">&times</button>');
	$('#model').append('<button class="btn" id="add">add</button>');
	 
	$('#add').click(function(){
		if($('#address').val() === "" || $('#title').val() === "") {
			$('#model').append('<div class="alert"></div>');
			$('.alert').append('<button type="button" class="close" data-dismiss="alert">&times;</button>');
  			$('.alert').append('<strong>Warning!</strong> Best check yo self, you re not looking too good.');				
		}
		else{
			myLink.push(new link($('#address').val(), $('#title').val()));
			new linkItem($('#link-display'), myLink[myLink.length-1]);
			div.removeClass('blackOverlay');
			$('#model').remove();
			button.prop('disabled', false);
		}
	});
	
	$('#closeBtn').click(function(){
		div.removeClass('blackOverlay');
		$('#model').remove();
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
