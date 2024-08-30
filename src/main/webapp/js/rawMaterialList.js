var rawMaterialTable = new Tabulator("#divRawMaterialTable" , {
	height: '212px',
	layout: 'fitDataTable',
	data: rawMaterialList,
	pagination: 'local',
	paginationSize: 5,
	selectableRows:1,
	columns: [
		{title:"Material ID", field: 'materialListId'},
		{title:"Material Name", field: 'material.materialName'},
		{title:"Quantity", field: 'quantity'},
		{title:"Date", field: 'dateRecieve'}
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

function createItem() {
	let item = {
		materialName:('#deleteRawMaterialName').val(),
		quantity: $('#deleteRawMaterialQuantity').val(),
		date:$('#deleteDate').val()
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
	console.log(JSON.stringify(item));
	/*
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
	*/
}

$('#btnAddRawMaterial').click(addItem);