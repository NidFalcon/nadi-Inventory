$("#btnLogin").click(function() {
	$.post("UserController", {
		action: "login",
		username: $("#txtUsername").val(),
		password: $("#txtPassword").val()
	}, function(response) {
		if (response.includes("Invalid Username or Password")) {
			alert(response);
		} else {
			$("#divMenu").html(response);
			$("#btnDashboard").click();
		}
	});
});

function checkUserCookie() {
	$.get("UserController", {
		action: "checkUserCookie"
	}, function(response) {
		if (response.includes("No existing user cookie")) {
			console.log(response);
		} else {
			$("#divMenu").html(response);
			$("#btnDashboard").click();
		}
	});
}

//checkUserCookie();

function checkUserSession() {
	$.get("UserController", {
		action: "checkUserSession"
	}, function(response) {
		if (response.includes("No existing user session")) {
			console.log(response);
		} else {
			$("#divMenu").html(response);
			$("#btnDashboard").click();
		}
	});
}

checkUserSession();