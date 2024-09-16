$('#btnDashboardDpp').click(function () {
	$('#divContent').html(html);
	$.post('DppController', {
		action: 'showDpp'
	}, function(response) {
		if (response.includes("login")){
			alert("login expired. Please Login again");
			$('#divMenu').html('');
			$('#divContent').html(response);
		} else {
			$('#btnCloseOffNavbar').click();
			$('#divContent').html(response);;
		}
	});
});

$('#btnDashboardDispatch').click(function() {
	$('#divContent').html(html);
	$.get('DispatchingController', {
		action: 'showDispatching'
	}, function(response) {
		if (response.includes("login")){
			alert("login expired. Please Login again");
			$('#divMenu').html('');
			$('#divContent').html(response);
		} else {
			$('#btnCloseOffNavbar').click();
			$('#divContent').html(response);;
		}
	});
});

$('#btnDashboardReports').click(function() {
	$('#divContent').html(html);
	$.get('ReportController', {
		action: 'showReports'
	}, function(response) {
		if (response.includes("login")){
			alert("login expired. Please Login again");
			$('#divMenu').html('');
			$('#divContent').html(response);
		} else {
			$('#btnCloseOffNavbar').click();
			$('#divContent').html(response);;
		}
	});
});

$('#btnDashboardRawMaterials').click(function() {
	$('#divContent').html(html);
	$.get('RawMaterialListController', {
		action: 'showRawMaterialList'
	}, function(response) {
		if (response.includes("login")){
			alert("login expired. Please Login again");
			$('#divMenu').html('');
			$('#divContent').html(response);
		} else {
			$('#divContent').html(response);
		}
	});
});
