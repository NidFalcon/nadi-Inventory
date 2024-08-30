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

function resetForm() {
	rawMaterialTable.deselectRow();	
	$('#updateRawMaterialName').val(''),
	$('#updateRawMaterialQuantity').val(''),
	$('#updateRawMaterialListDateSelected').val('')
};

function createItem(isInsert) {
	let item = {
		materialCode:$('#rawMaterialListName').val(),
		quantity: $('#rawMaterialListQuantity').val(),
		date:$('#material-date').val()
	};
	
	if (isInsert){
		item.materialListId = 0;
	} else {
		
	}
	return item;
}

function validate(item) {
	let valid = true;
	if(item.material.materialName === '' || item.quantity === '') {
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
	console.log(item);
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

$('#btnAddRawMaterial').click(addItem);