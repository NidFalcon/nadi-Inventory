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
		$('#btnShowUpdateRawMaterial').hide();
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
	let rawMaterial;
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

function validate(item) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let valid = true;
	if (item.materialName === '') {
		$('#errorMessage').html('Please fill out the Material Name');
		toastMessage.show();
		valid = false;
	}
	return valid;
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
			} else if (response.includes("login")) {
				$('.btnCloseModal').click();
				$('#divMenu').html('');
				$('#divContent').html(response);
				alert("login expired. Please Login again");
			} else {
				$('#errorMessage').html('Unable to save changes');
				toastMessage.show();
			}
		});
	}
}


$('#btnAddRawMaterial').click(function() {
	addItem("create");
});
$('#btnUpdateRawMaterial').click(function() {
	addItem("update");
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
			} else if (response.includes("login")) {
				$('#btnDeleteRawMaterialCancel').click();
				$('#divMenu').html('');
				$('#divContent').html(response);
				alert("login expired. Please Login again");
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
