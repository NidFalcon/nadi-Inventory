var rawMaterialTable = new Tabulator("#divRawMaterialTable" , {
	layout: 'fitColumns',
	data: rawMaterialList,
	pagination: 'local',
	pagination: true,
	paginationSize: 5,
	paginationSizeSelector:[5, 10, 15, 20],
	paginationCounter:"rows",
	selectableRows:1,
	movableColumns:true,
	responsiveLayout:true,
	columns: [
		{title:"Material ID", field: 'materialListId'},
		{title:"Material Code", field: 'material.materialCode'},
		{title:"Material Name", field: 'material.materialName'},
		{title:"Quantity", field: 'quantity'},
		{title:"User ID", field: 'userId.userId'},
		{title:"Date", field: 'dateRecieve'},
		{title:"Batch Id", field:'branch.branchId'}
	],
});


$('#btnUpdateMaterial').hide();
$('#btnDeleteMaterial').hide();

rawMaterialTable.on('rowClick',function() {
	let row = rawMaterialTable.getSelectedData()[0];
	if (row !== undefined) {
		populateForm(row);
		populateDeleteForm(row);
		$('#btnUpdateMaterial').show();
		$('#btnDeleteMaterial').show();
	} else {
		resetForm();
		$('#btnUpdateMaterial').hide();
		$('#btnDeleteMaterial').hide();
	}
})

//fill up the form for updates
function populateForm(row) {
	if(row !== undefined) {
		$('#updateRawMaterialName').val(row.material.materialName);
		$('#updateRawMaterialQuantity').val(row.quantity);
		$('#updateRawMaterialListDateSelected').val(row.dateRecieve);
	}
}

//fill up the form to delete
function populateDeleteForm(row) {
	if(row !== undefined) {
		$('#deleteRawMaterialId').val(row.materialListId);
		$('#deleteRawMaterialCode').val(row.material.materialCode);
		$('#deleteRawMaterialName').val(row.material.materialName);
		$('#deleteRawMaterialQuantity').val(row.quantity);
		$('#deleteRawMaterialUserId').val(row.userId.userId);
		$('#deleteRawMaterialDate').val(row.dateRecieve);
		$('#deleteRawMaterialBranchId').val(row.branch.Id);	
	}
}

function resetForm() {
	rawMaterialTable.deselectRow();	
	$('#updateRawMaterialName').val(''),
	$('#updateRawMaterialQuantity').val(''),
	$('#updateRawMaterialListDateSelected').val('')
};

function createItem() {
	let item = {
		materialName:('#rawMaterialListName').val(),
		quantity: $('#rawMaterialListQuantity').val(),
		date:$('#"rawMaterialListDateSelected"').val()
	};
	
	return item;
}

function validate(item) {
	let valid = true;
	if(material.materialName === '' || row.quantity == '') {
		alert('Please correctly fill-out all required fields');
		valid = false;
	} else if (item.quantity < 0) {
		alert('Quantity must be a non-negative number');
		valid = false;
	}
	return valid;
}

function addItem() {
	let item = createItem();
	if (validate(item)) {
		$.post('RawMaterialListController', {
			action: 'saveRawMaterial',
			item: JSON.stringify(item)
		}, function(response) {
			if (response.includes('success')) {
				$('#btnRawMaterials').click();
			} else {
				alert('Unable to save changes');
			}
		});
	}
}

$('#btnDeleteRawMaterial').click(function() {
	if ($('#deleteRawMaterialId').val() !== '') {
		$.post('RawMaterialController', {
			action: 'deleteRawMaterial',
			rawMaterial: JSON.stringify(createObject())
		}, function(response) {
			if (response.includes('success')) {
				$('#btnRawMaterials').click();
			} else {
				$('#divAlert').removeClass('d-none');
				$('#divAlert').html('Unable to save changes');
			}
		});
	} else {
		$('#divAlert').removeClass('d-none');
		$('#divAlert').html('Please select an item to delete');
	}
});
$('#btnAddRawMaterial').click(addItem);

