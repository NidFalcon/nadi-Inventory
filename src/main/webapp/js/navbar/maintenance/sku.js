var skuTable = new Tabulator("#divSkuTable", {
	layout: 'fitDataFill',
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
		$('#btnShowUpdateSku').show();
		$('#btnShowDeleteSku').show();
	} else {
		resetForm();
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
	}
	return item;
}

function validate(item) {
	let valid = true;
	if (item.skuName === '') {
		alert('Please fill out the SKU Name');
		valid = false;
	}
	return valid;
}

function addItem(crudOperation) {
	let item = createItem(crudOperation);
	if (validate(item)) {
		$.post('SkuController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('#btnMngSku').click();
			} else {
				alert('Unable to save changes');
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
