var html = '';
	html +='<div class="d-flex justify-content-center mt-5">'
  	html +='<div class="spinner-border text-success" role="status">'
    html +='<span class="visually-hidden">Loading...</span>'
  	html +='</div>'
	html +='</div>'

$('#btnLogout').click(function () {
	$.post('UserController', {
		action: 'logout'
	}, function(response) {
		$('#divContent').html(response);
		$('#divMenu').html('');
	});
});

$('#btnRawMaterials').click(function() {
	$('#divContent').html(html);
	$.get('RawMaterialListController', {
		action: 'showRawMaterialList'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnDashboard').click(function () {
	$('#divContent').html(html);
	$.post('DashboardController' , {
		action:'showDashboard'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnDpp').click(function () {
	$('#divContent').html(html);
	$.post('DppController', {
		action: 'showDpp'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);;
	});
});

$('#btnProductionMaterial').click(function () {
	$('#divContent').html(html);
	$.post('ProductionMaterialController', {
		action: 'showProductionMaterial'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnMngDispatchType').click(function () {
	$('#divContent').html(html);
	console.log("TEST");
	$.post('DispatchTypeController', {
		action: 'showDispatchType'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnMngBranch').click(function () {
	$('#divContent').html(html);
	$.post('BranchController', {
		action: 'showBranch'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnMngSku').click(function () {
	$('#divContent').html(html);
	console.log("btnMngSKU");
	$.post('SkuController', {
		action: 'showSku'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnMngMaterial').click(function () {
	$('#divContent').html(html);
	console.log("test");
	$.post('RawMaterialController', {
		action: 'showRawMaterial'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});


$('#btnDispatching').click(function() {
	$('#divContent').html(html);
	$.get('DispatchingController', {
		action: 'showDispatching'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnReports').click(function() {
	$('#divContent').html(html);
	$.get('ReportController', {
		action: 'showReports'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});

$('#btnProducts').click(function () {
	$('#divContent').html(html);
	$.get('FinishedProductListController', {
		action: 'showFinishedProductList'
	}, function(response) {
		$('#btnCloseOffNavbar').click();
		$('#divContent').html(response);
	});
});