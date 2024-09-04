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

$('#btnDpp').click(function () {
	$.post('DppController', {
		action: 'showDpp'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngDispatchType').click(function () {
	console.log("TEST");
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
	console.log("btnMngSKU");
	$.post('SkuController', {
		action: 'showSku'
	}, function(response) {
		$('#divContent').html(response);
	});
});

$('#btnMngMaterial').click(function () {
	console.log("test");
	$.post('RawMaterialController', {
		action: 'showRawMaterial'
	}, function(response) {
		$('#divContent').html(response);
	});
});


$('#btnDispatching').click(function() {
	$.get('DispatchingController', {
		action: 'showDispatching'
	}, function(response) {
		$('#divContent').html(response);
	});
});