/**
 * 
 */

$('#x').click(function() {
	console.log("Hello WOrld");
	$.post("UserController", {
		action: "login",
		username: "Tingyun",
		password: "Myp@ssw0rd"
	}, function(response) {
		alert(response);
	});
});