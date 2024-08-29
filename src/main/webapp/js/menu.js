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

$('#btnMngDispatchType').click(function () {
	$.post('DispatchTypeController', {
		action: 'showDispatchType'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngBranch').click(function () {
	$.post('BranchController', {
		action: 'showBranch'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngSku').click(function () {
	$.post('SkuController', {
		action: 'showSku'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngRawMaterial').click(function () {
	$.post('RawMaterialController', {
		action: 'showRawMaterial'
	}, function(response) {
		$('#divContent').html(response);
	});
});