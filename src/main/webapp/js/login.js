/**
 * 
 */

console.log('test');

$('#login').click(function() {
	console.log("login");
		$.post("login", {
			action: "login",
			username: "Tingyun",
			password: "Myp@ssw0rd"
		}, function(response) {
			console.log(response);
			$('#content').html(response);
		});
});