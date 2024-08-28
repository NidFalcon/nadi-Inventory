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
