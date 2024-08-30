$('#btnLogout').click(function () {
	$.post('UserController', {
		action: 'logout'
	}, function(response) {
		$('#divContent').html(response);
		$('#divMenu').html('');
	});
});

$('#btnRawMaterials').click(function() {
	$.get('RawMaterialListController', {
		action: 'showRawMaterialList'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnDashboard').click(function () {
	$.post('DashboardController' , {
		action:'showDashboard'
	}, function(response) {
		$('#divContent').html(response);
	});
});