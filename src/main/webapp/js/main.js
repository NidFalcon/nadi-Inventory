function checkUserSession() {
	$.get('UserController', {
		action: 'checkUserSession'
	}, function(response) {
		if (response.includes('showRegisterPage')) {
			$('#divContent').html(response);
		} else {
			$('#divMenu').html(response);
			$('#btnDashboard').click();
		}
	});
}

//checkUserSession();