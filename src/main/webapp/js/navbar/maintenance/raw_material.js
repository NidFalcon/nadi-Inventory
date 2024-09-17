var rawMaterials = new Tabulator("#divRawMaterialTable", {
	layout: 'fitColumns',
	data: rawMaterial,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector: [5, 10, 15, 20],
	paginationCounter: "rows",
	selectableRows: 1,
	movableColumns: true,
	responsiveLayout: true,
	columns: [
		{ title: "Material Code", field: 'materialCode' },
		{ title: "Material Name", field: 'materialName' },
		{ title: "Unit of Measurement", field: 'unitOfMeasurement' },
		{ title: "Active", field: 'isActive' }
	],
});

$('#btnShowUpdateRawMaterial').hide();
$('#btnShowDeleteRawMaterial').hide();

rawMaterials.on('rowClick', function() {
	let row = rawMaterials.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowUpdateRawMaterial').show();
		$('#btnShowDeleteRawMaterial').show();
	} else {
		resetForm();
		$('#btnShowUpdateRawMaterial').hide();
		$('#btnShowDeleteRawMaterial').hide();
	}
})

function populateForm(row) {
	if (row !== undefined) {
		$('#txtUpdateRawMaterialCode').val(row.materialCode)
		$('#txtUpdateRawMaterialName').val(row.materialName);
		$('#txtUpdateRawMaterialUnit').val(row.unitOfMeasurement);
		row.isActive === 'y' ? $('#chkUpdateRawMaterialIsActive').prop('checked', true) : $('#chkUpdateRawMaterialIsActive').prop('checked', false);
	}
}

function populateDeleteForm(row) {
	if (row !== undefined) {
		$('#deleteRawMaterialCode').val(row.materialCode)
		$('#deleteRawMaterialName').val(row.materialName);
		$('#deleteRawMaterialUnit').val(row.unitOfMeasurement);
		$('#deleteRawMaterialStatus').val(row.isActive);
	}
}

function createItem(crudOperation) {
	let item;
	if (crudOperation === "create") {
		item = {
			materialCode: $('#txtMaterialCode').val() !== '' ? $('#txtMaterialCode').val() : '',
			materialName: $('#txtRawMaterialName').val(),
			unitOfMeasurement: $('#txtRawMaterialUnit').val(),
			isActive: $('#chkRawMaterialIsActive').is(':checked') ? 'y' : 'n',
		};
	} else if (crudOperation === "update") {
		item = {
			materialCode: $('#txtUpdateRawMaterialCode').val() !== '' ? $('#txtUpdateRawMaterialCode').val() : '',
			materialName: $('#txtUpdateRawMaterialName').val(),
			unitOfMeasurement: $('#txtUpdateRawMaterialUnit').val(),
			isActive: $('#chkUpdateRawMaterialIsActive').is(':checked') ? 'y' : 'n',
		};
	} else if (crudOperation === "delete") {
		item = {
			materialCode: $('#deleteRawMaterialCode').val() !== '' ? $('#deleteRawMaterialCode').val() : '',
			materialName: $('#deleteRawMaterialName').val(),
			unitOfMeasurement: $('#deleteRawMaterialUnit').val(),
			isActive: $('#deleteRawMaterialStatus').val()
		}
	}
	return item;
}

function initializeMaterialCodeMap() {
	let materialCodeMap = {};
	$.each(rawMaterial, function(index, item) {
		materialCodeMap[item.materialCode] = true;
	});
	return materialCodeMap;
}

function initializeMaterialNameMap() {
	let materialNameMap = {};
	$.each(rawMaterial, function(index, item) {
		materialNameMap[item.materialName.toLowerCase()] = true;
	});
	return materialNameMap;
}

function isMaterialCodeExists(materialCode) {
	return materialCodeMap.hasOwnProperty(materialCode);
}

function isMaterialNameExists(materialName) {
	return materialNameMap.hasOwnProperty(materialName.toLowerCase());
}

function validate(item) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let valid = true;

	if (item.materialName === '') {
		$('#errorMessage').html('Please fill out the Material Name');
		toastMessage.show();
		valid = false;
	}

	if (isMaterialCodeExists(item.materialCode)) {
		$('#errorMessage').html('Material Code already exists');
		toastMessage.show();
		valid = false;
	}

	if (isMaterialNameExists(item.materialName)) {
		$('#errorMessage').html('Material Name already exists');
		toastMessage.show();
		valid = false;
	}

	return valid;
}

function recalculateMaterials() {
	materialCodeMap = initializeMaterialCodeMap();
	materialNameMap = initializeMaterialNameMap();
}

function addItem(crudOperation) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let item = createItem(crudOperation);
	if (validate(item)) {
		$.post('RawMaterialController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseModal').click();
				$('#divAlert').html(response);
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnMngMaterial').click();
			} else {
				$('#errorMessage').html('Unable to save changes');
				toastMessage.show();
			}
		});
	}
}

$('#btnAddRawMaterial').click(function() {
	
	recalculateMaterials();
	addItem("create");
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});

$('#btnUpdateRawMaterial').click(function() {
	
	recalculateMaterials();
	addItem("update");
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});

$('#btnDeleteRawMaterial').click(function() {
	
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	if ($('#deleteRawMaterialCode').val() !== '') {
		$.post('RawMaterialController', {
			action: 'deleteItem',
			item: JSON.stringify(createItem("delete"))
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteRawMaterialCancel').click();
				$('#divAlert').html(response);
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnRawMaterials').click();
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