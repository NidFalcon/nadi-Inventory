function initializeApp() {
	$.get('MainController', {
		action: 'initializeApp'
	}, null);
}

$('#btnTest').click(function(){
	console.log("test");
	$.get("UserController", {
		action: "test"
	}, function(response){
		console.log("test ending");
		console.log(response);
		$('#divContent').html(response);
	});
})

initializeApp();