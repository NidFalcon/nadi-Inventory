$('#btnLogin').click(function() {
	$.post('UserController', {
		action: 'login',
		username: $('#txtUsername').val(),
		password: $('#txtPassword').val()
	}, function(response) {
		if (response.includes('Invalid Username or Password')) {
			$('#divAlert').html(response);
			var $toastLiveExample = $('#liveToast');

			var toastBootstrap = bootstrap.Toast.getOrCreateInstance($toastLiveExample[0]);

			toastBootstrap.show();
		} else {
			$('#divMenu').html(response);
			$('#btnDashboard').click();
		}
	});
});

$('#btnRegister').click(function(){
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