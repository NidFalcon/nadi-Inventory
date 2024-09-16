var skuTable = new Tabulator("#divSkuTable" , {
	layout: 'fitColumns',
	data: sku,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector: [5, 10, 15, 20],
	paginationCounter: "rows",
	selectableRows: 1,
	movableColumns: true,
	responsiveLayout: true,
	initialSort: [
		{ column: "skuCode", dir: "asc" }
	],
	columns: [
		{ title: "SKU Code", field: 'skuCode' },
		{ title: "SKU Name", field: 'skuName' },
		{ title: "Unit of Measurement", field: 'unitOfMeasurement' },
		{ title: "Active?", field: 'isActive' }
	],
});

$('#btnShowUpdateSku').hide();
$('#btnShowDeleteSku').hide();

skuTable.on('rowClick', function() {
	let row = skuTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowUpdateSku').show();
		$('#btnShowDeleteSku').show();
	} else {
		$('#btnShowUpdateSku').hide();
		$('#btnShowDeleteSku').hide();
	}
})

function populateForm(row) {
	if (row !== undefined) {
		$('#updateSkuCode').val(row.skuCode);
		$('#updateSkuName').val(row.skuName);
		$('#updateSkuUnitOfMesaurement').val(row.unitOfMeasurement);
		row.isActive === 'y' ? $('#updateIsActive').prop('checked', true) : $('#updateIsActive').prop('checked', false);
	}
}

function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteSkuCode').val(row.skuCode)
		$('#deleteSkuName').val(row.skuName);
		$('#deleteSkuMeasurement').val(row.unitOfMeasurement);
		$('#deleteSkuisActive').val(row.isActive);	
	}
}

function createItem(crudOperation) {
	let item;

	if (crudOperation === "add") {
		item = {
			isActive: $('#chkIsActive').is(':checked') ? 'y' : 'n',
			skuCode: $('#txtSkuCode').val() !== '' ? $('#txtSkuCode').val() : '',
			skuName: $('#txtSkuName').val(),
			unitOfMeasurement: $('#txtUnitOfMeasurement').val()
		};
	} else if (crudOperation === "update") {
		item = {
			isActive: $('#updateIsActive').is(':checked') ? 'y' : 'n',
			skuCode: $('#updateSkuCode').val() !== '' ? $('#updateSkuCode').val() : '',
			skuName: $('#updateSkuName').val(),
			unitOfMeasurement: $('#updateSkuUnitOfMesaurement').val()
		};
	} else if (crudOperation === "delete") {
		item = {
			isActive: $('#deleteIsActive').is(':checked') ? 'y' : 'n',
			skuCode: $('#deleteSkuCode').val() !== '' ? $('#deleteSkuCode').val() : '',
			skuName: $('#deleteSkuName').val(),
			unitOfMeasurement: $('#deleteSkuMeasurement').val()
		};
	}
	
	return item;
}


function validate(item) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let valid = true;
	if (item.skuName === '') {
		$('#errorMessage').html('Please fill out the SKU Name');
		toastMessage.show();
		valid = false;
	}
	return valid;
}

function addItem(crudOperation) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let item = createItem(crudOperation);
	console.log(item);
	if (validate(item)) {
		$.post('SkuController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('#divAlert').html(response);	
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnMngSku').click();
			} else {
				$('#errorMessage').html('Unable to save changes');
				toastMessage.show();
			}
		});
	}
}

$('#btnAddSku').click(function() {
	addItem("add");
});
$('#btnUpdateSku').click(function() {
	addItem("update");
});

$('#btnDeleteSku').click(function() {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	if ($('#deleteSkuCode').val() !== '') {
		$.post('SkuController', {
			action: 'deleteItem',
			item: JSON.stringify(createItem("delete"))
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteSkuCancel').click();
				$('#divAlert').html(response);	
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnMngSku').click();
			} else {
				$('#errorMessage').html('Unable to save changes');
				toastMessage.show();
			}
		});
	} else {
		$('#errorMessage').html('Please select an item to delete');
		toastMessage.show();
	}
});

