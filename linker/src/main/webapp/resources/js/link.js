function link(address, title, id, read) {
	this.address = address;
	this.title = title;
	this.read = read;
	this.id = id;

	this.setTitle = function(newTitle) {
		this.title = newTitle;
	};

	this.setAddress = function(newAddress) {
		this.address = newAddress;
	};

	this.setRead = function() {
		if(this.read === false) this.read = true;
		else this.read = false;
	};

	this.getTitle = function() {
		return title;
	};

	this.getAddress = function(){
		return address;
	};

	this.getRead = function() {
		return read;
	};
	
	this.setId = function(id){
		this.id = id;
	};
	
}


