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
$('#btnMaintenance').click(function() {
	$.get('MaintenanceController', {
		action: 'showMaintenance'
	}, function(response) {
		$('#divContent').html(response);
	});
});