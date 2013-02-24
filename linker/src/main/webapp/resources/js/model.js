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
			var n = noty({
				   text: "warning",
				   type: "warning",
				   dismissQueue: true,
				   layout: 'center',
				   theme: 'defaultTheme',
				   timeout: true
			});
		}
		else{
//			var current = new link(jq('#address').val(), jq('#title').val(), 1 ,false);
//			console.log(current);
			jq(function() {
				// Call a URL and pass two arguments
				// Also pass a call back function
				// See http://api.jquery.com/jQuery.post/
				// See http://api.jquery.com/jQuery.ajax/
				// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
				// See http://bugs.jquery.com/ticket/7535
				jq.post("/linker/home/add",
							{ 	urll:  jq('#address').val(),
						  		title:  jq('#title').val(),
						  		readed: false},
								function(data){
									// data contains the result
									// Assign result to the sum id
						  			// It's works ;)
						  			if(data === -1){
						  				var n = noty({
						  				   text: "link already present",
						  				   type: "error",
						  				   dismissQueue: true,
						  				   layout: 'center',
						  				   theme: 'defaultTheme',
						  				   timeout: true
						  				});
						  			}
					  				else {
					  					var current = new link(jq('#address').val(), jq('#title').val(), data ,false);
						  				myLink.push(current);
						  				console.log(myLink.length);
										new linkItem(jq('#link-display'), current);
										//rimuove la finestra
										div.removeClass('blackOverlay');
										jq('#model').remove();
										button.prop('disabled', false);
					  				}
							}
						  	, 'json');
			});			
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
	jq('#linkItem'+myLink.length).append('<button id="removeItemBtn">delete</button>');
	jq('#removeItemBtn').attr('id', 'removeItemBtn'+ myLink.length);
	jq('#linkItem'+myLink.length).append('<button id="setRead" class="setRead">read</button>');
	jq('#setRead').attr('id', 'setRead'+ myLink.length);
	jq('#linkItem'+myLink.length).append('<label id="id" class="id"> </label>');
	jq('#id').attr('id', 'id'+ myLink.length);
	jq('#id'+ myLink.length).text(link.getId());
	
	jq('#setRead'+ myLink.length).click(function(){
		var id = jq(this).next('.id').text();
		var find = false;
		var index = 0;	
		for(var i = 0; i < myLink.length; i++){
			if(id === myLink[i].getId().toString()){
				index = i;
				find = true;
			}
		}
		if(find){
			jq(function() {
				jq.post("readed",
							{ 	id: myLink[index].id},
								function(data){	
									
								}
						  	, 'json');
			});
	
			
		}
	});
	
	jq('#removeItemBtn'+ myLink.length).click(function(){
		var id = jq(this).next('.setRead').next('.id').text();
		var find = false;
		var index = 0;	
		for(var i = 0; i < myLink.length; i++){
			if(id === myLink[i].getId().toString()){
				index = i;
				find = true;
			}
		}
		if(find){
			jq(function() {
				jq.post("/linker/home/delete",
							{ 	id: myLink[index].id},
								function(data){	
									myLink.splice(index, 1);
								}
						  	, 'json');
			});
	
			jq(this).parent().remove();
		}
	});
}