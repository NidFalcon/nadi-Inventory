/**
 * 
 */

console.log('test');

$('#login').click(function() {
	console.log('clicked');
	$.post("UserController", {
		action: "login",
		username: "Tingyun",
		password: "Myp@ssw0rd"
	}, function(response) {
		alert(response);
	});
});