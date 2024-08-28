$('#btnLogin').click(function() {
	$.post('UserController', {
		action: 'login',
		username: $('#txtUsername').val(),
		password: $('#txtPassword').val()
	}, function(response) {
		if (response.includes('Invalid Username or Password')) {
			alert(response);
		} else {
			$('#divMenu').html(response);
			$('#btnDashboard').click();
			$('divHeader').html(response);
		}
	});
});

function checkUserSession() {
	$.get('UserController', {
		action: 'checkUserSession'
	}, function(response) {
		if (response !== 'No existing user session') {
			$('#divMenu').html(response);
			$('#btnDashboard').click();
		}
	});
}

checkUserSession();