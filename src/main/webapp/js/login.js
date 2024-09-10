$('#btnLogin').click(function() {
	$.post('UserController', {
		action: 'login',
		username: $('#txtUsername').val(),
		password: $('#txtPassword').val()
	}, function(response) {
		if (response.includes('Invalid Username or Password')) {
			$('#divMessage').append(response);
			var $toastLiveExample = $('#liveToast');

			var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);

			toastBootstrap.show();
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

$('#txtPassword').on('keypress',function(e) {
    if(e.which == 13) {
        $('#btnLogin').click();
    }
});