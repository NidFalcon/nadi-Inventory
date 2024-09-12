function initializeApp() {
	$.get('MainController', {
		action: 'initializeApp'
	}, null);
}

$('#btnTest').click(function(){
	let testJson = {
        	"materialListId" : 0,
        	"materialCode" : "MAT003",
        	"quantity": "69",
        	"userId": 3,
        	"dateReceive" : "29-08-2024",
        	"branchId": "5"
	}
	
	$.get("UserController", {
		action: "test",
		item: JSON.stringify(testJson)
	}, function(response){
		console.log("test ending");
		console.log(response);
		$('#divContent').html(response);
	});
})

initializeApp();