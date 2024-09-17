var dispatchTypeTable = new Tabulator("#divDispatchTypeTable", {
	layout: "fitColumns",
	data: dispatchType,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector: [5, 10, 15, 20],
	paginationCounter: "rows",
	selectableRows: 1,
	movableColumns: true,
	responsiveLayout: true,
	columns: [
		{ title: "Dispatch Type Code", field: 'dispatchTypeCode' },
		{ title: "Dispatch Type Name", field: 'dispatchTypeName' },
		{ title: "Active", field: 'isActive' }
	],
});

$('#btnShowTypeUpdate').hide();
$('#btnShowTypeDelete').hide();

dispatchTypeTable.on('rowClick', function() {
	let row = dispatchTypeTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnShowTypeUpdate').show();
		$('#btnShowTypeDelete').show();
	} else {
		$('#btnShowTypeUpdate').hide();
		$('#btnShowTypeDelete').hide();
	}
});

function populateForm(row) {
	if (row !== undefined) {
		$('#updateDispatchTypeCode').val(row.dispatchTypeCode);
		$('#updateDispatchTypeName').val(row.dispatchTypeName);
		row.isActive === 'y' ? $('#updatecheckActive').prop('checked', true) : $('#updatecheckActive').prop('checked', false);
	}
}

function populateDeleteForm(row) {
	if (row !== undefined) {
		$('#deleteDispatchCode').val(row.dispatchTypeCode);
		$('#deleteDispatchName').val(row.dispatchTypeName);
		$('#deleteCheckActive').val(row.isActive);
	}
}

function initializeDispatchTypeCodeMap() {
	let dispatchTypeCodeMap = {};
	$.each(dispatchType, function(index, item) {
		dispatchTypeCodeMap[item.dispatchTypeCode] = true;
	});
	return dispatchTypeCodeMap;
}

function initializeDispatchTypeNameMap() {
	let dispatchTypeNameMap = {};
	$.each(dispatchType, function(index, item) {
		dispatchTypeNameMap[item.dispatchTypeName.toLowerCase()] = true;
	});
	return dispatchTypeNameMap;
}

function isDispatchTypeCodeExists(dispatchTypeCode) {
	return dispatchTypeCodeMap.hasOwnProperty(dispatchTypeCode);
}

function isDispatchTypeNameExists(dispatchTypeName) {
	return dispatchTypeNameMap.hasOwnProperty(dispatchTypeName.toLowerCase());
}

function validate(item) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let valid = true;

	if (item.isActive !== 'y' && item.isActive !== 'n') {
		$('#errorMessage').html('Please select a valid Active status');
		toastMessage.show();
		valid = false;
	}

	if (item.dispatchTypeCode === '') {
		$('#errorMessage').html('Please fill out the Dispatch Type Code');
		toastMessage.show();
		valid = false;
	}

	if (item.dispatchTypeName === '') {
		$('#errorMessage').html('Please fill out the Dispatch Type Name');
		toastMessage.show();
		valid = false;
	}

	if (isDispatchTypeCodeExists(item.dispatchTypeCode)) {
		$('#errorMessage').html('Dispatch Type Code already exists');
		toastMessage.show();
		valid = false;
	}

	if (isDispatchTypeNameExists(item.dispatchTypeName)) {
		$('#errorMessage').html('Dispatch Type Name already exists');
		toastMessage.show();
		valid = false;
	}
	return valid;
}

function createItem(crudOperation) {
	let item = {};

	switch (crudOperation) {
		case 'create':
			item = {
				isActive: $('#addCheckActive').is(':checked') ? 'y' : 'n',
				dispatchTypeCode: $('#addDispatchCode').val() !== '' ? $('#addDispatchCode').val() : '',
				dispatchTypeName: $('#addDispatchTypeName').val()
			};
			break;

		case 'update':
			item = {
				isActive: $('#updateCheckActive').is(':checked') ? 'y' : 'n',
				dispatchTypeCode: $('#updateDispatchTypeCode').val() !== '' ? $('#updateDispatchTypeCode').val() : '',
				dispatchTypeName: $('#updateDispatchTypeName').val()
			};
			break;

		case 'delete':
			item = {
				isActive: $('#deleteCheckActive').is(':checked') ? 'y' : 'n',
				dispatchTypeCode: $('#deleteDispatchCode').val() !== '' ? $('#deleteDispatchCode').val() : '',
				dispatchTypeName: $('#deleteDispatchName').val()
			};
			break;

		default:
			console.error('Invalid CRUD operation');
			break;
	}

	return item;
}

function recalculateDispatchType() {
	dispatchTypeCodeMap = initializeDispatchTypeCodeMap();
	dispatchTypeNameMap = initializeDispatchTypeNameMap();
}

function addItem(crudOperation) {
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	let item = createItem(crudOperation);
	if (validate(item)) {
		$.post('DispatchTypeController', {
			action: 'saveItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('#divAlert').html(response);
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnMngDispatchType').click();
			} else {
				$('#errorMessage').html('Unable to save changes');
				toastMessage.show();
			}
		});
	}
}

$('#btnAddDispatchType').click(function() {
	$(this).prop('disabled', true);
	recalculateDispatchType();
	addItem("create");
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});

$('#btnUpdateDispatchType').click(function() {
	$(this).prop('disabled', true);
	recalculateDispatchType(); 
	addItem("update");
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});

$('#btnDeleteDispatchType').click(function() {
	$(this).prop('disabled', true);
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	if ($('#deleteDispatchCode').val() !== '') {
		let item = createItem('delete');
		$.post('DispatchTypeController', {
			action: 'deleteItem',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteDispatchTypeCancel').click();
				$('#divAlert').html(response);
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnMngDispatchType').click();
			} else {
				$('#errorMessage').html('Unable to save changes');
				toastMessage.show();
			}
		});
	} else {
		$('#errorMessage').html('Please select a dispatch type to delete');
		toastMessage.show();
	}
	SetTimeout(() => $(this).prop('disabled', false), 1000);
});