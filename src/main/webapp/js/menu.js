$("#btnDashboard").click(function() {
	$.get("DashboardController", {
		action: "showDashboard"
	}, function(response) {
		$("#divContent").html(response);
	});
});

$("#btnInventory").click(function() {
	$.get("InventoryController", {
		action: "showInventory"
	}, function(response) {
		$("#divContent").html(response);
	});
});

$("#btnDispatch").click(function() {
	$.get("DispatchController", {
		action: "showDispatch"
	}, function(response) {
		$("#divContent").html(response);
	});
});

$("#btnReport").click(function() {
	$.get("ReportController", {
		action: "showReport"
	}, function(response) {
		$("#divContent").html(response);
	});
});

$("#btnMaintenance").click(function() {
	$.get("MaintenanceController", {
		action: "showMaintenance"
	}, function(response) {
		$("#divContent").html(response);
	});
});


$("#btnLogout").click(function () {
	$.post("UserController", {
		action: "logout"
	}, function(response) {
		$("#divContent").html(response);
		$("#divMenu").html("");
	});
});
