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

$('#btnRegister').click(function(){
	//$('#divContent').load('pages/registration.jsp');
	$.get("UserController", {
		action: "showRegisterPage"
	}, function(response) {	
		$('#divContent').html(response);
	})
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

$('#txtPassword').on('keypress',function(e) {
    if(e.which == 13) {
        $('#btnLogin').click();
    }
});

checkUserSession();