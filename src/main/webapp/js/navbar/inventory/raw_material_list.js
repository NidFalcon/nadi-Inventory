var rawMaterialTable = new Tabulator("#divRawMaterialTable", {
	layout: "fitColumns",
	data: rawMaterialList,
	pagination: 'local',
	pagination: true,
	paginationSize: 10,
	paginationSizeSelector: [5, 10, 15, 20],
	paginationCounter: "rows",
	selectableRows: 1,
	movableColumns: true,
	responsiveLayout: true,
	columns: [
		{ title: "Material ID", field: 'materialListId'},
		{ title: "Material Code", field: 'material.materialCode' },
		{ title: "Material Name", field: 'material.materialName' },
		{ title: "Quantity", field: 'quantity' },
		{ title: "Recieved By", field: 'user.username' },
		{ title: "Date", field: 'dateReceive' },
		{ title: "Branch", field: 'branch.branchName' }
	],
});

$('#btnUpdateMaterial').hide();
$('#btnDeleteMaterial').hide();

rawMaterialTable.on('rowClick', function() {
	let row = rawMaterialTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnUpdateMaterial').show();
		$('#btnDeleteMaterial').show();
	} else {
		$('#btnUpdateMaterial').hide();
		$('#btnDeleteMaterial').hide();
	}
})

function createOptions() {
	let html = '';
	$.each(materialOptions, function(index, item) {
		if ("y" == item.isActive) {
			html += '<option id="item' + item.materialCode + '" value="' + "" + item.materialCode + '">' + item.materialCode + " " + item.materialName + '</option>';
		}
	})
	$("select").html(html);
}

function populateForm(row) {
	if (row !== undefined) {
		$('#updateRawMaterialId').val(row.materialListId);
		$('#updateRawMaterialName').val(row.material.materialCode);
		$('#updateRawMaterialQuantity').val(row.quantity);
		$('#updateDate').val(row.dateReceive);
	}
}

function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteRawMaterialId').val(row.materialListId);
		$('#deleteRawMaterialCode').val(row.material.materialCode);
		$('#deleteRawMaterialQuantity').val(row.quantity);
		$('#deleteRawMaterialUserId').val(row.userId);
		$('#deleteRawMaterialDate').val(row.dateReceive);
		$('#deleteRawMaterialBranchId').val(row.branchId);
	}
}

function createItem(operationType) {
    let item = {};

    switch (operationType) {
        case 'add':
            item = {
                materialListId: $("#rawMaterialId").val(),
                materialCode: $('#rawMaterialListName').val(),
                quantity: parseInt($('#rawMaterialListQuantity').val()),
                dateReceive: $('#dateSelected').val()
            };
            break;
        case 'update':
            item = {
                materialListId: $("#updateRawMaterialId").val(),
                materialCode: $("#updateRawMaterialName").val(),
                quantity: parseInt($("#updateRawMaterialQuantity").val()),
                dateReceive: $("#updateDate").val()
            };
            break;
        case 'delete':
            item = {
                materialListId: $('#deleteRawMaterialId').val(),
                materialCode: $('#deleteRawMaterialCode').val(),
                quantity: $('#deleteRawMaterialQuantity').val(),
                userId: $('#deleteRawMaterialUserId').val(),
                dateReceive: $('#deleteRawMaterialDate').val(),
                branchId: $('#deleteRawMaterialBranchId').val()
            };
            break;
        default:
            console.error('Invalid operation type');
            break;
    }

    return item;
}

function validate(item) {
	let valid = true;
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	if (item.materialCode === '' || item.quantity === '' || item.date === '') {
		$('#errorMessage').html('please correctly fill out the required field.');
		toastMessage.show();
		valid = false;
	} else if (!/[0-9]+/i.test(item.quantity)){
		$('#errorMessage').html('please correctly fill out the required field.');
		toastMessage.show();
		valid = false;
	}
	else if (item.quantity < 0) {
		$('#errorMessage').html('quantity must be a positive number');
		toastMessage.show();
		valid = false;
	} 
	return valid;
}

function addItem(operationType) {
	let item = createItem(operationType);
	if (validate(item)) {
		$.post('RawMaterialListController', {
			action: 'saveRawMaterial',
			item: JSON.stringify(item),
			operation: operationType
		}, function(response) {
			if (response.includes("login")){
				$('.btnCloseAddModal').click();
				$('#divMenu').html('');
				$('#divContent').html(response);
				alert("login expired. Please Login again");
			} else if (response.includes('success')) {
				$('.btnCloseAddModal').click();
				$('#divAlert').html(response);
				var toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnRawMaterials').click();	
			} else {
				  $('#divAlert').html(response);
				  var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
				  toastMessage.show();
			}
		});
	}
}

$('#btnAddRawMaterial').click(function() {
	
	addItem("add");
	
});
$('#btnUpdateRawMaterial').click(function() {
	
	addItem("update");
	
});

$('#btnDeleteRawMaterial').click(function() {
	
	var toastMessage = bootstrap.Toast.getOrCreateInstance($('#errorToast')[0]);
	if ($('#deleteRawMaterialId').val() !== '') {

		$.post('RawMaterialListController', {
			action: 'deleteRawMaterial',
			item: JSON.stringify(createItem("delete"))
		}, function(response) {
			if (response.includes('success')) {
				$('#btnDeleteRawMaterialCancel').click();
				$('#divAlert').html(response);	
				toastMessage = bootstrap.Toast.getOrCreateInstance($('#successToast')[0]);
				toastMessage.show();
				$('#btnRawMaterials').click();
			} else {
				$('#divAlert').html(response);
				$('#errorMessage').html('unable to save changes');
				toastMessage.show();
			}
		});
	} else {
		$('#divAlert').html(response);
		$('#errorMessage').html('select an item to delete');
		toastMessage.show();
	}
	setTimeout(() => $(this).prop('disabled', false), 1000);
});

createOptions();