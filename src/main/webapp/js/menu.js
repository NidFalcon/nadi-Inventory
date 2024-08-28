$('#btnLogout').click(function () {
	$.post('UserController', {
		action: 'logout'
	}, function(response) {
		$('#divContent').html(response);
		$('#divMenu').html('');
	});
});

$('#btnDashboard').click(function() {
	$.get('DashboardController', {
		action: 'showDashboard'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnInventory').click(function() {
	$.get('InventoryController', {
		action: 'showInventory'
	}, function(response) {
		$('#divContent').html(response);
	});
});

// Maintenance Module
$('#btnMngDispatchTypes').click(function () {
	$.post('DispatchTypeController', {
		action: 'showDispatchTypes'
	}, function(response) {
		$('#divContent').html(response);
	});
});