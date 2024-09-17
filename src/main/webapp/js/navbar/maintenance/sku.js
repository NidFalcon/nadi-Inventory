var skuTable = new Tabulator("#divSkuTable", {
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
		$('#updateSkuUnitOfMeasurement').val(row.unitOfMeasurement);
		row.isActive === 'y' ? $('#updateIsActive').prop('checked', true) : $('#updateIsActive').prop('checked', false);
	}
}

function populateDeleteForm(row) {
	if (row !== undefined) {
		$('#deleteSkuCode').val(row.skuCode);
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
			unitOfMeasurement: $('#updateSkuUnitOfMeasurement').val()
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

function initializeSkuCodeMap() {
	let skuCodeMap = {};
	$.each(sku, function(index, item) {
		skuCodeMap[item.skuCode] = true;
	});
	return skuCodeMap;
}

function initializeSkuNameMap() {
	let skuNameMap = {};
	$.each(sku, function(index, item) {
		skuNameMap[item.skuName.toLowerCase()] = true;
	});
	return skuNameMap;
}

function isSkuCodeExists(skuCode) {
	return skuCodeMap.hasOwnProperty(skuCode);
}

function isSkuNameExists(skuName) {
	return skuNameMap.hasOwnProperty(skuName.toLowerCase());
}

function validate(item) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let valid = true;
	if (item.skuName === '') {
		$('#errorMessage').html('Please fill out the SKU Name');
		toastMessage.show();
		valid = false;
	}
	if (isSkuCodeExists(item.skuCode)) {
		$('#errorMessage').html('SKU Code already exists');
		toastMessage.show();
		valid = false;
	}
	if (isSkuNameExists(item.skuName)) {
		$('#errorMessage').html('SKU Name already exists');
		toastMessage.show();
		valid = false;
	}
	return valid;
}

function recalculateSku() {
	skuCodeMap = initializeSkuCodeMap();
	skuNameMap = initializeSkuNameMap();
}

function addItem(crudOperation) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let item = createItem(crudOperation);
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
	$(this).prop('disabled', true);
	recalculateSku();
	addItem("add");
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});

$('#btnUpdateSku').click(function() {
	$(this).prop('disabled', true);
	recalculateSku();
	addItem("update");
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});

$('#btnDeleteSku').click(function() {
	$(this).prop('disabled', true);
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
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});