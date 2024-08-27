$('#btnLogin').click(function() {
	$.post('UserController', {
		action: 'login',
		username: $('#txtUsername').val(),
		password: $('#txtPassword').val()
	}, function(response) {
		if (response.includes('Invalid Username or Password')) {
			$('#divMessage').html(response);
		} else {
			$('#divMenu').html(response);
			$('#btnDashboard').click();
		}
	});
});

/*
{
		action: 'register',
		username: $('#txtRegUsername').val(),
		password: $('#txtRegPassword').val(),
		branchId: $('#txtBranchId')
	}
*/

$('#btnRegisterLink').click(function(){
	$('#divContent').load('pages/registration.jsp');
})

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