$('#btnMngDispatchTypes').click(function () {
	$.post('MaintenanceController', {
		action: 'showMngDispatchTypes'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngBranches').click(function() {
	$.get('MaintenanceController', {
		action: 'showMngBranches'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngSkuCodes').click(function() {
	$.get('MaintenanceController', {
		action: 'showMngSkuCodes'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngMaterialCodes').click(function() {
	$.get('MaintenanceController', {
		action: 'showMngMaterialCodes'
	}, function(response) {
		$('#divContent').html(response);
	});
});