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
			var a = new link($('#address').val(), $('#title').val());
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

