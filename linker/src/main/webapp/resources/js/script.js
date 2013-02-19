$(document).ready(function(){
	var $add = $('#addBtn');
	var $home = $('#home');
	
	$add.click(function(){
		new model($add, $home);
	})
});